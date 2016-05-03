package org.testatoo.hamcrest.matcher.property.dummy

import org.testatoo.core.component.datagrid.Cell
import org.testatoo.core.component.datagrid.Column

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class DummyColumn extends Column {
    String title

    DummyColumn(String title) {
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
