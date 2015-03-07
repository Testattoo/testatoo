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

import org.testatoo.core.action.Select
import org.testatoo.core.action.Unselect
import org.testatoo.core.component.Component
import org.testatoo.core.component.ComponentException
import org.testatoo.core.component.input.Input
import org.testatoo.core.component.list.Item
import org.testatoo.core.state.Disabled
import org.testatoo.core.state.Selected
import org.testatoo.core.state.UnSelected

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
            throw new ComponentException("${c.meta.type} ${c} is disabled and cannot be selected")
        }

        if (c.be(new UnSelected()))
            c.evaluator.runAction(new Select(), c)
    }

    void unselect(String value) {
        unselect c.items.find { it.value == value } as Item
    }

    static void unselect(Component c) {
        if (c.hasState(Disabled)) {
            throw new ComponentException("${c.meta.type} ${c} is disabled and cannot be unselected")
        }

        if (c.be(new Selected()))
            c.evaluator.runAction(new Unselect(), c)
    }

    void enter(String value) {
        // Click to focus on component
        c.evaluator.click(c.id);
        Input input = (Input) c
        input.reset()
        input.enter(value)
    }
}
