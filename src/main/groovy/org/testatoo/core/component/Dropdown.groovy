package org.testatoo.core.component

import org.testatoo.core.Component
import org.testatoo.core.support.SingleSelector

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
abstract class Dropdown extends Component implements SingleSelector {

    abstract List<Item> getItems()

    abstract List<GroupItem> getGroupItems()

    abstract GroupItem groupItem(String value)

    abstract Item item(String value)

    abstract Item getSelectedItem()
}
