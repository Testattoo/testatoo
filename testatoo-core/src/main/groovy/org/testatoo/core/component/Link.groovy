package org.testatoo.core.component

import org.testatoo.core.property.ReferenceProperty
import org.testatoo.core.property.TextProperty

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Link extends Component {
    Link() {
        type Type.LINK
        support TextProperty, ReferenceProperty
    }
}
