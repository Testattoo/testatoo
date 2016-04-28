package org.testatoo.hamcrest.matcher.property

import org.testatoo.core.component.Item

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class InternalItem extends Item {
    String value

    InternalItem(String value) {
        this.value = value
    }

    @Override
    boolean selected() {
        return false
    }

    @Override
    boolean unselected() {
        return false
    }

    @Override
    void select() {

    }

    @Override
    void unselect() {

    }

    @Override
    Object value() {
        return value
    }
}
