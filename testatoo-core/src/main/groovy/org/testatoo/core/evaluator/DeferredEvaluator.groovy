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
 * @author David Avenante (d.avenante@gmail.com)
 */
class DeferredEvaluator implements Evaluator {

    @Override
    Object getImplementation() { EvaluatorHolder.get().implementation }

    @Override
    String getName() { EvaluatorHolder.get().name }

    @Override
    void open(String url) { EvaluatorHolder.get().open(url) }

    @Override
    String getString(String jQueryExpr) { EvaluatorHolder.get().getString(jQueryExpr) }

    @Override
    String evalScript(String script) { EvaluatorHolder.get().evalScript(script) }

    @Override
    void runScript(String script) { EvaluatorHolder.get().runScript(script) }

    @Override
    public <T> T getJson(String jQueryExpr) { EvaluatorHolder.get().getJson(jQueryExpr) }

    @Override
    boolean getBool(String jQueryExpr) {EvaluatorHolder.get().getBool(jQueryExpr) }

    @Override
    int getInt(String jQueryExpr) {EvaluatorHolder.get().getInt(jQueryExpr) }

    boolean getBoolProperty(String id, String prop) { EvaluatorHolder.get().getBoolProperty(id, prop) }

    int getIntProperty(String id, String prop) { EvaluatorHolder.get().getIntProperty(id, prop) }

    @Override
    String getStringProperty(String id, String prop) { EvaluatorHolder.get().getStringProperty(id, prop) }

    @Override
    List<MetaInfo> getMetaInfo(String jQueryExpr) { EvaluatorHolder.get().getMetaInfo(jQueryExpr) }

}
