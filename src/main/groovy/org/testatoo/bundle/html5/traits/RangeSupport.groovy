package org.testatoo.bundle.html5.traits

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
trait RangeSupport implements ValueSupport {

    BigDecimal minimun() {
        eval("it.prop('min')") as BigDecimal
    }

    BigDecimal maximum() {
        eval("it.prop('max')") as BigDecimal
    }

    BigDecimal step() {
        eval("it.prop('step')") as BigDecimal
    }


    boolean isInRange() {

    }

    boolean isOutOfRange() {

    }

}