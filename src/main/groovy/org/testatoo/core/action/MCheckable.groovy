package org.testatoo.core.action

import org.testatoo.core.Component

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
trait MCheckable {

    private Component getComponent() {}

    void check() {

    }

    boolean isChecked() {
        "it.is(':checked')"
    }

    boolean isUnchecked() {}
}
