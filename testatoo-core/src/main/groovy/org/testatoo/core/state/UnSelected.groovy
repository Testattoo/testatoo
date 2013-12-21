package org.testatoo.core.state

import org.testatoo.core.component.Component

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class UnSelected extends State {

    UnSelected() {
        evaluator { Component c -> !c.evaluator.getBool("\$('#${c.id}').prop('selected')") }
        description e: 'unselected', w: 'selected'
    }

}
