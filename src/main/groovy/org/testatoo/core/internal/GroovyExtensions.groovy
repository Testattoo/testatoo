/**
 * Copyright (C) 2014 Ovea (dev@ovea.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.core.internal

import org.testatoo.core.dsl.Block
import org.testatoo.core.dsl.Blocks
import org.testatoo.core.dsl.Key

import java.time.Duration

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class GroovyExtensions {

    public static Collection<?> plus(Key a, Key b) { [a, b] }

    public static Collection<?> plus(Key a, String b) { [a, b] }

    static boolean asBoolean(Block block) {
        Blocks.run(block)
        return true
    }

    public static Duration getSeconds(Number self) { Duration.ofSeconds(self.longValue()) }
}
