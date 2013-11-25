package org.testatoo.core.component.list

import org.testatoo.core.component.Component
import org.testatoo.core.component.Type
import org.testatoo.core.property.Items
import org.testatoo.core.property.Size
import org.testatoo.core.state.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class ListView extends Component {

    ListView() {
        type Type.LISTVIEW
        support Size
        support Items, {
            Component c -> c.evaluator.getMetaInfo("\$('#${id}').find('li')").collect { it as Item }
        }
        support Enabled, Disabled, Available, Missing, Hidden, Visible
    }

    List<Item> getItems() {
        this.evaluator.getMetaInfo("\$('#${id}').find('li')").collect { it as Item }
    }
}
