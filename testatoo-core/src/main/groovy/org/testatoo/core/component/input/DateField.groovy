package org.testatoo.core.component.input

import org.testatoo.core.component.Component
import org.testatoo.core.component.Type
import org.testatoo.core.property.Maximum
import org.testatoo.core.property.Minimum
import org.testatoo.core.property.Step

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class DateField extends TextField {

    DateField() {
        type Type.DATE_FIELD
        support Minimum, { Component c -> c.evaluator.getStringProperty(c.id, 'min') }
        support Maximum, { Component c -> c.evaluator.getStringProperty(c.id, 'max') }
        support Step
    }

}
