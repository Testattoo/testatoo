/**
 * Copyright © 2016 Ovea (d.avenante@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.evaluator.webdriver

import groovy.json.JsonSlurper
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions
import org.testatoo.core.Evaluator
import org.testatoo.core.MetaInfo
import org.testatoo.core.input.Key
import org.testatoo.core.input.MouseModifiers
import static org.testatoo.core.input.MouseModifiers.*
import org.testatoo.core.internal.Log

import static org.testatoo.core.input.Key.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class WebDriverEvaluator implements Evaluator {
    private final WebDriver webDriver
    private final JavascriptExecutor js
    private final List<String> registeredScripts = new ArrayList<>()

    WebDriverEvaluator(WebDriver webDriver) {
        this.webDriver = webDriver
        this.js = (JavascriptExecutor) webDriver
    }

    @Override
    WebDriver getDriver() { webDriver }

    @Override
    void open(String url) { webDriver.get(url) }

    @Override
    <T> T getJson(String jQueryExpr) {
        eval(null, "JSON.stringify(${removeTrailingChars(jQueryExpr)})")?.with { new JsonSlurper().parseText(it) as T }
    }

    @Override
    String eval(String id, String jsExpr) {
        execute(id, jsExpr)
    }

    @Override
    boolean check(String id, String jsExpr) {
        Boolean.parseBoolean(eval(id, jsExpr))
    }

    @Override
    void runScript(String script) {
        js.executeScript(script)
    }

    @Override
    void registerScripts(String... scripts) {
        registeredScripts.addAll(scripts)
    }

    @Override
    String getTitle() {
        webDriver.title
    }

    @Override
    String getPageSource() {
        webDriver.pageSource
    }

    @Override
    String getUrl() {
        webDriver.currentUrl
    }

    @Override
    Set<String> getWindowIds() {
        webDriver.windowHandles
    }

    @Override
    void switchToWindow(String id) {
        webDriver.switchTo().window(id)
    }

    @Override
    void closeWindow(String id) {
        webDriver.switchTo().window(id).close()
    }

    @Override
    void to(String url) {
        webDriver.navigate().to(url)
    }

    @Override
    void back() {
        webDriver.navigate().back()
    }

    @Override
    void forward() {
        webDriver.navigate().forward()
    }

    @Override
    void refresh() {
        webDriver.navigate().refresh()
    }

    @Override
    List<MetaInfo> metaInfo(String jQueryExpr) {
        List<Map> infos = getJson("${removeTrailingChars(jQueryExpr)}.testatoo({method:'metaInfos'});")
        return infos.collect {
            new MetaInfo(
                    id: it.id,
                    node: it.node
            )
        }
    }

    @Override
    void type(Collection<?> keys) {
        Actions action = new Actions(webDriver)
        Collection<Key> modifiers = []
        Collection<String> text = []
        keys.each { k ->
            if (k instanceof Key && text) throw new IllegalArgumentException('Cannot type a modifier after some text')
            if (k instanceof Key && k in [SHIFT, CTRL, ALT]) modifiers << k
            else text << k as String
        }
        modifiers.each { action.keyDown(KeyConverter.convert(it)) }
        text.each { it instanceof Key ? action.sendKeys(KeyConverter.convert(it)) : action.sendKeys(it) }
        modifiers.each { action.keyUp(KeyConverter.convert(it)) }
        action.build().perform()
    }

    @Override
    void click(String id, Collection<MouseModifiers> mouseModifiers = [LEFT], Collection<?> keys = []) {
        if (mouseModifiers == [LEFT, SINGLE] && keys.empty) {
            webDriver.findElement(By.id(id)).click()
            return
        }

        Actions action = new Actions(webDriver)
        Collection<Key> modifiers = []
        Collection<String> text = []
        keys.each { k ->
            if (k instanceof Key && text) throw new IllegalArgumentException('Cannot type a modifier after some text')
            if (k instanceof Key && k in [SHIFT, CTRL, ALT]) modifiers << k
            else text << k as String
        }
        modifiers.each { action.keyDown(KeyConverter.convert(it)) }
        text.each { it instanceof Key ? action.sendKeys(KeyConverter.convert(it)) : action.sendKeys(it) }
        if (mouseModifiers == [LEFT, SINGLE]) {
            action.click(webDriver.findElement(By.id(id)))
        } else if (mouseModifiers == [RIGHT, SINGLE]) {
            action.contextClick(webDriver.findElement(By.id(id)))
        } else if (mouseModifiers == [LEFT, DOUBLE]) {
            action.doubleClick(webDriver.findElement(By.id(id)))
        } else {
            throw new IllegalArgumentException('Invalid click sequence')
        }
        modifiers.each { action.keyUp(KeyConverter.convert(it)) }
        action.build().perform()
    }

    @Override
    void mouseOver(String id) {
        new Actions(webDriver).moveToElement(webDriver.findElement(By.id(id))).build().perform()
    }

    @Override
    void dragAndDrop(String originId, String targetId) {
        new Actions(webDriver).dragAndDrop(webDriver.findElement(By.id(originId)), webDriver.findElement(By.id(targetId))).build().perform()
    }

    @Override
    void close() throws Exception { webDriver.quit() }

    private String execute(String id, String s) {
        String element = ''
        if (id) {
            element = "var it = el = \$('#${id}');"
        }

        String expr = """
        return (function(jQuery) {
            if(!jQuery) { return '__JQUERY_MISSING__'; }
            if (!jQuery().testatoo) { return '__TESTATOO_MISSING__'; }
            else {
                $element
                return ${removeTrailingChars(s)};
            }
        }(window.jQuery));"""

        Log.log('====== Expression ======')
        Log.log(expr)

        String v = js.executeScript(expr)

        Log.log('======== RESULT ========')
        Log.log(v)

        if (v == '__JQUERY_MISSING__') {
            js.executeScript(getClass().getResource('jquery-3.1.1.slim.min.js').text
                    + getClass().getResource('testatoo.js').text)
            registeredScripts.each { js.executeScript(it) }
            return execute(id, s)
        }
        if (v == '__TESTATOO_MISSING__') {
            js.executeScript(getClass().getResource('testatoo.js').text)
            registeredScripts.each { js.executeScript(it) }
            return execute(id, s)
        }

        return v == 'null' || v == 'undefined' ? null : v
    }

    private static String removeTrailingChars(String expr) {
        expr = expr.trim()
        expr.endsWith(';') ? expr.substring(0, expr.length() - 1) : expr
    }
}