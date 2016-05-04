package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.testatoo.core.support.property.TitleSupport
import org.testatoo.hamcrest.PropertyMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class TitleMatcher extends PropertyMatcher<TitleSupport> {
    private String title

    TitleMatcher(String title) {
        this.title = title
    }

    @Override
    protected boolean matchesSafely(TitleSupport component) {
        component.title() == title
    }

    @Override
    void describeTo(Description description) {
        description.appendText('title ').appendValue(title)
    }

    @Override
    protected void describeMismatchSafely(TitleSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has title ').appendValue(component.title())
    }
}