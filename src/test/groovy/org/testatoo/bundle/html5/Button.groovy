package org.testatoo.bundle.html5

import org.testatoo.core.CssIdentifier

import static org.testatoo.core.Testatoo.getConfig

@CssIdentifier('button,input[type=submit],input[type=button],input[type=reset],input[type=image]')
class Button extends org.testatoo.core.component.Button {
    @Override
    String text() {
        config.evaluator.eval(this.id(), "it.is('input') ? it.val() : it.text().trim()")
    }
}
