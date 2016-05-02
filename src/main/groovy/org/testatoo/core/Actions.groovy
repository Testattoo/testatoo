/**
 * Copyright (C) 2016 Ovea (dev@ovea.com)
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
package org.testatoo.core

import org.testatoo.core.component.Component
import org.testatoo.core.component.Item
import org.testatoo.core.input.Keyboard
import org.testatoo.core.support.*
import org.testatoo.core.support.property.InputSupport

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Actions {
    static void visit(String uri) { Testatoo.browser.open(uri) }

    static Component check(Checkable c) { c.check() }

    static Component uncheck(UnCheckable c) { c.uncheck() }

    static void type(String text) { Keyboard.type(text) }

    static void clear(Clearable c) { c.clear() }

    static void reset(Resettable c) { c.reset() }

    static void submit(Submissible c) { c.submit() }

    static <T extends Component> T on(Component c) { c as T }

    static void select(Item... items) { items.each { it.select() } }

    static void unselect(Item... items) { items.each { it.unselect() } }

    static final FillAction fill(InputSupport c) { new FillAction(c) }

    static final FillAction set(InputSupport c) { new FillAction(c) }


    public static class FillAction {
        private InputSupport input

        public FillAction(InputSupport input) {
            this.input = input
        }

        public void with(Object value) {
            input.value(value)
        }

        public void to(Object value) {
            input.value(value)
        }

    }
}
