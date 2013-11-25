package org.testatoo.core.component.datagrid

import org.testatoo.core.component.Component
import org.testatoo.core.component.Type
import org.testatoo.core.property.Value
import org.testatoo.core.state.Available
import org.testatoo.core.state.Hidden
import org.testatoo.core.state.Missing
import org.testatoo.core.state.Visible

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Cell extends Component {

    Cell() {
        type Type.CELL
        support Value, { evaluator.getString("testatoo.ext.getText('${id}')") }
        support Available, Missing, Hidden, Visible
    }
}
