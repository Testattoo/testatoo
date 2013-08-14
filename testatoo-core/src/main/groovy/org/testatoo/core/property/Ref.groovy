package org.testatoo.core.property

import org.testatoo.core.component.Component
import org.testatoo.core.property.matcher.ContainingMatcher
import org.testatoo.core.property.matcher.EqualsToMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Ref extends Property {

    Ref() {
        evaluator { Component c -> c.evaluator.getReference(c) }
    }

    @Delegate
    private EqualsToMatcher.Matchers eq = EqualsToMatcher.matchers(this)
    @Delegate
    private ContainingMatcher.Matchers contains = ContainingMatcher.matchers(this)

}
