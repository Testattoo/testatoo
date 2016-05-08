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
package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.testatoo.core.support.property.TitleSupport
import org.testatoo.hamcrest.PropertyMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class TitleMatcher extends PropertyMatcher<TitleSupport> {
    private String title

    TitleMatcher(String title) {
        this.title = title
    }

    @Override
    protected boolean matchesSafely(TitleSupport component) {
        component.title() == title
    }

    @Override
    void describeTo(Description description) {
        description.appendText('title ').appendValue(title)
    }

    @Override
    protected void describeMismatchSafely(TitleSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has title ').appendValue(component.title())
    }
}