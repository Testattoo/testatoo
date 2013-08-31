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
package org.testatoo.config

import org.testatoo.config.annotation.Implementation
import org.testatoo.config.lifecycle.*
import org.testatoo.config.selenium.SeleniumServerBuilder
import org.testatoo.config.selenium.SeleniumServerConfig
import org.testatoo.config.selenium.SeleniumSessionBuilder
import org.testatoo.config.selenium.SeleniumSessionConfig
import org.testatoo.core.Current
import org.testatoo.core.evaluator.EvaluatorHolder

import java.lang.reflect.Field
import java.lang.reflect.Method
import java.util.logging.Level
import java.util.logging.Logger

import static org.testatoo.config.Ensure.notNull;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

final class DefaultTestatooConfig implements TestatooConfig {

    private static final Logger LOGGER = Logger.getLogger(DefaultTestatooConfig.class.name)

    private DefaultConcurrentTestingConfig concurrentTestingConfig
    private final List<EventListener> eventListeners = new ArrayList<EventListener>(100)
    private final DefaultSeleniumServerConfig seleniumServerConfig
    private final DefaultLifeCycleConfig lifeCycleConfig
    private final DefaultSeleniumSessionConfig seleniumSessionConfig
    private TestInterceptor interceptorChain = new TestInterceptor() {
        @Override
        public void invoke(TestInvocation invocation) throws Throwable {
            invocation.proceed()
        }
    };
    private final List<TestListener> testListeners = new LinkedList<TestListener>()

    DefaultTestatooConfig() {
        seleniumServerConfig = new DefaultSeleniumServerConfig(this)
        lifeCycleConfig = new DefaultLifeCycleConfig(this)
        seleniumSessionConfig = new DefaultSeleniumSessionConfig(this)
        concurrentTestingConfig = new DefaultConcurrentTestingConfig(this)
    }

    @Override
    public TestatooConfig install(TestatooModule... modules) throws Throwable {
        notNull(modules, "Testatoo module list")
        for (TestatooModule module : modules) {
            module.configure(this)
        }
        return this
    }

    @Override
    public LifeCycleConfig lifecycle() {
        return lifeCycleConfig
    }

    @Override
    public SeleniumServerConfig seleniumServers() {
        return seleniumServerConfig
    }

    @Override
    public SeleniumServerBuilder createSeleniumServer() {
        return new SeleniumServerBuilder()
    }

    @Override
    public SeleniumSessionConfig seleniumSessions() {
        return seleniumSessionConfig
    }

    @Override
    public SeleniumSessionBuilder createSeleniumSession() {
        return new DefaultSeleniumSessionBuilder()
    }

    @Override
    public TestatooConfig useAnnotations() {
        add(new TestListenerAdapter() {
            @Override
            public void onTest(Object instance, Method method) {
                Class<?> c = instance.class
                while (c != null && !c.equals(Object.class)) {
                    for (Field field : c.declaredFields) {
                        Implementation implementation = field.getAnnotation(Implementation.class);
                        if (implementation != null) {
                            field.setAccessible(true)
                            try {
                                field.set(instance, new CurrentImpl())
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e.message, e)
                            }
                        }
                    }
                    c = c.getSuperclass()
                }
            }
        });
        return this;
    }

    @Override
    public ConcurrentTestingConfig useConcurrentTesting() {
        return concurrentTestingConfig;
    }

    @Override
    public int findFreePort() { Port.findFreePort() }

    void register(EventListener eventListener) {
        eventListeners.add(eventListener)
    }

    void fire(Event event) throws Throwable {
        if (LOGGER.isLoggable(Level.FINE))
            LOGGER.fine("Firing event " + event + "...")
        for (EventListener listener : event.ordering().sort(new ArrayList<EventListener>(eventListeners))) {
            listener.on(event)
        }
    }

    void add(TestListener testListener) {
        testListeners.add(testListener)
    }

    void fireExecution(Object o, Method m) {
        for (TestListener testListener : testListeners) {
            testListener.onTest(o, m)
        }
    }

    void register(final TestInterceptor aroundInterceptor) {
        final TestInterceptor chain = interceptorChain
        this.interceptorChain = new TestInterceptor() {
            @Override
            public void invoke(final TestInvocation invocation) throws Throwable {
                aroundInterceptor.invoke(new TestInvocation() {
                    @Override
                    public Method getMethod() {
                        return invocation.getMethod()
                    }

                    @Override
                    public void proceed() throws Throwable {
                        chain.invoke(invocation)
                    }

                    @Override
                    public Object getTestInstance() {
                        return invocation.getTestInstance()
                    }
                });
            }
        };
    }

    void fire(TestInvocation testInvocation) throws Throwable {
        interceptorChain.invoke(testInvocation)
    }

    void scheduleTest(Class<?> testClass, Method testMethod, Runnable testBlock) {
        concurrentTestingConfig.scheduleTest(testClass, testMethod, testBlock)
    }

    private static final class CurrentImpl implements Current {
        @Override
        public Object get() {
            return EvaluatorHolder.get().implementation
        }
    }
}
