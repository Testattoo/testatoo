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
import org.testatoo.core.nature.TextSupport;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class Text extends Property {

    private String expected_text;

    public Text(String text) {
        this.expected_text = text;
    }

    @Override
    public boolean is(Component component) {
        if (component instanceof TextSupport) {
            String text = EvaluatorHolder.get().text((TextSupport) component);
            if (text.equals(expected_text))
                return true;
            else
                throw new AssertionError("Expected text " + expected_text + " but was " + text);
        }
        throw new AssertionError("The component does not support Text");
    }
}