/**
 * Copyright (C) 2016 Ovea (dev@ovea.com)
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
package org.testatoo.hamcrest

import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import org.testatoo.core.support.TextSupport

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class TextMatcher extends PropertyMatcher<TextSupport> {

    private String text

    TextMatcher(String text) {
        this.text = text
    }

    @Override
    protected boolean matchesSafely(TextSupport item) {
        item.text == text
    }

    @Override
    void describeTo(Description description) {
        description.appendValue(text)
    }

    @Override
    protected void describeMismatchSafely(TextSupport item, Description mismatchDescription) {
        mismatchDescription.appendText('was ').appendValue(item.text)
    }
}
