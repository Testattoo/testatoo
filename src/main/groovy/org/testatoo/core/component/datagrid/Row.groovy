package org.testatoo.core.component.datagrid

import org.testatoo.core.Component

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
abstract class Row extends Component {

    abstract List<Cell> getCells()

    abstract String getTitle()
}


