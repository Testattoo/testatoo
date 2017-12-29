package org.testatoo.bundle.html5

import org.testatoo.core.CssIdentifier
import org.testatoo.core.component.Component
import org.testatoo.core.support.property.ValueSupport
import org.testatoo.core.support.state.EmptySupport

import static org.testatoo.core.Testatoo.getConfig

@CssIdentifier('input[type=text]')
class InputTypeText extends Component implements ValueSupport, EmptySupport {
    Object value() {
        config.evaluator.eval(id(), "it.val()")
    }

    void clear() {
        this.click()
        config.evaluator.runScript("\$('#${id()}').val('').change()")
    }

    boolean empty() {
        config.evaluator.check(id(), "\$.trim(it.val()).length == 0")
    }
}
