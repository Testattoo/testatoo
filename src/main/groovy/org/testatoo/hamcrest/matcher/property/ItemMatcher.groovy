package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.testatoo.core.component.Item
import org.testatoo.core.support.ItemSupport
import org.testatoo.hamcrest.PropertyMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class ItemMatcher extends PropertyMatcher<ItemSupport> {

    private List<String> values = new ArrayList<>()
    private List<Item> items = new ArrayList<>()

    ItemMatcher(String... values) {
        this.values = values
    }

    ItemMatcher(Item... items) {
        this.items = items
    }

    @Override
    protected boolean matchesSafely(ItemSupport component) {
        if (values) {
            items.clear()
            values.each { items.add(new InternalItem(it)) }
        }
        values.clear()
        items.each { values.add(String.valueOf(it.value())) }
        component.items().size() == items.size() && component.items().containsAll(items)
    }

    @Override
    void describeTo(Description description) {
        List<String> expectedItems = new ArrayList<>()
        items.each { expectedItems.add(String.valueOf(it.value())) }

        description.appendText('item(s) [')
        description.appendText(expectedItems.join(', '))
        description.appendText(']')
    }

    @Override
    protected void describeMismatchSafely(ItemSupport component, Description mismatchDescription) {
        List<String> componentItems = new ArrayList<>()
        component.items().each { componentItems.add(String.valueOf(it.value())) }

        mismatchDescription.appendText('has item(s) [')
        mismatchDescription.appendText(componentItems.join(', '))
        mismatchDescription.appendText(']')
    }

    private class InternalItem extends Item {

        String value

        InternalItem(String value) {
            this.value = value
        }

        @Override
        boolean selected() {
            return false
        }

        @Override
        boolean unselected() {
            return false
        }

        @Override
        void select() {

        }

        @Override
        void unselect() {

        }

        @Override
        Object value() {
            return value
        }
    }
}
