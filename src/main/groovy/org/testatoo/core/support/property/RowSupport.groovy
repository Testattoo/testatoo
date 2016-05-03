package org.testatoo.core.support.property

import org.testatoo.core.component.datagrid.Row

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
interface RowSupport {
    List<Row> rows()

    Row row(String title)
}
