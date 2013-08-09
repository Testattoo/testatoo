package org.testatoo.config

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

interface Provider<T> {

    /**
     * @return an instance of the given type. To create a singleton provider, you can use
     *         {@link org.testatoo.config.SingletonProvider}
     */
    T get() throws Throwable
}