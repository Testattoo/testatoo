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
package org.testatoo.core.property.matcher

import org.testatoo.core.component.Component
import org.testatoo.core.property.Property

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
abstract class AbstractPropertyMatcher implements PropertyMatcher {

    final Property property

    AbstractPropertyMatcher(Property property) {
        this.property = property
    }

    @Override
    final void matches(Component c) {
        if (!c.supports(property)) {
            throw new AssertionError("Component ${this} does not support property ${getClass().simpleName}")
        }
        String v = property.getValue(c)
        doMatch(c, v)
    }

    abstract void doMatch(Component c, String currentValue)
}
