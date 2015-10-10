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

import org.testatoo.bundle.html5.traits.SelectSupport
import org.testatoo.bundle.html5.traits.ValueSupport
import org.testatoo.core.ByCss
import org.testatoo.core.Component

import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@ByCss('option,li')
class Item extends Component implements SelectSupport, ValueSupport {

    String getValue() {
        config.evaluator.eval(id, "it.attr('label') && it.attr('label').length > 0 ? it.attr('label') : it.text().trim()")
    }

    @Override
    boolean isEnabled() {
        !disabled
    }

    @Override
    boolean isDisabled() {
        config.evaluator.getBool(id, "el.is(':disabled') || el.attr('disabled') != undefined || el.closest('select').is(':disabled')")
    }

    boolean equals(o) {
        if (this.is(o)) return true
        return value == o.value
    }

    @Override
    String toString() {
        return value
    }
}