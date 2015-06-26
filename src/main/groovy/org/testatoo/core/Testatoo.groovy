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

import com.google.common.reflect.ClassPath
import groovy.time.TimeDuration
import org.testatoo.bundle.html5.components.Form
import org.testatoo.bundle.html5.components.Radio
import org.testatoo.bundle.html5.components.input.Input
import org.testatoo.core.evaluator.Evaluator
import org.testatoo.core.state.Checked
import org.testatoo.core.state.Unchecked

import java.util.concurrent.TimeoutException

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class Testatoo {

    static boolean debug = false

    static final Collection<Class<Component>> componentTypes = new HashSet<>()

    static Evaluator evaluator

    static void scan(String... packageNames) {
        componentTypes.addAll(packageNames
            .collect { ClassPath.from(Thread.currentThread().contextClassLoader).getTopLevelClassesRecursive(it) }
            .flatten()
            .collect { it.load() }
            .findAll { Component.isAssignableFrom(it) && Identifiers.hasIdentifier(it) })
    }

    // DSL
    static Component $(String jQuery, long timeout = 2000) { Component.$(jQuery, timeout) }

    static Components<? extends Component> $$(String jQuery, long timeout = 2000) { Components.$$(jQuery, timeout) }

    static void open(String uri) { browser.open(uri) }

    static Component check(Component c) {
        if (c.hasState(Unchecked))
            evaluator.click(c.id)
        else
            throw new ComponentException("${c.class.simpleName} ${c} is already checked and cannot be checked")
        return c
    }

    static Component uncheck(Component c) {
        if (c instanceof Radio) {
            throw new ComponentException("Operation not supported for ${c.class.simpleName}")
        }

        if (c.hasState(Checked))
            evaluator.click(c.id)
        else
            throw new ComponentException("${c.class.simpleName} ${c} is already unchecked and cannot be unchecked")
        return c
    }

    static Browser getBrowser() {
        return new Browser(evaluator)
    }

    static Interaction on(Component c) {
        return new Interaction(c)
    }

    static Form reset(Form form) {
        form.reset()
        return form
    }

    static Input reset(Input input) {
        input.evaluator.click(input.id);
        input.reset()
        evaluator.trigger(input.id, 'blur')
        return input
    }

    static Form submit(Form form) {
        form.submit()
        return form
    }

    static void waitUntil(TimeDuration duration = 5.seconds, Closure c) {
        c()
        try {
            _waitUntil duration.toMilliseconds(), 500, {
                Log.testatoo "waitUntil: ${c}"
                Blocks.run(Blocks.compose(Blocks.pending()))
            }
        } catch (TimeoutException e) {
            throw new RuntimeException("${e.message}")
        }
    }

    static {
        scan 'org.testatoo.bundle.html5'
    }

    private static <V> V _waitUntil(final long timeout, long interval, Closure<V> c) throws TimeoutException {
        Throwable ex = null
        long t = timeout
        for (; t > 0; t -= interval) {
            try {
                return c.call()
            } catch (Throwable e) {
                ex = e
            }
            Thread.sleep(interval)
        }
        throw new TimeoutException("Unable to reach the condition within ${timeout / 1000} seconds (${ex.message})")
    }
}
