package org.testatoo.core.state

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class OutOfRange extends State {
    OutOfRange() {
        check "it.is(':out-of-range')"
        description e: 'out of range', w: 'in range'
    }
}
