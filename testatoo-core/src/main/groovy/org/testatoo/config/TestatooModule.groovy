package org.testatoo.config

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

/**
 * Module used to configure Testatoo. Client code must provide an implementation of the module interface.
 * <p/>
 * To facilitate configuration and method accessibility, we also provide an
 * {@link org.testatoo.config.AbstractTestatooModule} which you can extend and will provide direct access
 * to methods in {@link org.testatoo.config.TestatooConfig}
 */
interface TestatooModule {
    void configure(TestatooConfig config) throws Throwable
}
