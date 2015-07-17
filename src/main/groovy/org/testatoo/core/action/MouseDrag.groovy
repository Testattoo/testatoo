package org.testatoo.core.action

import org.testatoo.core.Component

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class MouseDrag implements Action {

    final Component onto

    MouseDrag(Component onto) {
        this.onto = onto
    }

    @Override
    void execute(Component c) {
        c.evaluator.dragAndDrop(c.id, onto.id)
    }
}
