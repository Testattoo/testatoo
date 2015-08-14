package org.testatoo.core.action

import org.testatoo.bundle.html5.Button
import org.testatoo.core.Component
import org.testatoo.core.ComponentException
import org.testatoo.core.state.Available

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Reset implements Action {
    @Override
    void execute(Component c) {
        Button reset_button = c.find('[type=reset]:first')[0] as Button
        if (reset_button && reset_button.is(new Available()))
            reset_button.execute(new MouseClick())
        else
            throw new ComponentException('Cannot reset form without reset button')
    }
}
