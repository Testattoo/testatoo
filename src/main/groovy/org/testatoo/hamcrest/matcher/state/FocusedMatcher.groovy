package org.testatoo.hamcrest.matcher.state

import org.hamcrest.Description
import org.testatoo.core.support.state.FocusSupport
import org.testatoo.hamcrest.StateMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class FocusedMatcher extends StateMatcher<FocusSupport> {
    @Override
    protected boolean matchesSafely(FocusSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('is not focused')
        component.focused()
    }

    @Override
    void describeTo(Description description) {
        description.appendText('focused')
    }
}