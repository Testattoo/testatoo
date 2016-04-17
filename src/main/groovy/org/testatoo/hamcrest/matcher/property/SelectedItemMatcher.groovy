package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.testatoo.core.support.SelectedItemSupport
import org.testatoo.hamcrest.PropertyMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class SelectedItemMatcher extends PropertyMatcher<SelectedItemSupport> {

    private String item

    SelectedItemMatcher(String item) {
        this.item = item
    }

    @Override
    protected boolean matchesSafely(SelectedItemSupport selectedItemSupport) {
        selectedItemSupport.selectedItem().value() == item
    }

    @Override
    void describeTo(Description description) {
        description.appendValue('toto')
    }

    @Override
    protected void describeMismatchSafely(SelectedItemSupport selectedItemSupport, Description mismatchDescription) {
        mismatchDescription.appendText('has ').appendValue('todo')
    }

}
