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
 * This inteface is used a a callback that you can add when
 * registering Selenium sessions or WebDrivers. It can be used
 * to customize you selenium Session or WebDriver after it has
 * started, or even to setup a custom Cartridge
 * you want to test.
 * <p/>
 * <b>Example:</b>
 * <pre><code>
 * protected void configure() {
 *     install(commonModule());
 * 	   [...]
 *     seleniumSessions()
 *         .register(provider)
 *         .add(new EvaluatorListenerAdapter&lt;Selenium&gt;() {
 *             public void afterStart(final Selenium session) {
 *                 // customize session
 *                 // setup here your custom cartridge
 *             }
 *         });
 * }</code></pre>
 * <p/>
 * We strongly recommend that classes which need to receive callback of
 * object startup extend the adapter class {@link org.testatoo.config.cartridge.EvaluatorListenerAdapter}
 * insead of implementing this interface.
 * <br>
 * This will allow us adding more events to the interface without impacting your code.
 */
public interface EvaluatorListener<T> {

    /**
     * Callback method called before starting the implementation
     *
     * @param object The implementation object (Selenium session, WebDriver, ...)
     */
    void beforeStart(T object);

    /**
     * Callback method called after having started the implementation
     *
     * @param object The implementation object (Selenium session, WebDriver, ...)
     */
    void afterStart(T object);

    /**
     * Callback method called before starting the implementation
     *
     * @param object The implementation object (Selenium session, WebDriver, ...)
     */
    void beforeStop(T object);

    /**
     * Callback method called after having started the implementation
     *
     * @param object The implementation object (Selenium session, WebDriver, ...)
     */
    void afterStop(T object);

}
