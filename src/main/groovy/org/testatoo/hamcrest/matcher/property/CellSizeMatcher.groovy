package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.testatoo.core.support.property.CellSupport
import org.testatoo.hamcrest.PropertyMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class CellSizeMatcher extends PropertyMatcher<CellSupport> {
    private Integer number

    CellSizeMatcher(Integer number) {
        this.number = number
    }

    @Override
    protected boolean matchesSafely(CellSupport component) {
        component.cells().size() == number
    }

    @Override
    void describeTo(Description description) {
        description.appendText(number + ' cell(s)')
    }

    @Override
    protected void describeMismatchSafely(CellSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has ' + component.cells().size()).appendText(' cell(s)')
    }
}