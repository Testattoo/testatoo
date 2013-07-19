package org.testatoo.experimental.dsl.state

import org.testatoo.experimental.dsl.Evaluator
import org.testatoo.experimental.dsl.component.Component

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class EnabledState implements State {

    final Evaluator evaluator

    EnabledState(Evaluator evaluator) {
        this.evaluator = evaluator
    }

    @Override
    void matches(Component c) { Assert.ensure c, evaluator.isEnabled(c), [e: 'enabled', w: 'disabled'] }

    @Override
    String toString() { 'enabled' }
}
