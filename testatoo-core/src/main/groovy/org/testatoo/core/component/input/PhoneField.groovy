package org.testatoo.core.component.input

import org.testatoo.core.component.Type
import org.testatoo.core.property.Pattern


/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class PhoneField extends TextField {

    PhoneField() {
        type Type.PHONE_FIELD
        support Pattern
    }

}
