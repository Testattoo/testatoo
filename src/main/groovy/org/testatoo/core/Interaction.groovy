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
package org.testatoo.core

import org.testatoo.bundle.html5.components.input.Input
import org.testatoo.bundle.html5.components.list.Item
import org.testatoo.core.state.Disabled
import org.testatoo.core.state.Selected
import org.testatoo.core.state.Unselected

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Interaction {

    private Component c

    Interaction(Component c) {
        this.c = c
    }

    void select(String value) {
        select c.items.find { it.value == value } as Item
    }

    static void select(Component c) {
        if (c.hasState(Disabled)) {
            throw new ComponentException("${c.class.simpleName} ${c} is disabled and cannot be selected")
        }

        if (c.is(Unselected))
            c.evaluator.click(c.id);
            c.evaluator.trigger(c.id, 'change')
    }

    void unselect(String value) {
        unselect c.items.find { it.value == value } as Item
    }

    static void unselect(Component c) {
        if (c.hasState(Disabled)) {
            throw new ComponentException("${c.class.simpleName} ${c} is disabled and cannot be unselected")
        }

        if (c.is(Selected))
            c.evaluator.click(c.id);
            c.evaluator.trigger(c.id, 'change')
    }

    void enter(String value) {
        // TODO remove when FF issue on new driver is fixed => https://code.google.com/p/selenium/issues/detail?id=7937
        c.evaluator.trigger(c.id, 'blur')

        c.evaluator.click(c.id);
        Input input = (Input) c
        input.reset()
        input.enter(value)
        c.evaluator.trigger(c.id, 'blur')
    }
}
