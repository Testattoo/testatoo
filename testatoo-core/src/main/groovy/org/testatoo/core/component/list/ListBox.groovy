package org.testatoo.core.component.list

import org.testatoo.core.component.Component
import org.testatoo.core.component.Type
import org.testatoo.core.property.GroupItems
import org.testatoo.core.property.GroupItemsSize
import org.testatoo.core.property.Items
import org.testatoo.core.property.Label
import org.testatoo.core.property.SelectedItems
import org.testatoo.core.property.Size
import org.testatoo.core.property.VisibleItemsSize
import org.testatoo.core.state.Available
import org.testatoo.core.state.Disabled
import org.testatoo.core.state.Enabled
import org.testatoo.core.state.Hidden
import org.testatoo.core.state.Missing
import org.testatoo.core.state.MultiSelectable
import org.testatoo.core.state.SingleSelectable
import org.testatoo.core.state.Visible

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class ListBox extends Component {

    ListBox() {
        type Type.LISTBOX
        support Label, Size, GroupItemsSize, VisibleItemsSize, SelectedItems
        support Items, {
            Component c -> c.evaluator.getMetaInfo("\$('#${id}').find('option')").collect { it as Item }
        }
        support GroupItems, {
            Component c -> c.evaluator.getMetaInfo("\$('#${id}').find('optgroup')").collect { it as GroupItem }
        }
        support Enabled, Disabled, Available, Missing, Hidden, Visible, MultiSelectable, SingleSelectable
    }

    List<Item> getItems() {
        this.evaluator.getMetaInfo("\$('#${id}').find('option')").collect { it as Item }
    }

    List<GroupItem> getGroupItems() {
        this.evaluator.getMetaInfo("\$('#${id}').find('optgroup')").collect { it as GroupItem }
    }

}
