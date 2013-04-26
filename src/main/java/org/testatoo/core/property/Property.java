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
package org.testatoo.core.property;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class Property {

    private String property;
    private String value;

    public Property(String property, String value) {
        this.property = property;
        this.value = value;
    }

    public boolean equalsTo(String expected_value) {
        if (value.equals(expected_value))
            return true;
        throw new AssertionError("Expected " + property + ": \"" + expected_value + "\" but was: \"" + value + "\"");
    }

    public boolean contains(String expected_value) {
        if (value.contains(expected_value))
            return true;
        throw new AssertionError("Expected " + property + " contains: \"" + expected_value + "\" but was: \"" + value + "\"");
    }

    public boolean matches(String expression) {
        if (value.matches(expression))
            return true;
        throw new AssertionError("Expected " + property + " matches: \"" + expression + "\" but was: \"" + value + "\"");

    }

}
