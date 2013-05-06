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
class EqualsToMatcher implements Matcher {

    final Attribute attribute
    final Collection<String> expected

    EqualsToMatcher(Attribute attribute, Collection<String> expected) {
        this.attribute = attribute
        this.expected = expected
    }

    @Override
    void matches(Component c) {
        if (!c.supports(this)) {
            throw new AssertionError("Component ${this} doesn ot support attribute ${getClass().simpleName}")
        }
        String v = attribute.getValue(c)
        if (!(v in expected)) {
            if (expected.size() == 1) {
                throw new AssertionError("Expected ${attribute.class.simpleName} '${expected[0]}' but was '${v}'")
            } else {
                throw new AssertionError("Expected one of ${attribute.class.simpleName} '${expected}' but was '${v}'")
            }
        }
    }

    @Override
    String toString() { "${attribute} equals to ${expected.size() == 1 ? expected[0] : expected}" }

    static Matchers matchers(Attribute a) { new Matchers(attribute: a) }

    static class Matchers extends AttributeMatchers {
        EqualsToMatcher equalsTo(String expected) { equalsTo([expected]) }

        EqualsToMatcher equalsTo(Collection<String> anyOfExpected) { new EqualsToMatcher(attribute, anyOfExpected) }

        EqualsToMatcher equalsTo(String... anyOfExpected) { new EqualsToMatcher(attribute, Arrays.asList(anyOfExpected)) }
    }
}
