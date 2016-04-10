package org.testatoo.hamcrest

import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Has<T> extends BaseMatcher<T> {

    private final Matcher<T> matcher;

    public Has(Matcher<T> matcher) {
        this.matcher = matcher;
    }

    @Override
    boolean matches(Object item) {
        return matcher.matches(item);
    }

    @Override
    void describeTo(Description description) {
        description.appendText("has ").appendDescriptionOf(matcher);
    }

    @Override
    public void describeMismatch(Object item, Description mismatchDescription) {
        matcher.describeMismatch(item, mismatchDescription);
    }
}
