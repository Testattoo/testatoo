package org.testatoo.core.property

import org.testatoo.core.component.Component
import org.testatoo.core.property.matcher.EqualsToMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class VisibleItemsSize extends Property {

    VisibleItemsSize() {
        evaluator { Component c -> c.evaluator.getIntProperty(c.id, 'size') }
    }

    @Delegate
    private EqualsToMatcher.Matchers eq = EqualsToMatcher.matchers(this)
}