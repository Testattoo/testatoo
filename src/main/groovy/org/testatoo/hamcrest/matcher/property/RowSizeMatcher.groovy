package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.testatoo.core.support.property.RowSupport
import org.testatoo.hamcrest.PropertyMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class RowSizeMatcher extends PropertyMatcher<RowSupport> {
    @Override
    protected boolean matchesSafely(RowSupport item) {
        return false
    }

    @Override
    void describeTo(Description description) {

    }
}
