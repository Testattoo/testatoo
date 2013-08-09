package org.testatoo.config

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

interface ScopedProviderRegistry<T, E> {

    /**
     * Register in Testatoo an exetrnal built implementation.
     * It will be managed (started, stopped, ...) by Testatoo.
     *
     * @param instance The object instance to register. I.e. Selenium Server, Selenium Session, WebDriver, ...
     * @return this
     */
    ScopedProvider<E> register(T instance)

    /**
     * Register in Testatoo an implementation provider.
     * It will be managed (started, stopped, ...) by Testatoo.
     * <p/>
     * {@link Provider} are usually created by builders with the create...() methods
     * in Tesatoo module configuration. But you can provide you own implementation if you need.
     *
     * @param provider A {@link Provider} which will return the implementation when called
     * @return this
     */
    ScopedProvider<E> registerProvider(Provider<T> provider)

}