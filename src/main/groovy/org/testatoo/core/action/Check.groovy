package org.testatoo.core.action

import org.testatoo.core.Component
import org.testatoo.core.ComponentException
import org.testatoo.core.state.Unchecked

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Check implements Action {
    @Override
    void execute(Component c) {
        if (c.hasState(Unchecked))
            c.execute(new MouseClick())
        else
            throw new ComponentException("${c.class.simpleName} ${c} is already checked and cannot be checked")
    }
}
