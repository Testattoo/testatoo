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
import org.testatoo.core.component.Component
import org.testatoo.core.component.Item
import org.testatoo.core.input.Key
import org.testatoo.hamcrest.Matchers
import org.testatoo.hamcrest.PropertyMatcher
import org.testatoo.hamcrest.StateMatcher
import org.testatoo.hamcrest.matcher.property.*

import java.time.Duration

import static org.testatoo.core.Testatoo.getConfig
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
        config.evaluator.click(c.id(), [LEFT, SINGLE], keys)
    }

    static void rightClick(Collection<Key> keys, Component c) {
        config.evaluator.click(c.id(), [RIGHT, SINGLE], keys)
    }

    static void select(Component component, String... values) {
        for (value in values) {
            component.items().find { it.value() == value }.select()
        }
    }

    static void select(Component component, Item... items) {
        items.each { it.select() }
    }

    static void unselect(Component component, Item... items) {
        items.each { it.unselect() }
    }

    static void unselect(Component component, String... values) {
        for (value in values) {
            component.items().find { it.value() == value }.unselect()
        }
    }

    static PropertyMatcher getItems(Integer number) {
       new ItemSizeMatcher(number)
    }

    static PropertyMatcher getVisibleItems(Integer number) {
        new VisibleItemsSizeMatcher(number)
    }

    public static PropertyMatcher getGroups(Integer number) {
        new GroupSizeMatcher(number)
    }

    public static PropertyMatcher getColumns(Integer number) {
        new ColumnSizeMatcher(number)
    }

    public static PropertyMatcher getRows(Integer number) {
        new RowSizeMatcher(number)
    }

    public static PropertyMatcher getCells(Integer number) {
        new CellSizeMatcher(number)
    }
//
//    public static PropertyMatcher getParagraphs(Integer expected) {
//        Properties.paragraphSize.equalsTo(expected)
//    }
//
//    public static PropertyMatcher getArticles(Integer expected) {
//        Properties.articleSize.equalsTo(expected)
//    }

    static void should(Component component, Closure closure) {
        closure.delegate = component
        closure(this)
        for (Matcher matcher : component.BLOCKS) {
            waitUntil(closure, matcher)
        }
        component.BLOCKS.clear()
    }

    static void be(Component component, Class<StateMatcher> matcher) {
        component.BLOCKS.add(org.hamcrest.Matchers.is(matcher.newInstance()))
    }

    static void have(Component component, PropertyMatcher matcher) {
        component.BLOCKS.add(Matchers.has((matcher)))
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
            Description description = new StringDescription()
            description
                    .appendText('Unable to reach the condition after ' + config.waitUntil.toMillis() + ' milliseconds')
                    .appendText('\nExpected: ')
                    .appendDescriptionOf(what)
                    .appendText('\n     but: ');
            what.describeMismatch(c.delegate, description);
            throw new AssertionError(description.toString())
        }
    }
}
