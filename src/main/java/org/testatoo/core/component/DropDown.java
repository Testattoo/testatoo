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
package org.testatoo.core.component;

import org.testatoo.core.nature.LabelSupport;
import org.testatoo.core.nature.Selectable;

/**
 * This class allows the testing of a DropDown.
 * The DropDown allows the user to choose one item from a list.
 * When the dropDown list is inactive, it displays a single item.
 * When activated, it displays a list of items, from which the user may select one.
 * When the user selects a new item, the control reverts to its inactive state, displaying the selected item.
 *
 * @author David Avenante (d.avenante@gmail.com)
 */
public class DropDown extends Component implements LabelSupport, Selectable {

    public DropDown(String id) {
        super(id);
    }
}
