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

import org.testatoo.core.component.Component
import org.testatoo.core.component.ComponentException
import org.testatoo.core.component.input.TextField
import org.testatoo.core.component.list.Item
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
        select c.items.find { it.value.equals(value) } as Item
    }

    void select(Item item) {
        if (c.evaluator.getBool("testatoo.ext.isDisabled('${item.id}')")) {
            throw new ComponentException("${item.meta.type} ${item} is disabled and cannot be selected")
        }

        if (item.is(new UnSelected()))
            c.evaluator.evalScript("\$('#${item.id}').prop('selected', true);")
    }

    void unselect(String value) {
        unselect c.items.find { it.value.equals(value) } as Item
    }

    void unselect(Item item) {
        if (c.evaluator.getBool("testatoo.ext.isDisabled('${item.id}')")) {
            throw new ComponentException("${item.meta.type} ${item} is disabled and cannot be unselected ")
        }

        if (item.is(new Selected()))
            c.evaluator.evalScript("\$('#${item.id}').prop('selected', false);")
    }

    void enter(String value) {
        ((TextField) c).enter(value)
    }

}
