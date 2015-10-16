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

import org.testatoo.core.Component

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Actions {

    static void clickOn(Component c) { c.click() }

//    static void visit(String uri) { browser.open(uri) }
//
//    static void open(String uri) { visit(uri) }
//
//    static void type(String text) { Keyboard.type(text) }
//
//    static final Component check(CheckSupport c) {
//        c.check()
//    }
//
//    static final Component uncheck(CheckSupport c) {
//        c.uncheck();
//    }
//
//    static final Component on(Component c) {
//        return c
//    }
//
//    static final InputSupport fill(InputSupport c) {
//        return c
//    }
//
//    static final void clear(InputSupport input) {
//        input.clear()
//    }
//
//    static final void reset(Form form) {
//        Button reset_button = form.find('[type=reset]:first')[0] as Button
//        if (reset_button && reset_button.available)
//            reset_button.click()
//        else
//            throw new ComponentException('Cannot reset form without reset button')
//    }
//
//    static final void submit(Form form) {
//        Button submit_button = form.find('[type=submit]:first')[0] as Button
//        if (submit_button && submit_button.available)
//            submit_button.click()
//        else
//            throw new ComponentException('Cannot submit form without submit button')
//    }
//
//    static final Component select(SelectSupport c) {
//        c.select()
//    }
//
//    static final Component unselect(SelectSupport c) {
//        c.unselect()
//    }
}
