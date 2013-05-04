package org.testatoo.experimental.dsl

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-04
 */
class Label implements Attribute {
    final Evaluator evaluator

    @Delegate
    private EqualsToMatcher.Matchers eq = EqualsToMatcher.matchers(this)

    Label(Evaluator evaluator) {
        this.evaluator = evaluator
    }

    @Override
    String getValue(IdSupport component) {
        return evaluator.getLabel(component)
    }
}
