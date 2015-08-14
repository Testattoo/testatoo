package org.testatoo.core.action

import org.testatoo.core.Component
import org.testatoo.core.ComponentException
import org.testatoo.core.state.Checked

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Uncheck implements Action {
    @Override
    void execute(Component c) {
        if (c.hasState(Checked))
            c.execute(new MouseClick())
        else
            throw new ComponentException("${c.class.simpleName} ${c} is already unchecked and cannot be unchecked")
    }
}
