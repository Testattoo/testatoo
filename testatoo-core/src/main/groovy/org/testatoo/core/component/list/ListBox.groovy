/**
 * Copyright (C) 2008 Ovea <dev@ovea.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
