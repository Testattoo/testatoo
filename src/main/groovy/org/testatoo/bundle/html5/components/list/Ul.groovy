package org.testatoo.bundle.html5.components.list

import org.testatoo.core.ByCss
import org.testatoo.core.component.ListView

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@ByCss('ul')
class Ul extends ListView {

    @Override
    List<Li> getItems() {
        find('li', Li)
    }

    @Override
    Li item(String value) {
        items.find { it.value == value }
    }

}
