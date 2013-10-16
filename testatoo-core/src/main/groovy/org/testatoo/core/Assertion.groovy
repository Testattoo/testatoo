package org.testatoo.core

import org.testatoo.core.component.Component
import org.testatoo.core.property.Property
import org.testatoo.core.property.matcher.PropertyMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Assertion {

    Component component
    Property property
    PropertyMatcher propertyMatcher

    Assertion(Component c) {
        this.component = c;
    }

    Assertion has(Property p) {
        this.property = p
        return this
    }

    Assertion has(PropertyMatcher p) {
        this.propertyMatcher = p
        return this
    }

}
