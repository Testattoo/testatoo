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
package org.testatoo.experimental.dsl

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-04
 */
class ContainingMatcher implements Matcher {

    final Attribute attribute
    final Collection<String> contained

    ContainingMatcher(Attribute attribute, Collection<String> contained) {
        this.attribute = attribute
        this.contained = contained
    }

    @Override
    void matches(Component c) {
        String v = attribute.getValue(c)
        if (!contained.find { v.contains(it) }) {
            if (contained.size() == 1) {
                throw new AssertionError("Expected ${attribute.class.simpleName} containing '${contained[0]}' but was '${v}'")
            } else {
                throw new AssertionError("Expected one of ${attribute.class.simpleName} containing '${contained}' but was '${v}'")
            }
        }
    }

    @Override
    String toString() { "${attribute} contains ${contained.size() == 1 ? contained[0] : contained}" }

    static Matchers matchers(Attribute a) { new Matchers(attribute: a) }

    static class Matchers extends AttributeMatchers {
        ContainingMatcher containing(String expected) { containing([expected]) }

        ContainingMatcher containing(Collection<String> anyOfExpected) { new ContainingMatcher(attribute, anyOfExpected) }

        ContainingMatcher containing(String... anyOfExpected) { new ContainingMatcher(attribute, Arrays.asList(anyOfExpected)) }
    }
}
