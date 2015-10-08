/**
 * Copyright (C) 2014 Ovea (dev@ovea.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.bundle.html5.list

import org.testatoo.bundle.html5.traits.SelectTypeSupport
import org.testatoo.core.ByJs
import org.testatoo.core.Component

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@ByJs("it.is('select') && (!!it.attr('multiple') || it.prop('size') > 0)")
class ListBox extends Component implements SelectTypeSupport {

    List<Item> getItems() {
        find("option", Item)
    }

    Item item(String value) {
        items.find { it.value == value }
    }
}

//    ListBox() {
//        support Label, GroupItemsSize, VisibleItemsSize, SelectedItems, Size
//        support Items, { find("option", Item) }
//        support GroupItems, { find("optgroup", GroupItem) }
//        support MultiSelectable, SingleSelectable
//    }
//

//
//    List<GroupItem> getGroupItems() {
//        find("optgroup", GroupItem)
//    }

