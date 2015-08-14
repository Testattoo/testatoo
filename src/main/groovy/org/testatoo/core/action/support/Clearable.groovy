package org.testatoo.core.action.support

import org.testatoo.core.action.Clear

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
trait Clearable implements ActionSupport {
    void clear() {
        this.execute(new Clear())
    }
}