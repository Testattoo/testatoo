package org.testatoo.experimental.dsl.attribute.matcher

import org.testatoo.experimental.dsl.attribute.Attribute
import org.testatoo.experimental.dsl.component.Component

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-07
 */
abstract class AbstractAttributeMatcher implements AttributeMatcher {

    final Attribute attribute

    AbstractAttributeMatcher(Attribute attribute) {
        this.attribute = attribute
    }

    @Override
    final void matches(Component c) {
        if (!c.supports(attribute)) {
            throw new AssertionError("Component ${this} doesn ot support attribute ${getClass().simpleName}")
        }
        String v = attribute.getValue(c)
        doMatch(c, v)
    }

    abstract void doMatch(Component c, String currentValue)
}
