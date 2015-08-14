package org.testatoo.core.action

import org.testatoo.core.Component

import static org.testatoo.core.input.Key.BACK_SPACE

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Clear implements Action {
    @Override
    void execute(Component c) {
        c.click()
        c.evaluator.runScript("\$('#${c.id}').val(' ').change()")
        c.evaluator.enter([BACK_SPACE])
        c.evaluator.trigger(c.id, 'blur')
        c.click()
    }
}
