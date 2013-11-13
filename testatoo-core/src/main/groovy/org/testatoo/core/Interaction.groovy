package org.testatoo.core

import org.testatoo.core.component.Component
import org.testatoo.core.component.ComponentException
import org.testatoo.core.component.list.Item
import org.testatoo.core.state.Disabled

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Interaction {

    private Component c

    Interaction(Component c) {
        this.c = c
    }

    void select(String value) {
        select c.items.find { it.value.equals(value)} as Item
    }

    void select(Item item) {
        if (c.evaluator.getBool("testatoo.ext.isDisabled('${item.id}')")) {
            throw new ComponentException("${item.type} ${item} is disabled and cannot be selected ")
        }
        c.evaluator.getString("testatoo.ext.selectItem('${item.id}')")
    }

    void unselect(String value) {
        unselect c.items.find { it.value.equals(value)} as Item
    }

    void unselect(Item item) {
        if (c.evaluator.getBool("testatoo.ext.isDisabled('${item.id}')")) {
            throw new ComponentException("${item.type} ${item} is disabled and cannot be unselected ")
        }
        c.evaluator.getString("testatoo.ext.unSelectItem('${item.id}')")
    }

}
