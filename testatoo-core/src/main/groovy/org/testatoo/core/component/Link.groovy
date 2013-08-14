package org.testatoo.core.component

import org.testatoo.core.property.Text

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Link extends Component {
    Link() {
        type Type.LINK
        support Text, org.testatoo.core.property.Reference
    }
}
