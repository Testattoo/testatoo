package org.testatoo.core.state

import org.testatoo.core.Evaluator
import org.testatoo.core.component.Component

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class CheckedState implements State {

    final Evaluator evaluator

    CheckedState(Evaluator evaluator) {
        this.evaluator = evaluator
    }

    @Override
    void matches(Component c) { Assert.ensure c, evaluator.isChecked(c), [e: 'checked', w: 'unchecked'] }

    @Override
    String toString() { 'checked' }
}
