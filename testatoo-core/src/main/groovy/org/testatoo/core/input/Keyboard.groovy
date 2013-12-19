package org.testatoo.core.input

import org.testatoo.core.evaluator.DeferredEvaluator
import org.testatoo.core.evaluator.Evaluator

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Keyboard {

    // Settings
    // TODO Mathieu replace  DeferredEvaluator by PerThreadEvaluator
    static Evaluator evaluator = new DeferredEvaluator()

    static void enter(String data)  {
        evaluator.enter(data)
    }


}
