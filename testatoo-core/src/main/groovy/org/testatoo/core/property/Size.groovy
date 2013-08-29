package org.testatoo.core.property

import org.testatoo.core.component.Component
import org.testatoo.core.property.matcher.EqualsToMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Size extends Property {

    Size() {
        evaluator { Component c -> c.evaluator.getInt("testatoo.ext.getSize('${c.id}')") }
    }

    @Delegate
    private EqualsToMatcher.Matchers eq = EqualsToMatcher.matchers(this)
}
