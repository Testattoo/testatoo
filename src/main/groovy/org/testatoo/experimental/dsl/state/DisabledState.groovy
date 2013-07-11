package org.testatoo.experimental.dsl.state

import org.testatoo.experimental.dsl.Evaluator
import org.testatoo.experimental.dsl.component.Component

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class DisabledState implements State {

    final Evaluator evaluator

    DisabledState(Evaluator evaluator) {
        this.evaluator = evaluator
    }

    @Override
    void matches(Component c) { Assert.ensure c, !evaluator.isEnabled(c), [e: 'disabled', w: 'enabled'] }

    @Override
    String toString() { 'disabled' }
}