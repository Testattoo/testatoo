package org.testatoo.bundle.stub.field

import org.testatoo.core.CssIdentifier
import org.testatoo.core.component.field.DateField

@CssIdentifier('DateFieldStub')
class DateFieldStub extends DateField {
    String placeholder() {
        return 'Date Field Placeholder'
    }

    void value(Object value) {
    }

    void clear() {
    }

    String label() {
        return 'Date Field Label'
    }

    boolean inRange() {
        return false
    }

    Object maximum() {
        return null
    }

    Object minimum() {
        return null
    }

    Object step() {
        return null
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
