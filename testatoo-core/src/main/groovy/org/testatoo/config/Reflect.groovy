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
package org.testatoo.config

import java.lang.reflect.Field;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

final class Reflect {

    private Reflect() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(Object o, String fieldName) {
        try {
            Field f = o.getClass().getDeclaredField(fieldName)
            f.setAccessible(true)
            return (T) f.get(o)
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e.message, e)
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.message, e)
        }
    }
}