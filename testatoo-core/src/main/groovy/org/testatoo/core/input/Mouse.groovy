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
package org.testatoo.core.input

import org.testatoo.core.component.Component
import org.testatoo.core.evaluator.DeferredEvaluator
import org.testatoo.core.evaluator.Evaluator

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Mouse {

    // Settings
    // TODO Mathieu replace  DeferredEvaluator by PerThreadEvaluator
    static Evaluator evaluator = new DeferredEvaluator()

    static void clickOn(Component c) { evaluator.evalScript("testatoo.ext.simulate.click('${c.id}')") }

    static void doubleClickOn(Component c) { evaluator.evalScript("testatoo.ext.simulate.dblClick('${c.id}')") }

    static void rightClickOn(Component c) { evaluator.evalScript("testatoo.ext.simulate.rightClick('${c.id}')") }

    static void mouseOverOn(Component c) { evaluator.evalScript("testatoo.ext.simulate.mouseOver('${c.id}')") }

    static void mouseOutOff(Component c) { evaluator.evalScript("testatoo.ext.simulate.mouseOut('${c.id}')") }

    static Dragger drag(Component c) {
        return new Dragger(c)
    }

    public static class Dragger {
        private Component from;

        public Dragger(Component from) {
            this.from = from;
        }

        public void on(Component to) {
            evaluator.evalScript("testatoo.ext.simulate.drag('${from.id}', '${to.id}')")
        }
    }

}
