package org.testatoo.bundle.html5.components.helper

import org.testatoo.core.Component
import org.testatoo.core.ComponentException

import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class CheckHelper {

    static boolean isChecked(Component c) {
        config.evaluator.check(c.id, "it.is(':checked')")
    }

    static boolean isUnchecked(Component c) {
        !isChecked(c)
    }

    static void check(Component c) {
        if(c.disabled)
            throw new ComponentException("${c.class.simpleName} ${c} is disabled and cannot be checked")
        if (c.unchecked)
            c.click()
        else
            throw new ComponentException("${c.class.simpleName} ${c} is already checked and cannot be checked")
    }

    static void uncheck(Component c) {
        if(c.disabled)
            throw new ComponentException("${c.class.simpleName} ${c} is disabled and cannot be unchecked")
        if (c.checked)
            c.click()
        else
            throw new ComponentException("${c.class.simpleName} ${c} is already unchecked and cannot be unchecked")
    }
}
