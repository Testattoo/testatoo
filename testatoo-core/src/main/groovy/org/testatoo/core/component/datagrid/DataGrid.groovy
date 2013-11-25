package org.testatoo.core.component.datagrid

import org.testatoo.core.component.Component
import org.testatoo.core.component.Type
import org.testatoo.core.property.ColumnSize
import org.testatoo.core.property.RowSize
import org.testatoo.core.property.Size
import org.testatoo.core.state.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class DataGrid extends Component {

    DataGrid() {
        type Type.DATAGRID
        support Size, ColumnSize, RowSize
        support Enabled, Disabled, Available, Missing, Hidden, Visible
    }

    List<Column> getColumns() {
        this.evaluator.getMetaInfo("\$('#${id}').find('thead tr:last th')").collect { it as Column }
    }

    List<Row> getRows() {
        this.evaluator.getMetaInfo("\$('#${id}').find('tbody tr')").collect { it as Row }
    }
}
