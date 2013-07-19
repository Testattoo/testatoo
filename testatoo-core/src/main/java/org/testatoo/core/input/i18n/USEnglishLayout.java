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
package org.testatoo.core.input.i18n;

import org.testatoo.core.input.KeyboardLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class USEnglishLayout implements KeyboardLayout {

    private Map<Character, Integer> characterMap = new HashMap<Character, Integer>();

    public USEnglishLayout() {
        init();
    }

    public int convert(char character) {
        return characterMap.get(character);
    }

    private void init() {
        characterMap.put('a', 65);
        characterMap.put('b', 66);
        characterMap.put('c', 67);
        characterMap.put('d', 68);
        characterMap.put('e', 69);
        characterMap.put('f', 70);
        characterMap.put('g', 71);
        characterMap.put('h', 72);
        characterMap.put('i', 73);
        characterMap.put('j', 74);
        characterMap.put('k', 75);
        characterMap.put('l', 76);
        characterMap.put('m', 77);
        characterMap.put('n', 78);
        characterMap.put('o', 79);
        characterMap.put('p', 80);
        characterMap.put('q', 81);
        characterMap.put('r', 82);
        characterMap.put('s', 83);
        characterMap.put('t', 84);
        characterMap.put('u', 85);
        characterMap.put('v', 86);
        characterMap.put('w', 87);
        characterMap.put('x', 88);
        characterMap.put('y', 89);
        characterMap.put('z', 90);
    }
}