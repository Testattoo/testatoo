package org.testatoo.experimental.dsl

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-04
 */
class Label implements Attribute {
    final Evaluator evaluator

    Label(Evaluator evaluator) {
        this.evaluator = evaluator
    }

    @Override
    String getValue(IdSupport component) {
        return evaluator.getLabel(component)
    }

    EqualsToMatcher equalsTo(String expected) { equalsTo([expected]) }

    EqualsToMatcher equalsTo(Collection<String> anyOfExpected) { new EqualsToMatcher(this, anyOfExpected) }
}
