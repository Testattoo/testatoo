package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.testatoo.core.support.GroupSupport
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

