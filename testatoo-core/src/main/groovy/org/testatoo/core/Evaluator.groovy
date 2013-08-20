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
package org.testatoo.core

public interface Evaluator {

    String DEFAULT_NAME = Evaluator.name + ".DEFAULT";

    Object getImplementation()

    String getName()

    void open(String url)

    String  evalScript(String script)

    String getString(String jQueryExpr)

    public <T> T getJson(String jQueryExpr)

    boolean getBool(String jQueryExpr)

    int getInt(String jQueryExpr)

    boolean getBoolProperty(String id, String prop)

    int getIntProperty(String id, String prop)

    String getStringProperty(String id, String prop)

    List<MetaInfo> getMetaInfo(String jQueryExpr)

}
