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
import org.testatoo.core.component.Group
import org.testatoo.core.support.property.GroupSupport
import org.testatoo.hamcrest.PropertyMatcher
import org.testatoo.hamcrest.matcher.property.dummy.DummyGroup

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class GroupMatcher extends PropertyMatcher<GroupSupport> {
    private List<String> values = new ArrayList<>()
    private List<Group> groups = new ArrayList<>()

    GroupMatcher(String... values) {
        this.values = values
    }

    GroupMatcher(Group... groups) {
        this.groups = groups
    }

    @Override
    protected boolean matchesSafely(GroupSupport component) {
        if (values) {
            groups.clear()
            values.each { groups.add(new DummyGroup(it)) }
        }
        values.clear()
        groups.each { values.add(String.valueOf(it.value())) }
        component.groups().size() == groups.size() && component.groups().containsAll(groups)
    }

    @Override
    void describeTo(Description description) {
        List<String> expectedGroups = new ArrayList<>()
        groups.each { expectedGroups.add(String.valueOf(it.value())) }

        description.appendText('group(s) ')
        description.appendValueList('[', ', ', ']', expectedGroups)
    }

    @Override
    protected void describeMismatchSafely(GroupSupport component, Description mismatchDescription) {
        List<String> componentGroups = new ArrayList<>()
        component.groups().each { componentGroups.add(String.valueOf(it.value())) }

        mismatchDescription.appendText('has group(s) ')
        mismatchDescription.appendValueList('[', ', ', ']', componentGroups)
    }
}
