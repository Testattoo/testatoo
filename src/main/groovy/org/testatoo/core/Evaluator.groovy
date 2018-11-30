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
package org.testatoo.core

import org.testatoo.core.input.MouseModifiers

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
interface Evaluator extends AutoCloseable {
    Object getDriver()

    void open(String url)

    void runScript(String script)

    void registerScripts(String... scripts)

    String eval(String id, String jsExpr)

    boolean check(String id, String jsExpr)

    String getTitle()

    String getPageSource()

    String getUrl()

    Set<String> getWindowIds()

    void closeWindow(String id)

    void switchToWindow(String id)

    void clear(String id)

    void to(String url)

    void back()

    void forward()

    void refresh()

    abstract <T> T getJson(String expression)

    List<MetaInfo> metaInfo(String expression)

    void type(Collection<?> keys)

    void click(String id, Collection<MouseModifiers> click, Collection<?> keys)

    void mouseOver(String id)

    void dragAndDrop(String originId, String targetId)
}