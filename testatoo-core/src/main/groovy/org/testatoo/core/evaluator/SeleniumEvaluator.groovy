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
package org.testatoo.core.evaluator

import com.thoughtworks.selenium.Selenium
import groovy.json.JsonSlurper
import org.testatoo.core.Evaluator
import org.testatoo.core.Log
import org.testatoo.core.MetaInfo

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class SeleniumEvaluator implements Evaluator {

    private final Selenium selenium
    private final String name

    SeleniumEvaluator(String name, Selenium selenium) {
        this.name = name
        this.selenium = selenium
    }

    @Override
    Selenium getImplementation() { selenium }

    @Override
    String getName() { name }

    @Override
    void open(String url) { selenium.open(url) }

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
    public <T> T getJson(String jQueryExpr) { getString("JSON.stringify(${removeTrailingChars(jQueryExpr)})")?.with { new JsonSlurper().parseText(it) as T } }

    @Override
    String getString(String jQueryExpr) { eval(jQueryExpr) }

    @Override
    String evalScript(String script) {
        eval("""(function(){
        ${removeTrailingChars(script)};
    }());""")
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

    private String eval(String s) {
        String expr = """(function(\$, jQuery, testatoo){
    if(!jQuery) return '__TESTATOO_MISSING__';
    else return ${removeTrailingChars(s)};
}(window.testatoo, window.testatoo, window.testatoo));"""
        Log.selenium "EXECUTING:\n${expr}"
        String v = selenium.getEval(expr)
        if (v == '__TESTATOO_MISSING__') {
            selenium.runScript(getClass().getResource("jquery-2.0.3.min.js").text + getClass().getResource("testatoo.js").text)
            v = selenium.getEval(expr)
        }
        Log.selenium "RESULT: ${v}"
        return v == 'null' || v == 'undefined' ? null : v
    }

    private static String removeTrailingChars(String expr) {
        expr = expr.trim()
        expr.endsWith(';') ? expr.substring(0, expr.length() - 1) : expr
    }

}