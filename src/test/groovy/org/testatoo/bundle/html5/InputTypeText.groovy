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
package org.testatoo.bundle.html5

import org.testatoo.core.CssIdentifier
import org.testatoo.core.component.Component
import org.testatoo.core.support.property.ValueSupport
import org.testatoo.core.support.state.EmptySupport

import static org.testatoo.core.Testatoo.config

@CssIdentifier('input[type=text]')
class InputTypeText extends Component implements ValueSupport, EmptySupport {
    Object value() {
        config.evaluator.eval(id(), "it.val()")
    }

    void clear() {
        this.click()
        config.evaluator.runScript("\$('#${id()}').val('')")
    }

    boolean empty() {
        config.evaluator.check(id(), "\$.trim(it.val()).length == 0")
    }
}