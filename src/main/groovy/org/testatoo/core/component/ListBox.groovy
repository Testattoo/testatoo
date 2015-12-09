package org.testatoo.core.component

import org.testatoo.core.Component
import org.testatoo.core.support.MultiSelector

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
abstract class ListBox extends Component implements MultiSelector {

    abstract List<Item> getItems()

    abstract Item item(String value)

    abstract List<Item> getVisibleItems()

    abstract List<GroupItem> getGroupItems()

    abstract GroupItem groupItem(String value)

    abstract List<Item> getSelectedItems()

}
