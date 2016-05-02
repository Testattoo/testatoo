package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.testatoo.core.support.property.ColumnSupport
import org.testatoo.hamcrest.PropertyMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class ColumnSizeMatcher extends PropertyMatcher<ColumnSupport> {
    private Integer number

    ColumnSizeMatcher(Integer number) {
        this.number = number
    }

    @Override
    protected boolean matchesSafely(ColumnSupport component) {
        component.columns().size() == number
    }

    @Override
    void describeTo(Description description) {
        description.appendText(number + ' column(s)')
    }

    @Override
    protected void describeMismatchSafely(ColumnSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has ' + component.columns().size()).appendText(' column(s)')
    }
}
