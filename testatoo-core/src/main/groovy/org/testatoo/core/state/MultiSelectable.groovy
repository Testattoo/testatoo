package org.testatoo.core.state

import org.testatoo.core.component.Component

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class MultiSelectable extends State {

    MultiSelectable() {
        evaluator { Component c -> c.evaluator.getBool("testatoo.ext.isMultiSelectable('${c.id}')") }
        description e: 'multi selectable', w: 'not multi selectable'
    }

}
