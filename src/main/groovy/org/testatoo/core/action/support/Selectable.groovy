package org.testatoo.core.action.support

import org.testatoo.core.action.Select

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
trait Selectable implements ActionSupport {
    void select() {
        this.execute(new Select())
    }
}
