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

import org.testatoo.bundle.html5.components.Button
import org.testatoo.bundle.html5.components.Form
import org.testatoo.core.Component
import org.testatoo.core.ComponentException
import org.testatoo.core.input.Keyboard
import org.testatoo.core.input.Mouse
import org.testatoo.core.traits.CheckOnlySupport
import org.testatoo.core.traits.CheckSupport
import org.testatoo.core.traits.InputSupport

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Key.BACK_SPACE

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Actions {

    static void visit(String uri) { browser.open(uri) }

    static void open(String uri) { visit(uri) }

    static void clickOn(Component c) { Mouse.clickOn(c) }

    static void type(String text) {  Keyboard.type(text) }

    static final Component check(CheckSupport c) {
        if (c.unchecked)
            c.click()
        else
            throw new ComponentException("${c.class.simpleName} ${c} is already checked and cannot be checked")
    }

    static final Component uncheck(CheckSupport c) {
        if (c in CheckOnlySupport)
            throw new ComponentException("Unsupported action 'Uncheck' on component ${c}")
        if (c.checked)
            c.click()
        else
            throw new ComponentException("${c.class.simpleName} ${c} is already unchecked and cannot be unchecked")
    }

    static final Component on(Component c) {
        return c
    }

    static final InputSupport fill(InputSupport c) {
        return c
    }

    static final void clear(InputSupport input) {
        input.click()
        config.evaluator.runScript("\$('#${input.id}').val(' ').change()")
        config.evaluator.type([BACK_SPACE])
        config.evaluator.trigger(input.id, 'blur')
        input.click()
    }

    static final void reset(Form form) {
        Button reset_button = form.find('[type=reset]:first')[0] as Button
        if (reset_button && reset_button.available)
            reset_button.click()
        else
            throw new ComponentException('Cannot reset form without reset button')
    }

    static final void submit(Component form) {
        Button submit_button = form.find('[type=submit]:first')[0] as Button
        if (submit_button && submit_button.available)
            submit_button.click()
        else
            throw new ComponentException('Cannot submit form without submit button')
    }

    static final Component select(Component c) {
        if (c.selected) {
            throw new ComponentException("${c.class.simpleName} ${c} is already selected")
        }
        c.click()
    }

    static final Component unselect(Component c) {
        if (c.unselected) {
            throw new ComponentException("${c.class.simpleName} ${c} is already unselected")
        }
        c.click()
    }
}
