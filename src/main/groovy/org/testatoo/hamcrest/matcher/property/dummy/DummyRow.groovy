package org.testatoo.hamcrest.matcher.property.dummy

import org.testatoo.core.component.datagrid.Cell
import org.testatoo.core.component.datagrid.Row

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class DummyRow extends Row {
    String title

    DummyRow(String title) {
        this.title = title
    }

    @Override
    List<Cell> cells() {
        return null
    }

    @Override
    Cell cell(Object value) {
        return null
    }

    @Override
    String title() {
        return title
    }
}
