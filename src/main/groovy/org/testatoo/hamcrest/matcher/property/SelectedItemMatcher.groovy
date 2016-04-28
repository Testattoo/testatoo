package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.testatoo.core.component.Item
import org.testatoo.core.support.SelectedItemSupport
import org.testatoo.hamcrest.PropertyMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class SelectedItemMatcher extends PropertyMatcher<SelectedItemSupport> {

    private String value
    private Item item

    SelectedItemMatcher(String value) {
        this.value = value
    }

    SelectedItemMatcher(Item item) {
        this.item = item
    }

    @Override
    protected boolean matchesSafely(SelectedItemSupport component) {
        if(value) {
            return component.selectedItem().value() == value
        }
        component.selectedItem() == item
    }

    @Override
    void describeTo(Description description) {
        description.appendText('selected item ').appendValue(value ? value : item.value())
    }

    @Override
    protected void describeMismatchSafely(SelectedItemSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has selected item ').appendValue(component.selectedItem().value())
    }
}
