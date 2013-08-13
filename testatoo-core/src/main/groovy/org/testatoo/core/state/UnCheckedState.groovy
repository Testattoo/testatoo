package org.testatoo.core.state

import org.testatoo.core.Evaluator
import org.testatoo.core.component.Component

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class UnCheckedState implements State {

    final Evaluator evaluator

    UnCheckedState(Evaluator evaluator) {
        this.evaluator = evaluator
    }

    @Override
    void matches(Component c) { Assert.ensure c, !evaluator.isChecked(c), [e: 'unchecked', w: 'checked'] }

    @Override
    String toString() { 'unchecked' }
}
