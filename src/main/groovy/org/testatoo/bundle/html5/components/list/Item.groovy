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
package org.testatoo.bundle.html5.components.list

import org.testatoo.core.ByCss
import org.testatoo.core.Component
import org.testatoo.core.property.Label
import org.testatoo.core.property.Value
import org.testatoo.core.state.Disabled
import org.testatoo.core.state.Enabled
import org.testatoo.core.state.Selected
import org.testatoo.core.state.Unselected

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@ByCss('option,li')
class Item extends Component {

    Item() {
        support Label, "it.attr('label')"
        support Value, "it.text().trim()"
        support Disabled, { check "el.is(':disabled') || el.attr('disabled') != undefined || el.closest('select').is(':disabled');" }
        support Enabled, { check "!el.is(':disabled') || el.attr('disabled') == undefined || !el.closest('select').is(':disabled');" }
        support Selected, Unselected
    }

    boolean equals(o) {
        if (this.is(o)) return true
        return value == o
    }

    String getValue() {
        return eval("it.text().trim()")
    }

    @Override
    String toString() {
        return value
    }

}
