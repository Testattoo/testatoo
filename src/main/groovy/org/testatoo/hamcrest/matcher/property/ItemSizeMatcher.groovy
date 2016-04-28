package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.testatoo.core.support.ItemSupport
import org.testatoo.hamcrest.PropertyMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class ItemSizeMatcher extends PropertyMatcher<ItemSupport> {

    private Integer number

    ItemSizeMatcher(Integer number) {
        this.number = number
    }

    @Override
    protected boolean matchesSafely(ItemSupport component) {
        component.items().size() == number
    }

    @Override
    void describeTo(Description description) {
        description.appendText(number + ' item(s)')
    }

    @Override
    protected void describeMismatchSafely(ItemSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has ' + component.items().size()).appendText(' item(s)')
    }
}
