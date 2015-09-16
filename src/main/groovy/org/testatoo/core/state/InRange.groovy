package org.testatoo.core.state

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class InRange extends State {
    InRange() {
        check "!it.is(':out-of-range')"
        description e: 'in range', w: 'out of range'
    }
}
