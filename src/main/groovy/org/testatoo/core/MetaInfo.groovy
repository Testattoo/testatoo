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
package org.testatoo.core

import groovy.transform.Immutable
import org.testatoo.core.component.Component

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-08-20
 */
@Immutable
class MetaInfo {
    String type
    String cartridge
    String node
    String id

    @Override
    String toString() { "type=${type},id=${id},node=${node},cartridge=${cartridge}" }

    Object asType(Class clazz) {
        if (Component.isAssignableFrom(clazz)) {
            return Component.$("#${id}").asType(clazz)
        }
        return super.asType(clazz)
    }

}
