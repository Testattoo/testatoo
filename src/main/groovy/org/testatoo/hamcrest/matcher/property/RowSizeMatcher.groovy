package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.testatoo.core.support.property.RowSupport
import org.testatoo.hamcrest.PropertyMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class RowSizeMatcher extends PropertyMatcher<RowSupport> {
    private Integer number

    RowSizeMatcher(Integer number) {
        this.number = number
    }

    @Override
    protected boolean matchesSafely(RowSupport component) {
        component.rows().size() == number
    }

    @Override
    void describeTo(Description description) {
        description.appendText(number + ' row(s)')
    }

    @Override
    protected void describeMismatchSafely(RowSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has ' + component.rows().size()).appendText(' row(s)')
    }
}
