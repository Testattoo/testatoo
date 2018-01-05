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
package org.testatoo.bundle.stub

import org.testatoo.core.CssIdentifier
import org.testatoo.core.component.CheckBox

@CssIdentifier('CheckBoxStub')
class CheckBoxStub extends CheckBox {
    void check() {
    }

    void uncheck() {
    }

    String label() {
        return 'Checkbox Label'
    }

    boolean checked() {
        return false
    }
}