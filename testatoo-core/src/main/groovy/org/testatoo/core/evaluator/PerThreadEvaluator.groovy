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

import org.testatoo.core.MetaInfo

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-08-31
 */
class PerThreadEvaluator implements Evaluator {

    private static final ThreadLocal<Evaluator> current = new ThreadLocal<>()

    static void set(Evaluator e) {
        if (current.get()) {
            throw new IllegalStateException('An evaluator is already bound to local thread: ' + Thread.currentThread().name)
        }
        current.set(e)
    }

    static void remove() { current.remove() }

    static Evaluator getEvaluator() {
        Evaluator e = current.get()
        if (!e) {
            throw new IllegalStateException('No evaluator bound to local thread: ' + Thread.currentThread().name)
        }
        return e
    }

    @Override
    Object getImplementation() { evaluator.implementation }

    @Override
    String getName() { evaluator.name }

    @Override
    void open(String url) { evaluator.open(url) }

    @Override
    String evalScript(String script) { evaluator.evalScript(script) }

    @Override
    void runScript(String script) { evaluator.runScript(script) }

    @Override
    String getString(String jQueryExpr) { evaluator.getString(jQueryExpr) }

    @Override
    def <T> T getJson(String jQueryExpr) { evaluator.getJson(jQueryExpr) }

    @Override
    boolean getBool(String jQueryExpr) { evaluator.getBool(jQueryExpr) }

    @Override
    int getInt(String jQueryExpr) { evaluator.getInt(jQueryExpr) }

    @Override
    boolean getBoolProperty(String id, String prop) { evaluator.getBoolProperty(id, prop) }

    @Override
    int getIntProperty(String id, String prop) { evaluator.getIntProperty(id, prop) }

    @Override
    String getStringProperty(String id, String prop) { evaluator.getStringProperty(id, prop) }

    @Override
    List<MetaInfo> getMetaInfo(String jQueryExpr) { evaluator.getMetaInfo(jQueryExpr) }
}
