package org.testatoo.core.component.list

import org.testatoo.core.component.Component
import org.testatoo.core.component.Type
import org.testatoo.core.state.Available
import org.testatoo.core.state.Disabled
import org.testatoo.core.state.Enabled
import org.testatoo.core.state.Hidden
import org.testatoo.core.state.Missing
import org.testatoo.core.state.Visible

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class ListBox extends Component {
    ListBox() {
        type Type.LISTBOX
        support Items
        support Enabled, Disabled, Available, Missing, Hidden, Visible
    }
}
