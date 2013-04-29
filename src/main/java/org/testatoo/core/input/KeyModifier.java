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

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public enum KeyModifier {

    SHIFT(16),
    CONTROL(17),
    ALT(18);
// LEFT_SHIFT,
// LEFT_ALT,
// LEFT_CONTROL,
// FN;
//left window key  	91
//right window key 	92
//select key 	93

    private final int code;

    KeyModifier(int code) {
        this.code = code;
    }
}
