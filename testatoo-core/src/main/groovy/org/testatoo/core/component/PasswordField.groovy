package org.testatoo.core.component

import org.testatoo.core.Block
import org.testatoo.core.property.LabelProperty
import org.testatoo.core.property.PlaceholderProperty

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class PasswordField extends Component {

    PasswordField() {
        supportedProperties << PlaceholderProperty << LabelProperty
    }

    Block enter(String text) {
        return [
                run: {
                    evaluator.reset(this)
                    evaluator.focus = this
                    evaluator.type(text)
                },
                toString: { "enter '${text}' on ${this}" as String }
        ] as Block
    }

    @Override
    ComponentType getType() { ComponentType.PASSWORDFIELD }


}
