package org.testatoo.core.state

import org.testatoo.core.component.Component

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Selected extends State {

    Selected() {
        evaluator { Component c -> c.evaluator.getBool("\$('#${c.id}').prop('selected')") }
        description e: 'selected', w: 'unselected'
    }

}
