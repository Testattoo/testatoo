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
package org.testatoo.core

import org.testatoo.core.component.Component
import org.testatoo.core.property.matcher.PropertyMatcher
import org.testatoo.core.state.State

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Assertions<T extends Component> {

    final Components<T> components

    Assertions(Components<T> cs) {
        this.components = cs;
    }

    void are(State matcher) {
        components.each { assert it.is(matcher) }
    }

    void have(PropertyMatcher matcher) {
        components.each { assert it.has(matcher) }
    }

}
