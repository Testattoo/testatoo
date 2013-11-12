package org.testatoo.core.property

import org.testatoo.core.component.Component
import org.testatoo.core.component.list.Item
import org.testatoo.core.property.matcher.EqualsToListMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class SelectedItems extends Property {

    SelectedItems() {
        evaluator { Component c -> c.evaluator.getMetaInfo("\$('#${c.id} option:selected')").collect { (it as Item).value } }
    }

    @Delegate
    private EqualsToListMatcher.Matchers eq = EqualsToListMatcher.matchers(this)
}
