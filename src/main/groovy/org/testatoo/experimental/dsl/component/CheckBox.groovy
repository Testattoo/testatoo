package org.testatoo.experimental.dsl.component

import org.testatoo.experimental.dsl.property.LabelProperty

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class CheckBox extends Component {
    CheckBox() {
        supportedProperties.addAll([LabelProperty])
    }
}
