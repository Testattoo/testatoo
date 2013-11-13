package org.testatoo.core.component.list

import org.testatoo.core.component.Component
import org.testatoo.core.component.Type
import org.testatoo.core.property.GroupItems
import org.testatoo.core.property.GroupItemsSize
import org.testatoo.core.property.Items
import org.testatoo.core.property.Label
import org.testatoo.core.property.SelectedItems
import org.testatoo.core.property.Size
import org.testatoo.core.state.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class DropDown extends Component {

    DropDown() {
        type Type.DROPDOWN
        support Label, Size, GroupItemsSize, SelectedItems
        support Items, {
            Component c -> c.evaluator.getMetaInfo("\$('#${id}').find('option')").collect { it as Item }
        }
        support GroupItems, {
            Component c -> c.evaluator.getMetaInfo("\$('#${id}').find('optgroup')").collect { it as GroupItem }
        }
        support Enabled, Disabled, Available, Missing, Hidden, Visible
    }

    List<Item> getItems() {
        this.evaluator.getMetaInfo("\$('#${id}').find('option')").collect { it as Item }
    }

    List<GroupItem> getGroupItems() {
        this.evaluator.getMetaInfo("\$('#${id}').find('optgroup')").collect { it as GroupItem }
    }

}
