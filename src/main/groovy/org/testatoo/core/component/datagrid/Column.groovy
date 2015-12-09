package org.testatoo.core.component.datagrid

import org.testatoo.core.Component

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
abstract class Column extends Component {

    abstract List<Cell> getCells()

    abstract String getTitle()
}
