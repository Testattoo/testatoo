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
package org.testatoo.core.support.property

import org.testatoo.core.support.Clearable
import org.testatoo.core.support.state.EmptySupport
import org.testatoo.core.support.state.ReadOnlySupport
import org.testatoo.core.support.state.RequiredSupport

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
interface InputSupport extends Clearable, EmptySupport, ReadOnlySupport, RequiredSupport {
    String placeholder()

    void value(Object value)
}
