package org.testatoo.core.component.list

import org.testatoo.core.component.Component
import org.testatoo.core.component.Type
import org.testatoo.core.property.Items
import org.testatoo.core.property.Label
import org.testatoo.core.property.SelectedItems
import org.testatoo.core.property.Size
import org.testatoo.core.state.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class DropDown extends Component implements ListItem {

    DropDown() {
        type Type.DROPDOWN
        support Label, Size, SelectedItems
        support Items, {
            Component c -> c.evaluator.getMetaInfo("\$('#${id} > option')").collect { it as Item }
        }
        support Enabled, Disabled, Available, Missing, Hidden, Visible
    }

    List<Item> getItems() {
        this.evaluator.getMetaInfo("\$('#${id} > option')").collect { it as Item }
    }

}
