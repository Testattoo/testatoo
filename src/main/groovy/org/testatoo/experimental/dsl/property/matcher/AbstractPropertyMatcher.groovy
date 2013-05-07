package org.testatoo.experimental.dsl.property.matcher

import org.testatoo.experimental.dsl.property.Property
import org.testatoo.experimental.dsl.component.Component

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-07
 */
abstract class AbstractPropertyMatcher implements PropertyMatcher {

    final Property property

    AbstractPropertyMatcher(Property property) {
        this.property = property
    }

    @Override
    final void matches(Component c) {
        if (!c.supports(property)) {
            throw new AssertionError("Component ${this} doesn ot support property ${getClass().simpleName}")
        }
        String v = property.getValue(c)
        doMatch(c, v)
    }

    abstract void doMatch(Component c, String currentValue)
}
