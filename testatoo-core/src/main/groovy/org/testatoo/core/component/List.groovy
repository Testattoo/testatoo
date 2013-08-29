package org.testatoo.core.component

import org.testatoo.core.property.Size
import org.testatoo.core.state.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class List extends Component {

    List() {
        type Type.LIST
        support Size
        support Enabled, Disabled, Available, Missing, Hidden, Visible
    }
}
