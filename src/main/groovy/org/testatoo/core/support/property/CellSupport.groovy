package org.testatoo.core.support.property

import org.testatoo.core.component.datagrid.Cell

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
interface CellSupport {
    List<Cell> cells()

    Cell cell(Object value)
}