package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.testatoo.core.component.Group
import org.testatoo.core.component.Item
import org.testatoo.core.support.GroupSupport
import org.testatoo.hamcrest.PropertyMatcher

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
            values.each { groups.add(new InternalGroup(it)) }
        }
        values.clear()
        groups.each { values.add(String.valueOf(it.value())) }
        component.groups().size() == groups.size() && component.groups().containsAll(groups)
    }

    @Override
    void describeTo(Description description) {
        List<String> expectedGroups = new ArrayList<>()
        groups.each { expectedGroups.add(String.valueOf(it.value())) }

        description.appendText('group(s) [')
        description.appendText(expectedGroups.join(', '))
        description.appendText(']')
    }

    @Override
    protected void describeMismatchSafely(GroupSupport component, Description mismatchDescription) {
        List<String> componentGroups = new ArrayList<>()
        component.groups().each { componentGroups.add(String.valueOf(it.value())) }

        mismatchDescription.appendText('has group(s) [')
        mismatchDescription.appendText(componentGroups.join(', '))
        mismatchDescription.appendText(']')
    }

    private class InternalGroup extends Group {

        String value

        InternalGroup(String value) {
            this.value = value
        }

        @Override
        List<Item> items() {
            return null
        }

        @Override
        Item item(String value) {
            return null
        }

        @Override
        String value() {
            return value
        }
    }
}
