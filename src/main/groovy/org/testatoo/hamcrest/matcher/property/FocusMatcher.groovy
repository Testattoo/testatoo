package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.testatoo.core.support.state.FocusSupport
import org.testatoo.hamcrest.PropertyMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class FocusMatcher extends PropertyMatcher<FocusSupport> {
    @Override
    protected boolean matchesSafely(FocusSupport component) {
        component.focused()
    }

    @Override
    void describeTo(Description description) {
        description.appendText('focus')
    }

    @Override
    protected void describeMismatchSafely(FocusSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has no focus')
    }
}
