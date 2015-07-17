package org.testatoo.core.action

import org.testatoo.core.Component
import org.testatoo.core.input.Key

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class MouseClick implements Action {

    MouseClick(Collection<MouseModifiers> mouseModifiers = [], Collection<Key> keyModifiers = []) {
    }

    @Override
    void execute(Component c) {

    }
}
