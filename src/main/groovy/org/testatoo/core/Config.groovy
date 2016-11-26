/**
 * Copyright © 2016 Ovea (d.avenante@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.core

import com.google.common.reflect.ClassPath
import org.testatoo.core.component.Component
import org.testatoo.core.internal.Identifiers
import org.testatoo.core.internal.Log

import java.time.Duration

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class Config {
    /**
     * Change default time to wait for wait for assertions to complete (waitUntil)
     */
    Duration waitUntil = 2.seconds

    /**
     * Scan for packages containing custom component
     */
    void scan(String... packageNames) {
        componentTypes.addAll(packageNames
            .collect { ClassPath.from(Thread.currentThread().contextClassLoader).getTopLevelClassesRecursive(it) }
            .flatten()
            .collect { it.load() }
            .findAll { Component.isAssignableFrom(it) && Identifiers.hasIdentifier(it) })
    }

    /**
     * Activate debug mode
     */
    static void setDebug(boolean debug) {
        Log.debug = debug
    }

    final Collection<Class<Component>> componentTypes = new HashSet<>()

    /**
     * Sets the default evaluator to use
     */
    Evaluator evaluator
}
