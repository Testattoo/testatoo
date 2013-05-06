package org.testatoo.experimental.dsl

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-04
 */
class TextAttribute implements Attribute {
    final Evaluator evaluator

    @Delegate
    private EqualsToMatcher.Matchers eq = EqualsToMatcher.matchers(this)

    @Delegate
    private ContainingMatcher.Matchers contains = ContainingMatcher.matchers(this)

    TextAttribute(Evaluator evaluator) { this.evaluator = evaluator }

    @Override
    String getValue(IdSupport component) { evaluator.getText(component) }

    @Override
    String toString() { "Text" }
}
