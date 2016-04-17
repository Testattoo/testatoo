package org.testatoo.core.support

import org.testatoo.core.component.Item

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
interface ItemSupport {

    List<Item> items()

    Item item(String value)
}