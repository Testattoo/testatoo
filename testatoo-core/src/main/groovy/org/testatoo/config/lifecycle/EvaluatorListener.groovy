package org.testatoo.config.lifecycle

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
 * protected void configure() {*     install(commonModule());
 * 	   [...]
 *     seleniumSessions()
 *         .register(provider)
 *         .add(new EvaluatorListenerAdapter&lt;Selenium&gt;() {*             public void afterStart(final Selenium session) {*                 // customize session
 *                 // setup here your custom cartridge
 *}*});
 *}</code></pre>
 * <p/>
 * We strongly recommend that classes which need to receive callback of
 * object startup extend the adapter class {@link EvaluatorListenerAdapter}
 * insead of implementing this interface.
 * <br>
 * This will allow us adding more events to the interface without impacting your code.
 */
interface EvaluatorListener<T> {

    /**
     * Callback method called before starting the implementation
     *
     * @param object The implementation object (Selenium session, WebDriver, ...)
     */
    void beforeStart(T object)

    /**
     * Callback method called after having started the implementation
     *
     * @param object The implementation object (Selenium session, WebDriver, ...)
     */
    void afterStart(T object)

    /**
     * Callback method called before starting the implementation
     *
     * @param object The implementation object (Selenium session, WebDriver, ...)
     */
    void beforeStop(T object)

    /**
     * Callback method called after having started the implementation
     *
     * @param object The implementation object (Selenium session, WebDriver, ...)
     */
    void afterStop(T object)

}