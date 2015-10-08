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

import org.testatoo.core.ByCss
import org.testatoo.core.Component
import org.testatoo.core.property.Properties

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@ByCss('optgroup')
class GroupItem extends Component {

//    GroupItem() {
//        support Value, { eval("it.attr('label')") }
//        support Size
//        support Items, { find("option", Item) }
//    }

    List<Item> getItems() {
        find("option", Item)
    }

    Item item(String _value) {
        items.find { it.has(Properties.value) == _value }
    }

    boolean equals(o) {
        if (this.is(o)) return true
        return value == o
    }

    String getValue() {
        this.eval("it.attr('label')")
    }

    @Override
    String toString() {
        return value
    }

}
