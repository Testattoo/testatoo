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
package org.testatoo.core;

import org.testatoo.core.component.Component;
import org.testatoo.core.state.State;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class Is implements Block {

    private final State state;
    private final Component component;

    public Is(State state, Component component) {
        this.state = state;
        this.component = component;
    }

    @Override
    public Component execute() {
        state.check(component);
        return component;
    }
}
