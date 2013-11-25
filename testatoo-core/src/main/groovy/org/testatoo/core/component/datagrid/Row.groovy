package org.testatoo.core.component.datagrid

import org.testatoo.core.component.Component
import org.testatoo.core.component.Type
import org.testatoo.core.property.Size
import org.testatoo.core.state.Available
import org.testatoo.core.state.Hidden
import org.testatoo.core.state.Missing
import org.testatoo.core.state.Visible

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Row extends Component {

    Row() {
        type Type.ROW
        support Size, {
            evaluator.getInt("\$('#${id}').find('td').length")
        }
        support Available, Missing, Hidden, Visible
    }

    List<Cell> getCells() {
        this.evaluator.getMetaInfo("\$('#${id}').find('td')").collect { it as Cell }
    }

}
