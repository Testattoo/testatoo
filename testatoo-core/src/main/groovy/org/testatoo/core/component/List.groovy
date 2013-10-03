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
package org.testatoo.core.component

import org.testatoo.core.property.Size
import org.testatoo.core.state.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class List extends Component {

    List() {
        type Type.LIST
        support Size
        support Enabled, Disabled, Available, Missing, Hidden, Visible, Empty, Filled
    }

//    List<ListItem> items() {
//        return new ArrayList<ListItem>();
//    }

    java.util.List<ListItem> getItems() { evaluator.getMetaInfo("${id} > li").collect { it as ListItem } }

}