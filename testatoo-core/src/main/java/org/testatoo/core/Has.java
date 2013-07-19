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
import org.testatoo.core.property.Property;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class Has {

    private final Property property;
    private final Component component;

    public Has(Property property, Component component) {
        this.property = property;
        this.component = component;
    }

    public Matcher equalsTo(final Object expected_value) {
        return new Matcher() {
            @Override
            public void matches() {
                if (property.value(component).equals(expected_value))
                    return;
                throw new AssertionError("Expected " + property.property() + ": \"" + expected_value + "\" but was: \"" + property.value(component) + "\"");
            }
        };
    }

    public Matcher contains(final String expected_value) {
        return new Matcher() {
            @Override
            public void matches() {
                if (property.value(component).contains(expected_value))
                    return;
                throw new AssertionError("Expected " + property.property() + " contains: \"" + expected_value + "\" but was: \"" + property.value(component) + "\"");
            }
        };
    }

    public Matcher matches(final String expression) {
        return new Matcher() {
            @Override
            public void matches() {
                if (property.value(component).matches(expression))
                    return;
                throw new AssertionError("Expected " + property.property() + " matches: \"" + expression + "\" but was: \"" + property.value(component) + "\"");
            }
        };
    }

}
