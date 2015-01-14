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
import org.testatoo.core.component.input.TextField
import org.testatoo.core.component.list.Item
import org.testatoo.core.state.Disabled
import org.testatoo.core.state.Selected
import org.testatoo.core.state.UnSelected

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Interactions<T extends Component> {

    private Components<T> cc

    Interactions(Components<T> cc) {
        this.cc = cc
    }

    void select(String value) {
        cc.each { select it.items.find { it.value == value } as Item }
    }

    void unselect(String value) {
        cc.each { c ->
            unselect c.items.find { it.value == value } as Item
        }
    }

    static void select(Component c) {
        if (isDisabled(c)) {
            throw new ComponentException("${c.meta.type} ${c} is disabled and cannot be selected")
        }
        if (c.is(new UnSelected()))
            c.evaluator.runAction(new Select(), c)
    }

    void unselect(Component c) {
        if (isDisabled(c)) {
            throw new ComponentException("${c.meta.type} ${c} is disabled and cannot be unselected")
        }

        if (c.is(new Selected())) {
            cc.each { it ->
                it.evaluator.runAction(new Unselect(), it)
            }
        }
    }

    void enter(String value) {
        cc.each { c ->
            // Click to focus on component
            c.evaluator.click(c.id);
            ((TextField) c).enter(value)
        }
    }

    private static boolean isDisabled(Component c) {
        Boolean.valueOf(c.evaluator.getState(new Disabled(), c))
    }

}
