package org.testatoo.experimental.dsl

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-04
 */
class LabelAttribute implements Attribute {
    final Evaluator evaluator

    @Delegate
    private EqualsToMatcher.Matchers eq = EqualsToMatcher.matchers(this)

    @Delegate
    private ContainingMatcher.Matchers contains = ContainingMatcher.matchers(this)

    LabelAttribute(Evaluator evaluator) { this.evaluator = evaluator }

    @Override
    String getValue(IdSupport component) { evaluator.getLabel(component) }


    @Override
    String toString() { "Label" }
}
