package org.testatoo.core.state

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class ReadOnly extends State {
    ReadOnly() {
        check "it.prop('readonly')"
        description e: 'readonly', w: 'writable'
    }
}
