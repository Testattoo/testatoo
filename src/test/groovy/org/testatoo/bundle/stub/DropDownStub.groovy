/**
 * Copyright © 2018 Ovea (d.avenante@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.bundle.stub

import org.testatoo.core.CssIdentifier
import org.testatoo.core.component.Dropdown
import org.testatoo.core.component.Group
import org.testatoo.core.component.Item

@CssIdentifier('DropDownStub')
class DropDownStub extends Dropdown {
    List<Group> groups() {
        return null
    }

    Group group(String value) {
        return null
    }

    List<Item> items() {
        return null
    }

    Item item(String value) {
        return null
    }

    String label() {
        return 'DropDown Label'
    }

    Item selectedItem() {
        return null
    }

    boolean empty() {
        return false
    }
}
