package org.testatoo.core.state

import org.testatoo.core.component.Component

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Unchecked extends State {
    Unchecked() {
        evaluator { Component c -> !c.evaluator.isChecked(c) }
        description e: 'unchecked', w: 'checked'
    }
}
