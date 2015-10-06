package org.testatoo.core.traits

import org.testatoo.core.ComponentException
import org.testatoo.core.action.MouseClick

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
trait Checkable {

    void check() {
        if (unchecked)
            execute(new MouseClick())
        else
            throw new ComponentException("${this.class.simpleName} ${this} is already checked and cannot be checked")
    }

    boolean isChecked() {
        Boolean.parseBoolean(eval("it.is(':checked')"))
    }

    boolean isUnchecked() {
        !checked
    }
}
