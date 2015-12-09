package org.testatoo.bundle.html5.components.table

import org.testatoo.core.ByCss
import org.testatoo.core.component.datagrid.Column
import org.testatoo.core.component.datagrid.DataGrid
import org.testatoo.core.component.datagrid.Row

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@ByCss('table')
class Table extends DataGrid {

    @Override
    List<Th> getColumns() {
        find("thead tr:last th", Th)
    }

    @Override
    List<Tr> getRows() {
        find("tbody tr", Tr)
    }

    @Override
    Column column(String title) {
        columns.find { it.title == title }
    }
}
