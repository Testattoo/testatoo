package org.testatoo.core.component.list

import org.testatoo.core.component.Component
import org.testatoo.core.component.Type
import org.testatoo.core.property.Items
import org.testatoo.core.state.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class DropDown extends Component {
    DropDown() {
        type Type.DROPDOWN
//        support Items, { ['temp2', 'temps2'] }
        support Enabled, Disabled, Available, Missing, Hidden, Visible
    }
}
