package org.testatoo.core.action.support

import org.testatoo.core.action.Submit

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
trait Submissible implements ActionSupport {
    void submit() {
        this.execute(new Submit())
    }
}
