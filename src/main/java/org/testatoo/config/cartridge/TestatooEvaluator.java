/**
 * Copyright (C) 2013 Ovea <dev@testatoo.org>
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
package org.testatoo.config.cartridge;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

/**
 * Define all implementations supported in Testatoo.
 */
public enum TestatooEvaluator {

    /**
     * <a href="http://www.openqa.org/">Selenium</a>
     */
    SELENIUM("com.thoughtworks.selenium.Selenium");

    private final Class className;

    private TestatooEvaluator(String className) {
        this.className = load(className);
    }

    private Class load(String className) {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Check wheter this implementation is on the classpath
     *
     * @return true if the implementation is available
     */
    public boolean isPresent() {
        return className != null;
    }

    /**
     * Check wheter given object is an instance of this implementation.
     * Note that this implementation must be on the classpath
     * ({@link #isPresent()} must be true).
     *
     * @param o the instance to check
     * @return true if it is the case.
     */
    public boolean isInstance(Object o) {
        return isPresent() && className.isAssignableFrom(o.getClass());
    }

    /**
     * For a given implementation instance (i.e. Selenium session, WebDriver, ...)
     * return the TestatooEvaluator instance matching it
     *
     * @param instance The object instance
     * @return The TestatooEvaluator instance matching this implementation
     */
    public static TestatooEvaluator from(Object instance) {
        String className = instance.getClass().getName();
        for (TestatooEvaluator evaluator : values()) {
            if (evaluator.isInstance(instance)) {
                return evaluator;
            }
        }
        throw new IllegalStateException("Unknown / Unsupported communication layer: " + className);
    }

}