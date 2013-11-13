package org.testatoo.core.property

import org.testatoo.core.component.Component
import org.testatoo.core.property.matcher.EqualsToMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class GroupItemsSize extends Property {

    GroupItemsSize() {
        evaluator { Component c -> c.evaluator.getInt("\$('#${c.id}').find('optgroup').length") }
    }

    @Delegate
    private EqualsToMatcher.Matchers eq = EqualsToMatcher.matchers(this)
}