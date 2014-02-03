/**
 * Copyright (C) 2008 Ovea <dev@ovea.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.core.evaluator.webdriver

import groovy.json.JsonSlurper
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.testatoo.core.MetaInfo
import org.testatoo.core.evaluator.Evaluator
import org.testatoo.core.evaluator.KeyboardAction
import org.testatoo.core.evaluator.MouseAction

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class WebDriverEvaluator implements Evaluator {

    private final WebDriver webDriver
    private final JavascriptExecutor js
    private final WebDriverMouseAction mouseAction
    private final WebDriverKeyboardAction keyboardAction

    WebDriverEvaluator(WebDriver webDriver) {
        this.webDriver = webDriver
        this.js = (JavascriptExecutor) webDriver;
        this.keyboardAction = new WebDriverKeyboardAction(webDriver)
        this.mouseAction = new WebDriverMouseAction(webDriver, keyboardAction)
    }

    @Override
    WebDriver getImplementation() { webDriver }

    @Override
    void open(String url) { webDriver.get(url) }

    @Override
    boolean getBool(String jQueryExpr) { Boolean.valueOf(getString(jQueryExpr)) }

    @Override
    int getInt(String jQueryExpr) { Integer.valueOf(getString(jQueryExpr)) }

    @Override
    boolean getBoolProperty(String id, String prop) { Boolean.valueOf(getStringProperty(id, prop)) }

    @Override
    int getIntProperty(String id, String prop) { Integer.valueOf(getStringProperty(id, prop)) }

    @Override
    String getStringProperty(String id, String prop) { getString("\$('#" + id + "').prop('" + prop + "')") }

    @Override
    public <T> T getJson(String jQueryExpr) {
        getString("JSON.stringify(${removeTrailingChars(jQueryExpr)})")?.with { new JsonSlurper().parseText(it) as T }
    }

    @Override
    String getString(String jQueryExpr) { eval(jQueryExpr) }

    @Override
    String evalScript(String script) {
        eval("""(function(){
        ${removeTrailingChars(script)};
    }());""")
    }

    @Override
    void runScript(String script) {
        ((JavascriptExecutor) webDriver).executeScript(script);
    }

    @Override
    List<MetaInfo> getMetaInfo(String jQueryExpr) {
        List<Map> infos = getJson("${removeTrailingChars(jQueryExpr)}.getMetaInfos();")
        return infos.collect {
            new MetaInfo(
                    id: it.id,
                    type: it.type,
                    node: it.node
            )
        }
    }

    @Override
    KeyboardAction keyboard() { keyboardAction }

    @Override
    MouseAction mouse() { mouseAction }

    @Override
    void close() throws Exception {
        webDriver.quit()
    }

    private String eval(String s) {
        String expr = """var _evaluate = function(\$, jQuery, testatoo) {
            if(!jQuery) return '__TESTATOO_MISSING__';
                else return ${removeTrailingChars(s)};
            }; return _evaluate(window.testatoo, window.testatoo, window.testatoo);"""

        String v = js.executeScript(expr)
        if (v == '__TESTATOO_MISSING__') {
            js.executeScript(getClass().getResource("jquery-2.0.3.min.js").text + getClass().getResource("testatoo.js").text)
            v = js.executeScript(expr)
        }
        return v == 'null' || v == 'undefined' ? null : v
    }

    private static String removeTrailingChars(String expr) {
        expr = expr.trim()
        expr.endsWith(';') ? expr.substring(0, expr.length() - 1) : expr
    }
}
