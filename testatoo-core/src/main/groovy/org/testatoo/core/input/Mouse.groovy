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
