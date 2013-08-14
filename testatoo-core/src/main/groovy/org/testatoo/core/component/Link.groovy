package org.testatoo.core.component

import org.testatoo.core.property.Ref
import org.testatoo.core.property.Text
import org.testatoo.core.state.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Link extends Component {
    Link() {
        type Type.LINK
        support Text, Ref
        support Enabled, Disabled, Available, Missing, Hidden, Visible
    }
}
