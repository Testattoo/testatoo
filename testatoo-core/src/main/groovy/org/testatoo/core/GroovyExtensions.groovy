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

import groovy.time.TimeDuration
import org.testatoo.core.component.Component
import org.testatoo.core.evaluator.Evaluator
import org.testatoo.core.input.Key
import org.testatoo.core.property.Properties
import org.testatoo.core.property.Property
import org.testatoo.core.property.matcher.PropertyMatcher
import org.testatoo.core.state.State

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class GroovyExtensions {

    public static Collection<?> plus(Key a, Key b) { [a, b] }
    public  static Collection<?> plus(Key a, String b) { [a, b] }

    static void click(Key key, Component c) { click([key], c) }
    static void rightClick(Key key, Component c) { rightClick([key], c) }

    static void click(Collection<Key> keys, Component c) { Testatoo.evaluator.click(c.id, Evaluator.MouseButton.LEFT, Evaluator.MouseClick.SINGLE, keys) }
    static void rightClick(Collection<Key> keys, Component c) { Testatoo.evaluator.click(c.id, Evaluator.MouseButton.RIGHT, Evaluator.MouseClick.SINGLE, keys) }

    static Block are(Collection components, State matcher) {
        Blocks.and(components.collect { ((Component) it).is(matcher) })
    }

    static boolean asBoolean(Block block) {
        Blocks.run(block)
        return true
    }

    public static PropertyMatcher getItems(Integer expected) {
        Properties.size.equalsTo(expected)
    }

    public static PropertyMatcher getGroupItems(Integer expected) {
        Properties.groupItemsSize.equalsTo(expected)
    }

    public static PropertyMatcher getColumns(Integer expected) {
        Properties.columnSize.equalsTo(expected)
    }

    public static PropertyMatcher getRows(Integer expected) {
        Properties.rowSize.equalsTo(expected)
    }

    public static PropertyMatcher getCells(Integer expected) {
        Properties.size.equalsTo(expected)
    }

    public static PropertyMatcher getVisibleItems(Integer expected) {
        Properties.visibleItemsSize.equalsTo(expected)
    }

    public static TimeDuration getSeconds(Number self) { new TimeDuration(0, 0, 0, self.intValue(), 0) }

}
