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

import org.testatoo.core.component.Component;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public abstract class State {

    protected String expected_state;
    protected String none_expected_state;

    abstract public void is(Component component);

    protected String stateMessage(Component component) {
        return "Component " + component.getClass().getSimpleName() + " with id: \"" + component.id() + "\" expected " + expected_state + " but was " + none_expected_state;
    }

    protected String invertedStateMessage(Component component) {
        return "Component " + component.getClass().getSimpleName() + " with id: \"" + component.id() + "\" expected " + none_expected_state + " but was " + expected_state;
    }
}
