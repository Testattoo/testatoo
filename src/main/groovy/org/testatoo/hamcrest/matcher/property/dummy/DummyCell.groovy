package org.testatoo.hamcrest.matcher.property.dummy

import org.testatoo.core.component.datagrid.Cell

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class DummyCell extends Cell {
    String value

    DummyCell(String value) {
        this.value = value
    }

    @Override
    Object value() {
        return value
    }
}
