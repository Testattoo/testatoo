package org.testatoo.core.action

import org.testatoo.core.Component

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Fill implements Action {

    String value

    Fill(String value) {
        this.value = value
    }

    @Override
    void execute(Component c) {
        c.evaluator.trigger(c.id, 'blur')

        c.execute(new MouseClick())
        // TODO Mathieu le reset doit etre executer depuis le trai et non l action !!!
        c.execute(new Clear())

        c.evaluator.enter([value])
        c.evaluator.trigger(c.id, 'blur')
    }
}
