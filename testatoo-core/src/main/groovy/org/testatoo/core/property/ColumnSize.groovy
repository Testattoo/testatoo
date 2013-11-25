package org.testatoo.core.property

import org.testatoo.core.component.Component
import org.testatoo.core.property.matcher.EqualsToMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class ColumnSize extends Property {

    ColumnSize() {
        evaluator { Component c -> c.evaluator.getInt("\$('#${c.id}').find('thead tr:last th').length") }
    }

    @Delegate
    private EqualsToMatcher.Matchers eq = EqualsToMatcher.matchers(this)

}
