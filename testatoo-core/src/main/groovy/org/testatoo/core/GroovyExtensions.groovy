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
package org.testatoo.core

import org.testatoo.core.component.Component
import org.testatoo.core.state.State

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class GroovyExtensions {

    static boolean asBoolean(Block block) {
        Blocks.run(block)
        return true
    }

    static Block are(Collection<? extends Component> components, State matcher) {
        Blocks.and(components.collect { it.is(matcher) })
    }

//    static Block have(Collection<? extends Component> components, PropertyMatcher matcher) {
//        Blocks.and(components.collect { it.has(matcher) })
//    }
//
//    public static TimeDuration getSeconds(Number self) { new TimeDuration(0, 0, 0, self.intValue(), 0) }
//
//    public static TimeDuration getSecond(Number self) { getSeconds(self) }

}
