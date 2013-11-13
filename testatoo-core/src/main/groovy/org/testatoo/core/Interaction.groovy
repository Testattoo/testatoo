package org.testatoo.core

import org.testatoo.core.component.Component
import org.testatoo.core.component.list.Item

import static org.testatoo.core.Testatoo.assertThat

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
        c.evaluator.getString("testatoo.ext.selectItem('${item.id}')")
    }

    void unSelect(String value) {
        unSelect c.items.find { it.value.equals(value)} as Item

    }

    void unSelect(Item item) {
        c.evaluator.getString("testatoo.ext.unSelectItem('${item.id}')")
    }

}
