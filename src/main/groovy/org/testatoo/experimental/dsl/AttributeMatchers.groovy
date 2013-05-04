package org.testatoo.experimental.dsl

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-04
 */
class AttributeMatchers {
    Attribute attribute

    Attribute getAttribute() {
        if (!attribute) throw new AssertionError('Attribute not set !')
        return attribute
    }
}
