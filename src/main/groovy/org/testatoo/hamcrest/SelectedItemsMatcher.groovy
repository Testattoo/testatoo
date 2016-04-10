package org.testatoo.hamcrest

import org.hamcrest.Description
import org.testatoo.core.component.Dropdown

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class SelectedItemsMatcher extends PropertyMatcher<Dropdown> {

    private String[] items

    SelectedItemsMatcher(String... items) {
        this.items = items
    }

    @Override
    protected boolean matchesSafely(Dropdown item) {
        return false
    }

    @Override
    void describeTo(Description description) {

    }
}
