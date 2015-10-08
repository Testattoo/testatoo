package org.testatoo.bundle.html5.traits

import static org.testatoo.core.Testatoo.getConfig

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
trait RangeSupport implements ValueSupport {

    Object getMinimun() {
        config.evaluator.eval(id, "it.prop('min')") as BigDecimal
    }

    Object getMaximum() {
        config.evaluator.eval(id, "it.prop('max')") as BigDecimal
    }

    Object getStep() {
        config.evaluator.eval(id, "it.prop('step')") as BigDecimal
    }

    boolean isInRange() {
        !outOfRange
    }

    boolean isOutOfRange() {
        config.evaluator.getBool(id, "it.is(':out-of-range')")
    }
}