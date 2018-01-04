package org.testatoo.bundle.stub.field

import org.testatoo.core.CssIdentifier
import org.testatoo.core.component.field.WeekField

@CssIdentifier('WeekFieldStub')
class WeekFieldStub extends WeekField {
    String placeholder() {
        return 'Week Field Placeholder'
    }

    void value(Object value) {
    }

    void clear() {
    }

    String label() {
        return 'Week Field Label'
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
