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
package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.testatoo.core.support.property.GroupSupport
import org.testatoo.hamcrest.PropertyMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class GroupSizeMatcher extends PropertyMatcher<GroupSupport> {
    private Integer number

    GroupSizeMatcher(Integer number) {
        this.number = number
    }

    @Override
    protected boolean matchesSafely(GroupSupport component) {
        component.groups().size() == number
    }

    @Override
    void describeTo(Description description) {
        description.appendText(number + ' group(s)')
    }

    @Override
    protected void describeMismatchSafely(GroupSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has ' + component.groups().size()).appendText(' group(s)')
    }
}

