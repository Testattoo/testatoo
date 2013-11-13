package org.testatoo.core.component.list

import org.testatoo.core.component.Component
import org.testatoo.core.component.Type
import org.testatoo.core.property.Label
import org.testatoo.core.property.Text
import org.testatoo.core.property.Value
import org.testatoo.core.state.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Item extends Component {

    Item() {
        type Type.ITEM
        support Text, Value, Label
        support Enabled, Disabled, Available, Missing, Hidden, Visible
    }

    boolean equals(o) {
        if (this.is(o)) return true
        return value.equals(o)
    }

    String getValue() {
        return evaluator.getString("testatoo.ext.getText('${id}')")
    }

    @Override
    String toString() {
        return value
    }

}
