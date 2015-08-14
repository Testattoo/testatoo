package org.testatoo.core.action.support

import org.testatoo.core.action.Uncheck

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
trait Uncheckable implements ActionSupport {
    void uncheck() {
        this.execute(new Uncheck())
    }
}