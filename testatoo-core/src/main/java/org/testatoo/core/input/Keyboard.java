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
package org.testatoo.core.input;

import org.testatoo.core.EvaluatorHolder;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class Keyboard {

    public static void type(String text) {
        EvaluatorHolder.get().type(text);
    }

    public static void press(Key key) {
        EvaluatorHolder.get().press(key);
    }

    public static void keyDown(KeyModifier keyModifier) {
        EvaluatorHolder.get().keyDown(keyModifier);
    }

    public static void release(KeyModifier keyModifier) {
        EvaluatorHolder.get().release(keyModifier);
    }

    public static void release() {
        EvaluatorHolder.get().release();
    }
}
