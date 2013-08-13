package org.testatoo.core.property

import org.testatoo.core.Evaluator
import org.testatoo.core.component.Component
import org.testatoo.core.property.matcher.ContainingMatcher
import org.testatoo.core.property.matcher.EqualsToMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class TitleProperty extends PropertySkeleton {

    @Delegate
    private EqualsToMatcher.Matchers eq = EqualsToMatcher.matchers(this)
    @Delegate
    private ContainingMatcher.Matchers contains = ContainingMatcher.matchers(this)

    TitleProperty(Evaluator evaluator) { super(evaluator) }

    @Override
    String value(Component component) { evaluator.getTitle(component) }

    @Override
    String toString() { "Title" }
}
