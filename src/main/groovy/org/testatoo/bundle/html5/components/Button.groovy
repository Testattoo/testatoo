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
package org.testatoo.bundle.html5.components

import org.testatoo.core.Component
import org.testatoo.core.IdentifiedByCss
import org.testatoo.core.property.Text

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
@IdentifiedByCss('button,input[type=submit],input[type=button],input[type=reset],input[type=image]')
class Button extends Component {

    Button() {
        support Text, "it.is('input') ? it.val() : it.text().trim()"
    }

}
