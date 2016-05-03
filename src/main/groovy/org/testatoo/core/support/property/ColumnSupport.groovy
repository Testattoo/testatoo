package org.testatoo.core.support.property

import org.testatoo.core.component.datagrid.Column

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
interface ColumnSupport {
    List<Column> columns()

    Column column(String title)
}
