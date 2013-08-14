package org.testatoo

import org.testatoo.core.component.Component
import org.testatoo.core.component.Type
import org.testatoo.core.property.Title

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class MyPanel extends Component {
    MyPanel() {
        type Type.PANEL
        // existing property, override evaluation
        support Title, { Component c -> /*c.evaluator.execute('$(...)')*/ return 'temp2' }
        // custom property
        support MyProp
    }
}
