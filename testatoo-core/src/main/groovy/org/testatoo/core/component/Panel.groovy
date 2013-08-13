package org.testatoo.core.component

import org.testatoo.core.property.TitleProperty

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Panel extends Component {

    Panel() {
        supportedProperties << TitleProperty
    }

    @Override
    ComponentType getType() { ComponentType.BUTTON }
}
