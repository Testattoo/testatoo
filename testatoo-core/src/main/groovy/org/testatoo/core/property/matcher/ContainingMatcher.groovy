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

import org.testatoo.core.property.Property
import org.testatoo.core.component.Component

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class ContainingMatcher extends AbstractPropertyMatcher {

    final Collection<String> contained

    ContainingMatcher(Property property, Collection<String> contained) {
        super(property)
        this.contained = contained
    }

    @Override
    void doMatch(Component c, String currentValue) {
        if (!contained.find { currentValue.contains(it) }) {
            if (contained.size() == 1) {
                throw new AssertionError("Expected ${property.class.simpleName} containing '${contained[0]}' but was '${currentValue}'")
            } else {
                throw new AssertionError("Expected one of ${property.class.simpleName} containing '${contained}' but was '${currentValue}'")
            }
        }
    }

    @Override
    String toString() { "${property} contains ${contained.size() == 1 ? contained[0] : contained}" }

    static Matchers matchers(Property a) { new Matchers(property: a) }

    static class Matchers extends PropertyMatchers {

        ContainingMatcher containing(String expected) { containing([expected]) }

        ContainingMatcher containing(Collection<String> anyOfExpected) { new ContainingMatcher(property, anyOfExpected) }

        ContainingMatcher containing(String... anyOfExpected) { new ContainingMatcher(property, Arrays.asList(anyOfExpected)) }
    }
}
