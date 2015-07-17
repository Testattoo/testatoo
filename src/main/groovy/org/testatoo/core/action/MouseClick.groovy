package org.testatoo.core.action

import org.testatoo.core.Component
import org.testatoo.core.input.Key

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class MouseClick implements Action {

    final Collection<MouseModifiers> mouseModifiers
    final Collection<Key> keyModifiers

    MouseClick(Collection<MouseModifiers> mouseModifiers = [MouseModifiers.LEFT, MouseModifiers.SINGLE], Collection<Key> keyModifiers = []) {
        this.mouseModifiers = mouseModifiers
        this.keyModifiers = keyModifiers
    }

    @Override
    void execute(Component c) {
        c.evaluator.click(c.id, mouseModifiers, keyModifiers)
    }
}
