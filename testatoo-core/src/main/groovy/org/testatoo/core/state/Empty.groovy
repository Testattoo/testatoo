package org.testatoo.core.state

import org.testatoo.core.component.Component

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Empty extends State {

    Empty() {
        evaluator { Component c -> !c.evaluator.getBool("\$('#" + c.id + "').is(':checked')") }
        description e: 'empty', w: 'not empty'
    }
}
