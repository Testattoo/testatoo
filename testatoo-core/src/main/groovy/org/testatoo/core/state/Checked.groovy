package org.testatoo.core.state

import org.testatoo.core.component.Component

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Checked extends State {
    Checked() {
        evaluator { Component c -> c.evaluator.isChecked(c) }
        description e: 'checked', w: 'unchecked'
    }
}
