package org.testatoo.bundle.stub.field

import org.testatoo.core.CssIdentifier
import org.testatoo.core.component.field.URLField

@CssIdentifier('UrlFieldStub')
class UrlFieldStub extends URLField {
    String placeholder() {
        return 'Url Field Placeholder'
    }

    void value(Object value) {
    }

    void clear() {
    }

    String label() {
        return 'Url Field Label'
    }

    Object length() {
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
