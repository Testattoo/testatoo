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

import org.testatoo.core.ComponentException
import org.testatoo.core.support.CheckSupport
import org.testatoo.core.support.LabelSupport
import org.testatoo.core.ByCss
import org.testatoo.core.Component

import static org.testatoo.bundle.html5.components.helper.LabelHelper.*
import static org.testatoo.bundle.html5.components.helper.CheckHelper.*

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
@ByCss('input[type=radio]')
class Radio extends Component implements LabelSupport, CheckSupport {

    @Override
    boolean isChecked() {
        isChecked(this)
    }

    @Override
    boolean isUnchecked() {
        isUnchecked(this)
    }

    @Override
    void check() {
        check(this)
    }

    @Override
    void uncheck() {
        throw new ComponentException("${this.class.simpleName} ${this} cannot be unchecked (not supported)")
    }

    @Override
    String getLabel() {
        getLabel(this)
    }
}
