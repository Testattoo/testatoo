package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.testatoo.core.support.property.LengthSupport
import org.testatoo.hamcrest.PropertyMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class LengthMatcher  extends PropertyMatcher<LengthSupport> {
    private Object length

    LengthMatcher(Object length) {
        this.length = length
    }

    @Override
    protected boolean matchesSafely(LengthSupport component) {
        component.length() == length
    }

    @Override
    void describeTo(Description description) {
        description.appendText('length ' + length.toString())
    }

    @Override
    protected void describeMismatchSafely(LengthSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has length ' + component.length().toString())
    }
}