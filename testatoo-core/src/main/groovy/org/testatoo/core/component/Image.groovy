package org.testatoo.core.component

import org.testatoo.core.property.Source
import org.testatoo.core.state.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Image extends Component {

    Image() {
        type Type.IMAGE
        support Source
        support Enabled, Disabled, Available, Missing, Hidden, Visible
    }

}
