/**
 * Copyright (C) 2008 Ovea <dev@ovea.com>
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
package org.testatoo.core.state;

import org.testatoo.core.EvaluatorHolder;
import org.testatoo.core.component.Component;
import org.testatoo.core.nature.Checkable;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class Checked extends State {

    public Checked() {
        expected_state = "checked";
        none_expected_state = "unchecked";
    }

    @Override
    public void check(Component component) {
        if (component instanceof Checkable) {
            if (!EvaluatorHolder.get().isChecked((Checkable) component)) {
                throw new AssertionError(stateMessage(component));
            }
            return;
        }
        throw new AssertionError("The component is not Checkable");
    }
}
