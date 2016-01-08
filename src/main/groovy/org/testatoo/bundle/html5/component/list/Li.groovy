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
package org.testatoo.bundle.html5.component.list

import org.testatoo.bundle.html5.component.WebElement
import org.testatoo.core.ComponentException
import org.testatoo.core.component.Item
import org.testatoo.core.ByCss

import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@ByCss('li')
class Li extends Item implements WebElement {

    String getValue() {
        config.evaluator.eval(id, 'it.text().trim()')
    }

    boolean isSelected() {
        throw new ComponentException('Unsupported Operation')
    }

    boolean isUnselected() {
        !selected
    }

    void select() {
        throw new ComponentException("${this.class.simpleName} ${this} cannot be selected (Unsupported Operation)")
    }

    void unselect() {
        throw new ComponentException("${this.class.simpleName} ${this} cannot be unselected (Unsupported Operation)")
    }

    boolean equals(o) {
        if (this.is(o)) return true
        return value == o.value
    }

    @Override
    String toString() {
        return value
    }
}