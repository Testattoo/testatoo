package org.testatoo.core.action.support

import org.testatoo.core.action.Reset

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
trait Fillable implements ActionSupport {
    void fill() {
        this.execute(new Reset())
    }
}