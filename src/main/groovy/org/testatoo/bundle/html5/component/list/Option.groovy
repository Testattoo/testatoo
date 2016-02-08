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
import org.testatoo.core.ByCss
import org.testatoo.core.Component
import org.testatoo.core.ComponentException
import org.testatoo.core.component.Item

import static org.testatoo.core.Testatoo.config

/**
 * @author David Avenante (d.avenante@gmail.com)
 */

@ByCss('option')
class Option extends Item implements WebElement {

    @Override
    boolean isSelected() {
        config.evaluator.check(id, "!!it.prop('selected')")
    }

    @Override
    boolean isUnselected() {
        !selected
    }

    @Override
    Object getValue() {
        config.evaluator.eval(id, "it.attr('label') && it.attr('label').length > 0 ? it.attr('label') : it.text().trim()")
    }

    @Override
    boolean isEnabled() {
        !disabled
    }

    @Override
    boolean isDisabled() {
        config.evaluator.check(id, "el.is(':disabled') || el.attr('disabled') != undefined || el.closest('select').is(':disabled')")
    }

    boolean equals(o) {
        if (this.is(o)) return true
        return value == o.value
    }

    @Override
    String toString() {
        return value
    }

    @Override
    void click() {
        // Find the parent
        try {
            Select select = $("select:has(\"#${id}\")") as Select
            config.evaluator.unselect(select, (Item[]) select.items.toArray())
        } catch (e) {
            MultiSelect select = $("select:has(\"#${id}\")") as MultiSelect
            config.evaluator.unselect(select, (Item[]) select.items.toArray())
        }
        this.select()
    }

    @Override
    void select() {
        if(this.disabled)
            throw new ComponentException("${this.class.simpleName} ${this} is disabled and cannot be selected")
        if (this.unselected) {
            // Find the parent
            Component select = $("select:has(\"#${id}\")") as Component
            config.evaluator.select(select, this)
        }
        else
            throw new ComponentException("${this.class.simpleName} ${this} is already selected and cannot be selected")
    }

    @Override
    void unselect() {
        if (this.disabled)
            throw new ComponentException("${this.class.simpleName} ${this} is disabled and cannot be unselected")
        if (this.selected) {
            // Find the parent
            Component select = $("select:has(\"#${id}\")") as Component
            config.evaluator.unselect(select, this)
        }
        else
            throw new ComponentException("${this.class.simpleName} ${this} is already unselected and cannot be unselected")
    }
}
