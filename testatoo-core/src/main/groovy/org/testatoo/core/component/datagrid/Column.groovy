package org.testatoo.core.component.datagrid

import org.testatoo.core.component.Component
import org.testatoo.core.component.Type
import org.testatoo.core.property.Size
import org.testatoo.core.property.Title
import org.testatoo.core.state.Available
import org.testatoo.core.state.Hidden
import org.testatoo.core.state.Missing
import org.testatoo.core.state.Visible

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Column extends Component {

    Column() {
        type Type.COLUMN
        support Size, {
            int index = evaluator.getInt("\$('#${id}').index()") + 1
            evaluator.getInt("\$('#${id}').closest('table').find('tbody tr').find('td:nth-child(${index})').length")
        }
        support Title, {
            evaluator.getString("testatoo.ext.getText('${id}')")
        }
        support Available, Missing, Hidden, Visible
    }

    List<Cell> getCells() {
        int index = evaluator.getInt("\$('#${id}').index()") + 1
        this.evaluator.getMetaInfo("\$('#${id}').closest('table').find('tbody tr').find('td:nth-child(${index})')").collect { it as Cell }
    }

}
