package org.testatoo.core.component.datagrid

import org.testatoo.core.Component

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
abstract class DataGrid extends Component {

    abstract List<Column> getColumns()

    abstract List<Row> getRows()

    abstract Column column(String title)
}
