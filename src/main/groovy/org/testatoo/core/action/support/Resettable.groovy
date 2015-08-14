package org.testatoo.core.action.support

import org.testatoo.core.action.Reset

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
trait Resettable implements ActionSupport {
    void reset() {
        this.execute(new Reset())
    }
}