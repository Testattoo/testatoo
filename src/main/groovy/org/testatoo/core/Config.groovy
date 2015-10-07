package org.testatoo.core

import com.google.common.reflect.ClassPath
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
     * Scan for packages containing custom components
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
    void setDebug(boolean debug) {
        Log.debug = debug
    }

    final Collection<Class<Component>> componentTypes = new HashSet<>()

    /**
     * Sets the default evaluator to use
     */
    Evaluator evaluator
}
