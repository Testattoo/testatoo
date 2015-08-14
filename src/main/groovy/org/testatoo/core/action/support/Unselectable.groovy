package org.testatoo.core.action.support

import org.testatoo.core.action.Unselect

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
trait Unselectable implements ActionSupport {
    void unselect() {
        this.execute(new Unselect())
    }
}
