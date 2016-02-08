/**
 * Copyright (C) 2016 Ovea (dev@ovea.com)
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

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.StringDescription
import org.testatoo.core.Component
import org.testatoo.core.ComponentException
import org.testatoo.core.component.Item
import org.testatoo.core.input.Key
import org.testatoo.core.support.MultiSelector
import org.testatoo.core.support.SingleSelector

import java.time.Duration

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.MouseModifiers.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class GroovyExtensions {

    public static Duration getSeconds(Number self) { Duration.ofSeconds(self.longValue()) }

    public static Collection<?> plus(Key a, Key b) { [a, b] }

    public static Collection<?> plus(Key a, String b) { [a, b] }

    static void click(Key key, Component c) { click([key], c) }

    static void rightClick(Key key, Component c) { rightClick([key], c) }

    static void click(Collection<Key> keys, Component c) {
        config.evaluator.click(c.id, [LEFT, SINGLE], keys)
    }

    static void rightClick(Collection<Key> keys, Component c) {
        config.evaluator.click(c.id, [RIGHT, SINGLE], keys)
    }

    static void select(MultiSelector selector, String... values) {
        ArrayList<Item> items = new ArrayList<Item>();
        for (value in values) {
            items.add((Item) selector.items.find { it.value == value } )
        }
        _select(selector as Component, (Item[]) items.toArray())
    }

    static void select(MultiSelector selector, Item... items) {
        _select(selector as Component, items)
    }

    static void select(SingleSelector selector, String value) {
        _select(selector as Component, (Item) selector.items.find { it.value == value })
    }

    static void select(SingleSelector selector, Item item) {
        _select(selector as Component, item)
    }

    private static void _select(Component selector, Item... items) {
        config.evaluator.select(selector as Component, items)
    }

    static void unselect(MultiSelector selector, String... values) {
        ArrayList<Item> items = new ArrayList<Item>();
        for (value in values) {
            items.add((Item) selector.items.find { it.value == value } )
        }
        _unselect(selector as Component, (Item[]) items.toArray())
    }

    static void unselect(MultiSelector selector, Item... items) {
        _unselect(selector as Component, items)
    }

    static void unselect(SingleSelector selector, String value) {
        _unselect(selector as Component, (Item) selector.items.find { it.value == value })
    }

    static void unselect(SingleSelector selector, Item item) {
        _unselect(selector as Component, item)
    }

    private static void _unselect(Component selector, Item... items) {
        config.evaluator.unselect(selector as Component, items)
    }

    static void should(Component component, Closure closure) {
        closure.delegate = component
        closure(this)
        for (Matcher matcher : component.BLOCKS) {
            waitUntil(closure, matcher)
        }
        component.BLOCKS.clear()
    }

    static void be(Component component, Class<Matcher> matcher) {
        component.BLOCKS.add(matcher.newInstance())
    }

    private static void waitUntil(Closure c, Matcher what) {
        boolean success = false;
        long timeout = config.waitUntil.toMillis()
        long interval = 200

        Log.log "WaitUntil: " + timeout
        for (; timeout > 0; timeout -= interval) {
            if(what.matches(c.delegate)) {
                success = true
                break
            }
            Thread.sleep(interval)
        }

        if(!success) {
            Description description = new StringDescription();
            description.appendDescriptionOf(what)
            throw new ComponentException("Unable to reach " + description.toString() + " in " + config.waitUntil.toMillis() + " milliseconds");
        }
    }
}
