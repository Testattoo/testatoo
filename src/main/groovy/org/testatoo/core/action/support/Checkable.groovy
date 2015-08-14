package org.testatoo.core.action.support

import org.testatoo.core.action.Check

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
trait Checkable implements ActionSupport {
    void check() {
        this.execute(new Check())
    }
}