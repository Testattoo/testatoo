package org.testatoo.hamcrest.matcher.property.dummy

import org.testatoo.core.component.Group
import org.testatoo.core.component.Item

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class DummyGroup extends Group {
    String value

    DummyGroup(String value) {
        this.value = value
    }

    @Override
    List<Item> items() {
        return null
    }

    @Override
    Item item(String value) {
        return null
    }

    @Override
    String value() {
        return value
    }
}