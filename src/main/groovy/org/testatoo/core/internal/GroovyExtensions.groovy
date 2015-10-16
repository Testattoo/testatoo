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

import org.testatoo.bundle.html5.components.list.Item
import org.testatoo.core.Component
import org.testatoo.core.ComponentException
import org.testatoo.core.dsl.Block
import org.testatoo.core.dsl.Blocks
import org.testatoo.core.input.Key
import org.testatoo.core.support.InputSupport

import java.time.Duration

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.MouseModifiers.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class GroovyExtensions {

    static void click(Key key, Component c) { click([key], c) }

    static void rightClick(Key key, Component c) { rightClick([key], c) }

    static void click(Collection<Key> keys, Component c) {
        config.evaluator.click(c.id, [LEFT, SINGLE], keys)
    }

    static void rightClick(Collection<Key> keys, Component c) {
        config.evaluator.click(c.id, [RIGHT, SINGLE], keys)
    }

    static void doubleClick(Component c) {
        config.evaluator.click(c.id, [LEFT, DOUBLE])
    }

    public static Collection<?> plus(Key a, Key b) { [a, b] }

    public static Collection<?> plus(Key a, String b) { [a, b] }

    static boolean asBoolean(Block block) {
        Blocks.run(block)
        return true
    }

    static void with(InputSupport input, String value) {
        // TODO builder
        config.evaluator.trigger(input.id, 'blur')

        clear(input)
        config.evaluator.type([value])
        config.evaluator.trigger(input.id, 'blur')
    }


//    static void select(Component selector, String... values) {
//        if (values) {
//            if (values.length > 1 && selector.singleSelectable) {
//                throw new ComponentException("${selector.class.simpleName} ${selector} doesn't support multi selection")
//            }
//
//            for (value in values) {
//                Item item = selector.items.find { it.value == value } as Item
//                if (item.selected)
//                    throw new ComponentException("${item.class.simpleName} ${item} is already selected")
//                if (item.disabled)
//                    throw new ComponentException("${item.class.simpleName} ${item} is disabled and cannot be selected")
//                item.click()
//            }
//        }
//    }

//    static void select(Component selector, Item... items) {
//        for (item in items) {
//            if (item.selected) {
//                throw new ComponentException("TODO")
//            }
//            item.click()
//        }
//    }

    static void unselect(Component c, String... values) {
        if (values) {
            for (value in values) {
                Item item = c.items.find { it.value == value } as Item
                if (item.unselected)
                    throw new ComponentException("${item.class.simpleName} ${item} is already unselected")
                item.click()
            }
        }
    }

    static void unselect(Component selector, Item... items) {
        for (item in items) {
            if (item.unselected) {
                throw new ComponentException("TODO")
            }
            item.click()
        }
    }

    public static Duration getSeconds(Number self) { Duration.ofSeconds(self.longValue()) }
}
