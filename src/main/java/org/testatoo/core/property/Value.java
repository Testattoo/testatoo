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
package org.testatoo.core.property;

import org.testatoo.core.EvaluatorHolder;
import org.testatoo.core.component.Component;
import org.testatoo.core.nature.ValueSupport;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class Value extends Property {

    private String expected_value;

    public Value(String value) {
        this.expected_value = value;
    }

    @Override
    public boolean is(Component component) {
        if (component instanceof ValueSupport) {
            String value = EvaluatorHolder.get().value((ValueSupport) component);
            if (value.equals(expected_value))
                return true;
            else
                throw new AssertionError("Expected value " + expected_value + " but was " + value);
        }
        throw new AssertionError("The component does not support Value");
    }
}
