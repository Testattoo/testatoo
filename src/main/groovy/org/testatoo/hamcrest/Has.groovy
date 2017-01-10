/**
 * Copyright © 2016 Ovea (d.avenante@gmail.com)
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
package org.testatoo.hamcrest

import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Has<T> extends BaseMatcher<T> {
    private final Matcher<T> matcher

    Has(Matcher<T> matcher) {
        this.matcher = matcher
    }

    @Override
    boolean matches(Object item) {
        return matcher.matches(item)
    }

    @Override
    void describeTo(Description description) {
        description.appendText("has ").appendDescriptionOf(matcher)
    }

    @Override
    void describeMismatch(Object item, Description mismatchDescription) {
        matcher.describeMismatch(item, mismatchDescription)
    }
}