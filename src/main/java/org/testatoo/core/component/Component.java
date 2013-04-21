/**
 * Copyright (C) 2013 Ovea <dev@testatoo.org>
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
package org.testatoo.core.component;


import org.testatoo.core.property.Property;
import org.testatoo.core.state.State;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class Component {

    /**
     * The unique identifier of the component
     */
    private String id;

    public Component(String id) {
        this.id = id;
    }

    /**
     * To get the id of the component
     *
     * @return the id of the component
     */
    public String id() {
        return id;
    }

    public boolean is(State state) {
        return state.is(this);
    }

    public boolean has(Property property) {
        return property.is(this);
    }

}
