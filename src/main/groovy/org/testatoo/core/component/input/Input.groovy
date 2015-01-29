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
package org.testatoo.core.component.input

import org.testatoo.core.component.Component
import org.testatoo.core.property.Text
import org.testatoo.core.property.Value
import org.testatoo.core.state.Empty
import org.testatoo.core.state.Filled
import org.testatoo.core.state.Invalid
import org.testatoo.core.state.Valid

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class Input extends Component {

    Input() {
        support Text, Value, Empty, Filled, Valid, Invalid
    }

    void enter(String text) { evaluator.enter([text]) }

    void reset() {
        evaluator.runScript("\$('#${id}').val('')")
    }

}
