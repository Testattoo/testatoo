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
package org.testatoo.config.testatoo;

import org.aopalliance.intercept.MethodInvocation;
import org.testatoo.config.TestatooModule;
import org.testatoo.config.annotation.TestatooModules;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testatoo.config.testatoo.Ensure.notEmpty;
import static org.testatoo.config.testatoo.Ensure.notNull;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

/**
 * Main entry point which creates a Testatoo handler given configuration modules.
 */
public final class Testatoo {

    private final DefaultTestatooConfig config;

    private Testatoo(DefaultTestatooConfig config) {
        this.config = config;
    }

    /**
     * Configure Testatoo by using modules provided in this annotation
     *
     * @param testatooModules Annotation containing the module class list
     * @return The created Testatoo instance
     */
    public static Testatoo configure(TestatooModules testatooModules) {
        notNull(testatooModules, "TestatooModules annotation");
        Class<? extends TestatooModule>[] classes = testatooModules.value();
        notEmpty("Testatoo module list", classes);
        List<TestatooModule> modules = new ArrayList<TestatooModule>(classes.length);
        try {
            for (Class<? extends TestatooModule> moduleClass : classes) {
                Constructor ctor = moduleClass.getDeclaredConstructor();
                ctor.setAccessible(true); // enables the class and constructor to be in package visibility
                modules.add((TestatooModule) ctor.newInstance());
            }
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof RuntimeException)
                throw (RuntimeException) e.getTargetException();
            throw new RuntimeException(e.getTargetException().getMessage(), e.getTargetException());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return configure(modules);
    }

    /**
     * Configure Testatoo by using given list of modules
     *
     * @param testatooModules module list
     * @return The created Testatoo instance
     */
    public static Testatoo configure(TestatooModule... testatooModules) {
        notNull(testatooModules, "Testatoo module list");
        notEmpty("Testatoo module list", testatooModules);
        return configure(Arrays.asList(testatooModules));
    }

    /**
     * Configure Testatoo by using given list of modules
     *
     * @param testatooModules module list
     * @return The created Testatoo instance
     */
    public static Testatoo configure(Iterable<TestatooModule> testatooModules) {
        notNull(testatooModules, "Testatoo module list");
        notEmpty("Testatoo module list", testatooModules);
        DefaultTestatooConfig config = new DefaultTestatooConfig();
        for (TestatooModule module : testatooModules) {
            module.configure(config);
        }
        return new Testatoo(config);
    }

    /**
     * Starts Testatoo. This call will fire a start event to all listeners to start all containers,
     * Selenium servers, Selenium sessions, WebDrivers, ...
     * <p/>
     * usually, this method woul be called before all test of a class
     */
    public void start() throws Throwable {
        config.fire(Event.START);
    }

    /**
     * Stops Testatoo. This call will fire a stop event to all listeners to stop all containers,
     * Selenium servers, Selenium sessions, WebDrivers, ...
     * <p/>
     * usually, this method woul be called after all test of a class
     */
    public void stop() throws Throwable {
        config.fire(Event.STOP);
    }

    public void on(Object o, Method m) {
        config.fireExecution(o, m);
    }

    /**
     * This method is called for each test invocation. This will let Testatoo decide,
     * with the given configuration, wheter the method is executed or not. By
     * default, all methods are executed.
     * <p/>
     * You can register an interceptor (in AOP, and Around Advice) when you
     * configure Testatoo which will enabled you to matches some things before a test,
     * after a test and decide wheter you want or not to matches the test.
     *
     * @param testInvocation The test invocation
     * @throws Throwable Any exception thrown by the tes execution
     * @see org.testatoo.config.lifecycle.LifeCycleConfig
     */
    public void executeTestMethod(MethodInvocation testInvocation) throws Throwable {
        config.fire(testInvocation);
    }

    public void scheduleTest(Class<?> testClass, Method testMethod, Runnable testBlock) {
        config.scheduleTest(testClass, testMethod, testBlock);
    }

}
