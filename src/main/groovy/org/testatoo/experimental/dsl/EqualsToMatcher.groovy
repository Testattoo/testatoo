package org.testatoo.experimental.dsl

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-04
 */
class EqualsToMatcher implements Matcher {

    final Attribute attribute
    final Collection<String> expected

    EqualsToMatcher(Attribute attribute, Collection<String> expected) {
        this.attribute = attribute
        this.expected = expected
    }

    @Override
    void matches(Component c) {
        String v = attribute.getValue(c)
        if (!(v in expected)) {
            if (expected.size() == 1) {
                throw new AssertionError("Expected ${attribute.class.simpleName} '${expected[0]}' but was '${v}'")
            } else {
                throw new AssertionError("Expected one of ${attribute.class.simpleName} '${expected}' but was '${v}'")
            }
        }
    }

    static Matchers matchers(Attribute a) { new Matchers(attribute: a) }

    static class Matchers extends AttributeMatchers {
        EqualsToMatcher equalsTo(String expected) { equalsTo([expected]) }

        EqualsToMatcher equalsTo(Collection<String> anyOfExpected) { new EqualsToMatcher(attribute, anyOfExpected) }

        EqualsToMatcher equalsTo(String... anyOfExpected) { new EqualsToMatcher(attribute, Arrays.asList(anyOfExpected)) }
    }
}
