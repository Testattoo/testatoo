package org.testatoo.core.property

import org.testatoo.core.Evaluator
import org.testatoo.core.component.Component
import org.testatoo.core.property.matcher.ContainingMatcher
import org.testatoo.core.property.matcher.EqualsToMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class ReferenceProperty extends PropertySkeleton {

    @Delegate
    private EqualsToMatcher.Matchers eq = EqualsToMatcher.matchers(this)
    @Delegate
    private ContainingMatcher.Matchers contains = ContainingMatcher.matchers(this)

    ReferenceProperty(Evaluator evaluator) { super(evaluator) }

    @Override
    String value(Component component) { evaluator.getReference(component) }

    @Override
    String toString() { "Reference" }
}
