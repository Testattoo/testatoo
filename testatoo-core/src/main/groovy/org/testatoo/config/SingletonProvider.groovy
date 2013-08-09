package org.testatoo.config

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

/**
 * Wrapper for {@link org.testatoo.config.Provider} which returns always the same
 * instance, as a singleton
 * <p/>
 * <b>Example:</b>
 * <pre>{@code Provider<Container> singleton = SingletonProvider.from(container);}</pre>
 */
abstract class SingletonProvider<T> implements Provider<T> {

    private T instance

    @Override
    public final T get() throws Throwable{
        if (instance == null) {
            instance = create()
        }
        return instance
    }

    protected abstract T create() throws Throwable

    /**
     * Wrap the given {@link org.testatoo.config.Provider} to return always the same instance all the time. like a singleton.
     *
     * @param provider The provider to wrap
     * @param <T>      Type of object returned
     * @return A {@link org.testatoo.config.Provider} that will always returns the same instance
     */
    public static <T> SingletonProvider<T> from(final Provider<T> provider) {
        if (provider == null) {
            return null
        }
        return new SingletonProvider<T>() {
            @Override
            protected T create() throws Throwable {
                return provider.get()
            }
        };
    }
}