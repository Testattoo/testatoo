package org.testatoo.core.property

import org.testatoo.core.property.matcher.PropertyMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Properties {

    // Properties
    static final Placeholder placeholder = new Placeholder()

    static final Label label = new Label()
    static final PropertyMatcher label(String expected) { label.equalsTo(expected) }

    static final Text text = new Text()
    static final PropertyMatcher text(String expected) { text.equalsTo(expected) }

    static final Value value = new Value()
    static final PropertyMatcher value(String expected) { value.equalsTo(expected) }

    static final Reference reference = new Reference()
    static final PropertyMatcher reference(String expected) { reference.equalsTo(expected) }

    static final Title title = new Title()
    static final PropertyMatcher title(String expected) { title.equalsTo(expected) }

    static final Items items = new Items()
    static final PropertyMatcher items(String... expected) { items.equalsTo(expected) }

    static final GroupItems groupItems = new GroupItems()
    static final PropertyMatcher groupItems(String... expected) { groupItems.equalsTo(expected) }
    static final GroupItemsSize groupItemsSize = new GroupItemsSize()

    static final VisibleItemsSize visibleItemsSize = new VisibleItemsSize()

    static final Size size = new Size()

    static final SelectedItems selectedItems = new SelectedItems()
    static final PropertyMatcher selectedItems(String... expected) { selectedItems.equalsTo(expected) }
}
