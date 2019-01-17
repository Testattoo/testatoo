/**
 * Copyright © 2018 Ovea (d.avenante@gmail.com)
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
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.testatoo.core.Evaluator
import org.testatoo.core.MetaInfo
import org.testatoo.core.input.Key
import org.testatoo.core.input.MouseModifiers
import org.testatoo.core.internal.Log

import static org.testatoo.core.input.MouseModifiers.*

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
    Object getDriver() { webDriver }

    @Override
    void open(String url) { webDriver.get(url) }

    @Override
    <T> T getJson(String expression) {
        eval(null, "JSON.stringify(${removeTrailingChars(expression)})")?.with { new JsonSlurper().parseText(it) as T }
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
    List<MetaInfo> metaInfo(String expression) {
        List<Map> infos = getJson("${removeTrailingChars(expression)}.testatoo({method: 'metaInfos'});")
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
            if (k instanceof Key && k in [Key.SHIFT, Key.CTRL, Key.ALT]) modifiers << k
            else text << k as String
        }
        modifiers.each { action.keyDown(KeyConverter.convert(it)) }
        text.each { it instanceof Key ? action.sendKeys(KeyConverter.convert(it)) : action.sendKeys(it) }
        modifiers.each { action.keyUp(KeyConverter.convert(it)) }
        action.build().perform()
    }

    @Override
    void click(String id, Collection<MouseModifiers> mouseModifiers = [LEFT], Collection<?> keys = []) {
        WebElement element = webDriver.findElement(By.id(id))
        // Not used cause still an issue in FF => https://github.com/mozilla/geckodriver/issues/776
//        new Actions(webDriver).moveToElement(element).build().perform()
        // Temporary fix with pure js command
        runScript("document.getElementById('${id}').scrollIntoView(true)")

        // Temporary hack until Selenium fix
        if (optionInDropdown(id)) {
            element.click()
            return
        }

        Actions action = new Actions(webDriver)
        Collection<Key> modifiers = []
        Collection<String> text = []
        keys.each { k ->
            if (k instanceof Key && text) throw new IllegalArgumentException('Cannot type a modifier after some text')
            if (k instanceof Key && k in [Key.SHIFT, Key.CTRL, Key.ALT]) modifiers << k
            else text << k as String
        }
        modifiers.each { action.keyDown(KeyConverter.convert(it)) }
        text.each { it instanceof Key ? action.sendKeys(KeyConverter.convert(it)) : action.sendKeys(it) }
        if (mouseModifiers == [LEFT, SINGLE]) {
            action.click(element)
        } else if (mouseModifiers == [RIGHT, SINGLE]) {
            action.contextClick(element)
        } else if (mouseModifiers == [LEFT, DOUBLE]) {
            action.doubleClick(element)
        } else {
            throw new IllegalArgumentException('Invalid click sequence')
        }
        modifiers.each { action.keyUp(KeyConverter.convert(it)) }
        action.perform()
    }

    @Override
    void mouseOver(String id) {
        new Actions(webDriver).moveToElement(webDriver.findElement(By.id(id))).build().perform()
    }

    @Override
    void dragAndDrop(String originId, String targetId) {
        new Actions(webDriver).dragAndDrop(webDriver.findElement(By.id(originId)), webDriver.findElement(By.id(targetId))).perform()
    }

    @Override
    void close() throws Exception { webDriver.quit() }

    private String execute(String id, String s) {
        String element = ''
        if (id) {
            element = "var it = el = \$('[id=\"${id}\"]');"
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

    private boolean optionInDropdown(String id) {
        return check(id, "it.prop('tagName').toLowerCase() === 'option' && !it.closest('select').prop('multiple')")
    }
}
