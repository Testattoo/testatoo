package org.testatoo.core.state

import org.testatoo.core.component.Component

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class SingleSelectable extends State {

    SingleSelectable() {
        evaluator { Component c -> !c.evaluator.getBool("testatoo.ext.isMultiSelectable('${c.id}')") }
        description e: 'single selectable', w: 'not single selectable'
    }

}
