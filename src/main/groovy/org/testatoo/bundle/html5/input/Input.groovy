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
package org.testatoo.bundle.html5.input

import org.testatoo.core.Component
import org.testatoo.core.action.Fill
import org.testatoo.core.action.Reset
import org.testatoo.core.action.support.Fillable
import org.testatoo.core.action.support.Resettable
import org.testatoo.core.property.Label
import org.testatoo.core.property.Placeholder
import org.testatoo.core.property.Value
import org.testatoo.core.state.*

import static org.testatoo.core.input.Key.BACK_SPACE

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class Input extends Component implements Resettable, Fillable {

    Input() {
        support Placeholder, Label, Optional, Required, Empty, Filled, Valid, Invalid, Reset, Fill
        support Value, "it.val()"
    }

    @Override
    void reset() {
        evaluator.runScript("\$('#${id}').val(' ').change()")
        evaluator.enter([BACK_SPACE])
    }
}
