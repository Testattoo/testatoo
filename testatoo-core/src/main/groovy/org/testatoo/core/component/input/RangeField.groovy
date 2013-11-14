package org.testatoo.core.component.input

import org.testatoo.core.component.Type
import org.testatoo.core.property.Maximum
import org.testatoo.core.property.Minimum
import org.testatoo.core.property.Step

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class RangeField extends TextField {

    RangeField() {
        type Type.RANGE_FIELD
        support Minimum, Maximum, Step
    }

}
