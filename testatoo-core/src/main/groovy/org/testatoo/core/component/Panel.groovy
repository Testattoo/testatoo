package org.testatoo.core.component

import org.testatoo.core.property.Title
import org.testatoo.core.state.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Panel extends Component {
    Panel() {
        type Type.PANEL
        support Title
        support Enabled, Disabled, Available, Missing, Hidden, Visible
    }
}
