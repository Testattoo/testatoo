package org.testatoo.core.traits

import org.testatoo.bundle.html5.list.Item

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
trait MultiSelectable extends SingleSelectable {

    void select(Collection<Item> items) {

    }

    List<Item> getSelectedItems() {
        return null;
    }

}
