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
package org.testatoo.core.component;

import org.testatoo.core.evaluator.EvaluatorHolder;
import org.testatoo.core.Has;
import org.testatoo.core.Is;
import org.testatoo.core.Matcher;
import org.testatoo.core.property.Property;
import org.testatoo.core.state.State;
import org.testatoo.core.component.IdSupport;

/**
 * This is the base class for all type of graphic components.
 *
 * @author David Avenante (d.avenante@gmail.com)
 */
public class Component implements IdSupport {

    private static ThreadLocal<Component> it = new ThreadLocal<Component>();

    public static Component it() {
        return it.get();
    }

    /**
     * The unique identifier of the component
     */
    private String id;

    public Component(String id) {
        this.id = id;
        it.set(this);
    }

    /**
     * To get the id of the component
     *
     * @return the id of the component
     */
    public String id() {
        return id;
    }

    @Override
    public String getId() {
        return id();
    }

    public Is is(State state) {
        return new Is(state, this);
    }

    public Has has(Property property) {
        return new Has(property, this);
    }

    // TODO user a State matcher
    public Matcher contains(final Component... components) {
        final Component current = this;
        return new Matcher() {
            @Override
            public void matches() {
                for (Component component : components) {
                    if (!EvaluatorHolder.get().contains(current, component)) {
                        throw new AssertionError("Component " + current.getClass().getSimpleName() + " with id: \"" + current.id() + "\" does no contains component " + component.getClass().getSimpleName() + " with id: \"" + component.id() + "\"");
                    }
                }
            }
        };
    }

    public Matcher displays(final Component... components) {
        final Component current = this;
        return new Matcher() {
            @Override
            public void matches() {
                contains(components);
                for (Component component : components) {
                    if (!EvaluatorHolder.get().isVisible(component)) {
                        throw new AssertionError("Component " + current.getClass().getSimpleName() + " with id: \"" + current.id() + "\" does no displays component " + component.getClass().getSimpleName() + " with id: \"" + component.id() + "\"");
                    }
                }
            }
        };
    }

}
