/**
 * Copyright (C) 2011 Ovea <dev@ovea.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
