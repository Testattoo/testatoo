package org.testatoo.core.support

import org.testatoo.bundle.html5.components.list.Item

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
interface SingleSelector {

    void select(Item item)

    void select(String value)
}