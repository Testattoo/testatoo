package org.testatoo.core.component

import org.testatoo.core.Component

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
abstract class ListView extends Component {

    abstract List<Item> getItems()

    abstract Item item(String value)
}
