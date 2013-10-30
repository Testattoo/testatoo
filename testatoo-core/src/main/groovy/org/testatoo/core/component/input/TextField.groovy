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
package org.testatoo.core.component.input

import org.testatoo.core.component.Type
import org.testatoo.core.component.input.Input
import org.testatoo.core.property.Label
import org.testatoo.core.property.Placeholder

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class TextField extends Input {
    TextField() {
        type Type.TEXTFIELD
        support Placeholder, Label
    }

    void enter(String text) { evaluator.evalScript("testatoo.ext.type('${id}', '${text}')") }
}
