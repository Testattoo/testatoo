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
package org.testatoo.config.lifecycle;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

/**
 * This configuration class enables you to hook into Testatoo system
 * if you need to execute some code before, after all tests and also
 * before, after each test and decide where you want to execute a
 * givne test or not.
 */
public interface LifeCycleConfig {

    /**
     * Add an execution block to run after Testatoo system as started.
     * I.e. if you need to setup a database
     *
     * @param runnable The code to execute
     * @return this
     */
    LifeCycleConfig onStart(Runnable runnable);

    /**
     * Add an execution block to run before Testatoo system stops.
     * I.e. if you need to clean a database
     *
     * @param runnable The code to execute
     * @return this
     */
    LifeCycleConfig onStop(Runnable runnable);

    /**
     * Replace current {@link org.aopalliance.intercept.MethodInterceptor}
     * used to intercept all test calls. By default, no interceptor
     * is setup and all test are executed.
     * <p/>
     * You can setup an interceptor if you need to execute some code
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
    LifeCycleConfig onTest(MethodInterceptor interceptor);

    /**
     * Register a listener when a test method starts
     *
     * @param listener The listener to add
     * @return this
     */
    LifeCycleConfig onTest(TestListener listener);
}
