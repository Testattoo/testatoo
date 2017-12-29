package org.testatoo.bundle.html5

import org.testatoo.core.CssIdentifier
import org.testatoo.core.component.Panel

import static org.testatoo.core.Testatoo.getConfig

@CssIdentifier('div')
class DropPanel extends Panel {
    String title() {
        config.evaluator.eval(id(), "it.find('h1').text()")
    }
}