package org.testatoo.core.property

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

    @Override
    String value(Component component) { component.evaluator.getReference(component) }

    @Override
    String toString() { "Reference" }
}
