package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.testatoo.core.support.property.ReferenceSupport
import org.testatoo.hamcrest.PropertyMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class ReferenceMatcher extends PropertyMatcher<ReferenceSupport> {
    String reference

    ReferenceMatcher(String reference) {
        this.reference = reference
    }

    @Override
    protected boolean matchesSafely(ReferenceSupport component) {
        component.reference() == reference
    }

    @Override
    void describeTo(Description description) {
        description.appendText(reference)
    }

    @Override
    protected void describeMismatchSafely(ReferenceSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has ' + component.reference())
    }
}
