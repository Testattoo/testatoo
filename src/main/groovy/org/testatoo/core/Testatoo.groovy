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
import org.testatoo.core.evaluator.Evaluator

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class Testatoo {

    static boolean debug = false
    static TimeDuration waitUntil = 2.seconds

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

    static Browser getBrowser() {
        return new Browser(evaluator)
    }

    static void resetWaitUntil() {
        waitUntil = 2.seconds
    }

    static {
        scan 'org.testatoo.bundle.html5'
    }
}
