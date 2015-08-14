package org.testatoo.core.action

import org.testatoo.bundle.html5.Button
import org.testatoo.core.Component
import org.testatoo.core.ComponentException
import org.testatoo.core.state.Available

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Submit implements Action {
    @Override
    void execute(Component c) {
        Button submit_button = c.find('[type=submit]:first')[0] as Button
        if (submit_button && submit_button.is(new Available()))
            submit_button.execute(new MouseClick())
        else
            throw new ComponentException('Cannot submit form without submit button')
    }
}
