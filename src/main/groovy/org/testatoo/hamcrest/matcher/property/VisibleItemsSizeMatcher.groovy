package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.testatoo.core.support.VisibleItemsSupport
import org.testatoo.hamcrest.PropertyMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class VisibleItemsSizeMatcher extends PropertyMatcher<VisibleItemsSupport> {
    private Integer number

    VisibleItemsSizeMatcher(Integer number) {
        this.number = number
    }

    @Override
    protected boolean matchesSafely(VisibleItemsSupport component) {
        component.visibleItems().size() == number
    }

    @Override
    void describeTo(Description description) {
        description.appendText(number + ' visible item(s)')
    }

    @Override
    protected void describeMismatchSafely(VisibleItemsSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has ' + component.visibleItems().size()).appendText(' visible item(s)')
    }
}
