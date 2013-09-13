package org.testatoo.core.state

import org.testatoo.core.component.Component

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Filled extends State {

    Filled() {
        evaluator { Component c -> !c.evaluator.getBool("testatoo.ext.isEmpty('${c.id}')") }
        description e: 'not empty', w: 'empty'
    }
}