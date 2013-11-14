package org.testatoo.core.component.input

import org.testatoo.core.component.Type
import org.testatoo.core.property.Maximum
import org.testatoo.core.property.Minimum
import org.testatoo.core.property.Step

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class NumberField extends TextField {

    NumberField() {
        type Type.NUMBER_FIELD
        support Minimum, Maximum, Step
    }

}
