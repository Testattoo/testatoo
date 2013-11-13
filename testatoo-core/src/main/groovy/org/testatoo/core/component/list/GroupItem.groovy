package org.testatoo.core.component.list

import org.testatoo.core.component.Component
import org.testatoo.core.component.Type
import org.testatoo.core.property.Items
import org.testatoo.core.property.Label
import org.testatoo.core.property.Value
import org.testatoo.core.state.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class GroupItem extends Component {

    GroupItem() {
        type Type.GROUPITEM
        support Label, Value
        support Items, {
            Component c -> c.evaluator.getMetaInfo("\$('#${id}').find('option')").collect { it as Item }
        }
        support Enabled, Disabled, Available, Missing, Hidden, Visible
    }

    List<Item> getItems() {
        this.evaluator.getMetaInfo("\$('#${id}').find('option')").collect { it as Item }
    }

    boolean equals(o) {
        if (this.is(o)) return true
        return value.equals(o)
    }

    String getValue() {
        return evaluator.getString("testatoo.ext.getLabel('${id}')")
    }

    @Override
    String toString() {
        return value
    }

}
