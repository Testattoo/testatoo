package org.testatoo.core.component

import org.testatoo.core.property.TitleProperty

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Panel extends Component {
    Panel() {
        type Type.PANEL
        support TitleProperty
    }
}
