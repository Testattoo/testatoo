/**
 * Copyright (C) 2014 Ovea (dev@ovea.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.core.dsl

import org.testatoo.core.Component
import org.testatoo.core.input.Keyboard
import org.testatoo.core.support.*

import static org.testatoo.core.Testatoo.getBrowser

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Actions {

    static void clickOn(Component c) { c.click() }

    static void visit(String uri) { browser.open(uri) }

    static final Component check(Checkable c) { c.check() }

    static final Component uncheck(UnCheckable c) { c.uncheck() }

    static void type(String text) { Keyboard.type(text) }

    static final void clear(Clearable c) { c.clear() }

    static final void reset(Resettable c) { c.reset() }

    static final void submit(Submissible c) { c.submit() }

    static final <T extends Component> T on(Component c) { c as T }

    static final FillBuilder fill(InputSupport c) {
        new FillBuilder(c)
    }

    public static class FillBuilder {
        private InputSupport input

        public FillBuilder(InputSupport input) {
            this.input = input
        }

        public void with(String value) {
            input.value = value
        }
    }

//    static void open(String uri) { visit(uri) }
}
