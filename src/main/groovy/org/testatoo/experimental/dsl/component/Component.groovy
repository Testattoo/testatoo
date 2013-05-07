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
package org.testatoo.experimental.dsl.component

import org.testatoo.experimental.dsl.property.Property
import org.testatoo.experimental.dsl.Block
import org.testatoo.experimental.dsl.Blocks
import org.testatoo.experimental.dsl.Evaluator
import org.testatoo.experimental.dsl.Id
import org.testatoo.experimental.dsl.Matcher
import org.testatoo.experimental.dsl.property.matcher.PropertyMatcher
import org.testatoo.experimental.dsl.state.State

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-03
 */
class Component implements IdSupport {

    private Id id
    Evaluator evaluator
    List<Class<Property>> supportedProperties = [];

    void setId(Id id) { this.id = id }

    String getId() throws ComponentException { id.getValue(evaluator) }

    Block is(State matcher) { block 'is', matcher }

    Block are(State matcher) { block 'is', matcher }

    Block has(PropertyMatcher matcher) { block 'has', matcher }

    Block have(PropertyMatcher matcher) { block 'has', matcher }

    Block click() { Blocks.block "click on ${this}", { evaluator.click(this) } }

    private block(String type, Matcher m) { Blocks.block "matching ${this} ${type} ${m}", { m.matches(this) } }

    @Override
    String toString() { getClass().simpleName + ":${id as String}" }

    Object asType(Class clazz) {
        if (Component.isAssignableFrom(clazz)) {
            Component c = (Component) clazz.newInstance()
            c.setId(this.id)
            c.evaluator = this.evaluator
            return c
        }
        return super.asType(clazz)
    }

    boolean supports(Property property) { property.class in supportedProperties }
}
