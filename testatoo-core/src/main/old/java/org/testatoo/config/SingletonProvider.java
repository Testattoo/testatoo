/**
 * Copyright (C) 2008 Ovea <dev@ovea.com>
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
package org.testatoo.config;

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
public abstract class SingletonProvider<T> implements Provider<T> {

    private T instance;

    @Override
    public final T get() throws Throwable{
        if (instance == null) {
            instance = create();
        }
        return instance;
    }

    protected abstract T create() throws Throwable;

    /**
     * Wrap the given {@link org.testatoo.config.Provider} to return always the same instance all the time. like a singleton.
     *
     * @param provider The provider to wrap
     * @param <T>      Type of object returned
     * @return A {@link org.testatoo.config.Provider} that will always returns the same instance
     */
    public static <T> SingletonProvider<T> from(final Provider<T> provider) {
        if (provider == null) {
            return null;
        }
        return new SingletonProvider<T>() {
            @Override
            protected T create() throws Throwable {
                return provider.get();
            }
        };
    }
}
