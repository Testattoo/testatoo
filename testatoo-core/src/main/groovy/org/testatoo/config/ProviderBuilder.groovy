package org.testatoo.config

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

interface ProviderBuilder<T> {

    /**
     * @return a {@link org.testatoo.config.Provider} that is able to return the
     *         proper implementation built, at runtime when needed.
     */
    Provider<T> build()
}
