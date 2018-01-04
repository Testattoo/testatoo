package org.testatoo.bundle.stub.field

import org.testatoo.core.CssIdentifier
import org.testatoo.core.component.field.MonthField

@CssIdentifier('MonthFieldStub')
class MonthFieldStub extends MonthField {
    String placeholder() {
        return 'Month Field Placeholder'
    }

    void value(Object value) {
    }

    void clear() {
    }

    String label() {
        return 'Month Field Label'
    }

    Object value() {
        return null
    }

    boolean empty() {
        return false
    }

    boolean focused() {
        return false
    }

    boolean readOnly() {
        return false
    }

    boolean required() {
        return false
    }

    boolean valid() {
        return false
    }
}
