package org.testatoo.bundle.html5.components.helper

import org.testatoo.core.Component
import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class RangeHelper {

    static Object getMinimun(Component c) {
        config.evaluator.eval(c.id, "it.prop('min')")
    }

    static Object getMaximum(Component c) {
        config.evaluator.eval(c.id, "it.prop('max')")
    }

    static Number getStep(Component c) {
        Object value = config.evaluator.eval(c.id, "it.prop('step')")
        if (value)
            value as BigDecimal
        else
            0
    }

    static boolean isInRange(Component c) {
        !isOutOfRange(c)
    }

    static boolean isOutOfRange(Component c) {
        config.evaluator.check(c.id, "it.is(':out-of-range')")
    }
}
