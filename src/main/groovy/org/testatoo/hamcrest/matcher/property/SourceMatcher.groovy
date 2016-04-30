package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.testatoo.core.support.property.SourceSupport
import org.testatoo.hamcrest.PropertyMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class SourceMatcher extends PropertyMatcher<SourceSupport> {
    String value

    SourceMatcher(String value) {
        this.value = value
    }

    @Override
    protected boolean matchesSafely(SourceSupport item) {
        return false
    }

    @Override
    void describeTo(Description description) {

    }
}
