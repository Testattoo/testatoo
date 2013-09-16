package experimental.dsl.todo

import org.testatoo.core.component.Component
import org.testatoo.core.component.Type
import org.testatoo.core.property.Title
import org.testatoo.core.state.Available
import org.testatoo.core.state.Enabled

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class MyPanel extends Component {
    MyPanel() {
        type Type.PANEL

        // existing property, override evaluation
        support Title, { Component c -> /*c.evaluator.execute('$(...)')*/ return 'temp2' }

        support Enabled

        // overriden state evaluation
        support Available, { Component c -> return true }

        // properties and states and custom ones
        support experimental.dsl.todo.MyProp, experimental.dsl.todo.MyState, Enabled
    }
}
