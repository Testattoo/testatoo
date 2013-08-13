package org.testatoo.core.component

import org.testatoo.core.property.ReferenceProperty
import org.testatoo.core.property.TextProperty

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Link extends Component {

    Link() {
        supportedProperties << TextProperty << ReferenceProperty
    }

    @Override
    ComponentType getType() { ComponentType.LINK }
}
