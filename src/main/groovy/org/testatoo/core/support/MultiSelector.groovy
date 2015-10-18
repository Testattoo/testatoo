package org.testatoo.core.support

import org.testatoo.bundle.html5.components.list.Item

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
interface MultiSelector {

    void select(Item... items)

    void select(String... values)
}