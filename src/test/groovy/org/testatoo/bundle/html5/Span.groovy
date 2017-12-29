package org.testatoo.bundle.html5

import org.testatoo.core.CssIdentifier
import org.testatoo.core.component.Component
import org.testatoo.core.support.property.TextSupport

import static org.testatoo.core.Testatoo.getConfig

@CssIdentifier('span')
class Span extends Component implements TextSupport {
    @Override
    String text() {
        config.evaluator.eval(id(), "it.text()")
    }
}
