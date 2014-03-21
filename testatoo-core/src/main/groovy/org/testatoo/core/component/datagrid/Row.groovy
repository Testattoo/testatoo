/**
 * Copyright (C) 2013 Ovea (dev@ovea.com)
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
package org.testatoo.core.component.datagrid

import org.testatoo.core.component.Component
import org.testatoo.core.property.Size

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Row extends Component {

    Row() {
        support Size, {
            Integer.valueOf(evaluator.getString("\$('#${id}').find('td').length"))
        }
    }

    List<Cell> getCells() {
        this.evaluator.getMetaInfo("\$('#${id}').find('td')").collect { it as Cell }
    }

}
