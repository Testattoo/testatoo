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
import org.testatoo.core.dsl.Browser
import org.testatoo.core.dsl.Keyboard
import org.testatoo.core.dsl.Mouse
import org.testatoo.core.internal.Identifiers
import org.testatoo.core.internal.Log

import java.time.Duration

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class Testatoo {

    /**
     * Controls your browser
     */
    static final Browser browser = new Browser()

    /**
     * Access the keyboard
     */
    static final Keyboard keyboard = new Keyboard()

    /**
     * Access the mouse
     */
    static final Mouse mouse = new Mouse()

    /**
     * Change default time to wait for wait for assertions to complete (waitUntil)
     */
    static Duration waitUntil = 2.seconds

    /**
     * Create a component
     */
    static Component $(String jQuery, long timeout = 2000) { Component.$(jQuery, timeout) }

    /**
     * Creates a list of components
     */
    static Components<? extends Component> $$(String jQuery, long timeout = 2000) { Components.$$(jQuery, timeout) }

    /**
     * Scan for packages containing custom components
     */
    static void scan(String... packageNames) {
        componentTypes.addAll(packageNames
            .collect { ClassPath.from(Thread.currentThread().contextClassLoader).getTopLevelClassesRecursive(it) }
            .flatten()
            .collect { it.load() }
            .findAll { Component.isAssignableFrom(it) && Identifiers.hasIdentifier(it) })
    }

    static Evaluator getEvaluator() {
        if (evaluator == null) {
            throw new IllegalStateException("Missing evaluator")
        }
        return evaluator
    }

    /**
     * Activate debug mode
     */
    static void setDebug(boolean debug) {
        Log.debug = debug
    }

    /**
     * Sets the default evaluator to use
     */
    static void setEvaluator(Evaluator evaluator) {
        Testatoo.evaluator = evaluator
    }

    static {
        scan 'org.testatoo.bundle.html5'
    }

    static final Collection<Class<Component>> componentTypes = new HashSet<>()
    private static Evaluator evaluator

}
