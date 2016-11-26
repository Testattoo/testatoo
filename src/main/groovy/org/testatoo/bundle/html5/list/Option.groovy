/**
 * Copyright © 2016 Ovea (d.avenante@gmail.com)
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
package org.testatoo.bundle.html5.list

import org.testatoo.core.CssIdentifier
import org.testatoo.core.ComponentException
import org.testatoo.core.component.Item

import static org.testatoo.core.Testatoo.$
import static org.testatoo.core.Testatoo.config
import static org.testatoo.core.input.Key.CTRL
import static org.testatoo.core.input.MouseModifiers.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */

@CssIdentifier('option')
class Option extends Item {
    @Override
    boolean selected() {
        config.evaluator.check(id(), "!!it.prop('selected')")
    }

    @Override
    Object value() {
        config.evaluator.eval(id(), "it.text().trim().length > 0 ? it.text().trim() : (it.attr('label') && it.attr('label').length > 0 ? it.attr('label') : '')")
    }

    @Override
    boolean enabled() {
        !config.evaluator.check(id(), "el.is(':disabled') || el.attr('disabled') != undefined || el.closest('select').is(':disabled')")
    }

    boolean equals(Option o) {
        if (this.is(o)) return true
        value() == o.value()
    }

    @Override
    String toString() {
        return value()
    }

    @Override
    void click() {
        // TODO: To fix FF issue so un-select all others before click
        if(onMultiSelect()) {
            MultiSelect select = $("select:has(\"#${id()}\")") as MultiSelect
            select.items().findAll { it.selected() }.forEach {
                config.evaluator.click(it.id(), [LEFT, SINGLE], [CTRL])
            }
        }
        if (!selected()) {
            if(onMultiSelect()) {
                config.evaluator.click(id(), [LEFT, SINGLE], [CTRL])
            } else {
                config.evaluator.click(id(), [LEFT, SINGLE], [])
            }
        }
    }

    @Override
    void select() {
        if (!enabled())
            throw new ComponentException("${this.class.simpleName} ${this} is disabled and cannot be selected")
        if (!selected()) {
            if(onMultiSelect()) {
                config.evaluator.click(id(), [LEFT, SINGLE], [CTRL])
            } else {
                config.evaluator.click(id(), [LEFT, SINGLE], [])
            }

        } else
            throw new ComponentException("${this.class.simpleName} ${this} is already selected and cannot be selected")
    }

    @Override
    void unselect() {
        if (!enabled())
            throw new ComponentException("${this.class.simpleName} ${this} is disabled and cannot be unselected")
        if (selected()) {
            config.evaluator.click(id(), [LEFT, SINGLE], [CTRL])
        } else
            throw new ComponentException("${this.class.simpleName} ${this} is already unselected and cannot be unselected")
    }

    private boolean onMultiSelect() {
        config.evaluator.check(id(), "it.closest('select').attr('multiple') && it.closest('select').attr('multiple').length > 0")
    }
}
