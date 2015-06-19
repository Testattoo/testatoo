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
package org.testatoo.core.property.matcher

import org.testatoo.bundle.html5.components.Component
import org.testatoo.core.property.Property

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class EqualsToListMatcher extends AbstractPropertyMatcher {

    final Collection<?> expected

    EqualsToListMatcher(Property property, Collection<?> expected) {
        super(property)
        this.expected = expected
    }

    @Override
    void doMatch(Component c, Object currentValue) {
        if (!(currentValue == expected)) {
            throw new AssertionError("Expected ${property.class.simpleName} '${expected}' but was '${currentValue}'")
        }
    }

    @Override
    String toString() { "${property} equals to ${expected.size() == 1 ? expected[0] : expected}" }

    static Matchers matchers(Property a) { new Matchers(property: a) }

    static class Matchers extends PropertyMatchers {

        EqualsToListMatcher equalsTo(Collection<?> items) { new EqualsToListMatcher(property, items) }

        EqualsToListMatcher equalsTo(Object... items) { new EqualsToListMatcher(property, Arrays.asList(items)) }

    }

}
