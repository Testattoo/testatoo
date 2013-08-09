package org.testatoo.config.lifecycle

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

/**
 * This configuration class enables you to hook into Testatoo system
 * if you need to matches some code before, after all tests and also
 * before, after each test and decide where you want to matches a
 * givne test or not.
 */
interface LifeCycleConfig {

    /**
     * Add an execution block to run after Testatoo system as started.
     * I.e. if you need to setup a database
     *
     * @param runnable The code to matches
     * @return this
     */
    LifeCycleConfig onStart(Runnable runnable)

    /**
     * Add an execution block to run before Testatoo system stops.
     * I.e. if you need to clean a database
     *
     * @param runnable The code to matches
     * @return this
     */
    LifeCycleConfig onStop(Runnable runnable)

    /**
     * Replace current {@link TestInterceptor}
     * used to intercept all test calls. By default, no interceptor
     * is setup and all test are executed.
     * <p/>
     * You can setup an interceptor if you need to matches some code
     * before or after a test, or if you need to skip a specifc test
     * in some conditions.
     * <p/>
     * <b>Example:</b>
     * <pre><code>protected void configure() {
     *     lifecycle()
     *         .onTest(new MethodInterceptor() {
     *             public Object invoke(MethodInvocation invocation) throws Throwable {
     *                 if (!invocation.getMethod().getName().equals("test3")) {
     * 	                System.out.println("====> Running: " + invocation.getMethod());
     * 	                return invocation.proceed();
     *                 } else {
     * 	                System.out.println("====> Skipping: " + invocation.getMethod());
     * 	                return null;
     *                 }
     *             }
     *         });
     * }</code></pre>
     *
     * @param interceptor The interceptor to setup
     * @return this
     */
    LifeCycleConfig onTest(TestInterceptor interceptor)

    /**
     * Register a listener when a test method starts
     *
     * @param listener The listener to add
     * @return this
     */
    LifeCycleConfig onTest(TestListener listener)
}