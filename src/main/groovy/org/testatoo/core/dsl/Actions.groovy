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

import org.testatoo.bundle.html5.components.list.Li
import org.testatoo.core.Component
import org.testatoo.core.input.Keyboard
import org.testatoo.core.support.*

import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
// TODO bad naming maybe TestatooDSL
class Actions {

    static void clickOn(Component c) { c.click() }

    static void visit(String uri) { browser.open(uri) }

    static final Component check(Checkable c) { c.check() }

    static final Component uncheck(UnCheckable c) { c.uncheck() }

    static void type(String text) { Keyboard.type(text) }

    static final void clear(Clearable c) { c.clear() }

    static final void reset(Resettable c) { c.reset() }

    static final void submit(Submissible c) { c.submit() }

    static final SingleSelectorAction on(SingleSelector c) { new SingleSelectorAction(c) }

    static final MultiSelectorAction on(MultiSelector c) { new MultiSelectorAction(c) }

    static final FillAction fill(InputSupport c) {
        new FillAction(c)
    }

    public static class FillAction {
        private InputSupport input

        public FillAction(InputSupport input) {
            this.input = input
        }

        public void with(String value) {
            input.value = value
        }
    }

    public static class SingleSelectorAction {
        private SingleSelector selector

        public SingleSelectorAction(SingleSelector selector) {
            this.selector = selector
        }

        public void select(String value) {
            selector.select(value)
        }

        public void select(Li item) {
            selector.select(item)
        }
    }

    public static class MultiSelectorAction {
        private MultiSelector selector

        public MultiSelectorAction(MultiSelector selector) {
            this.selector = selector
        }

        public void select(String... value) {
            selector.select(value)
        }

        public void select(Li... item) {
            selector.select(item)
        }

        public void unselect(String... values) {


        }
    }
}
