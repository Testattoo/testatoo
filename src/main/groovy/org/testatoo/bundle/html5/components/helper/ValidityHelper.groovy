package org.testatoo.bundle.html5.components.helper

import org.testatoo.core.Component

import static org.testatoo.core.Testatoo.getConfig

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class ValidityHelper {

    static boolean isValid(Component c) {
        !isInvalid(c)
    }

    static boolean isInvalid(Component c) {
        config.evaluator.check(c.id, "it.is(':invalid')")
    }
}
