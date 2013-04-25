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

import com.ovea.tajin.server.Server;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.testatoo.config.ConcurrentTestingConfig;
import org.testatoo.config.TestatooConfig;
import org.testatoo.config.TestatooModule;
import org.testatoo.config.annotation.Implementation;
import org.testatoo.config.container.ContainerBuilder;
import org.testatoo.config.container.ContainerConfig;
import org.testatoo.config.container.ContainerType;
import org.testatoo.config.lifecycle.LifeCycleConfig;
import org.testatoo.config.lifecycle.TestListener;
import org.testatoo.config.lifecycle.TestListenerAdapter;
import org.testatoo.config.selenium.SeleniumServerBuilder;
import org.testatoo.config.selenium.SeleniumServerConfig;
import org.testatoo.config.selenium.SeleniumSessionBuilder;
import org.testatoo.config.selenium.SeleniumSessionConfig;
import org.testatoo.core.Current;
import org.testatoo.core.EvaluatorHolder;

import java.io.IOException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.testatoo.config.testatoo.Ensure.notNull;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

final class DefaultTestatooConfig implements TestatooConfig {

    private static final Logger LOGGER = Logger.getLogger(DefaultTestatooConfig.class.getName());

    private DefaultConcurrentTestingConfig concurrentTestingConfig;
    private final List<EventListener> eventListeners = new ArrayList<EventListener>(100);
    private final DefaultContainerConfig containerConfig;
    private final DefaultSeleniumServerConfig seleniumServerConfig;
    private final DefaultLifeCycleConfig lifeCycleConfig;
    private final DefaultSeleniumSessionConfig seleniumSessionConfig;
    private MethodInterceptor interceptorChain = new MethodInterceptor() {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            return invocation.proceed();
        }
    };
    private final List<TestListener> testListeners = new LinkedList<TestListener>();

    DefaultTestatooConfig() {
        containerConfig = new DefaultContainerConfig(this);
        seleniumServerConfig = new DefaultSeleniumServerConfig(this);
        lifeCycleConfig = new DefaultLifeCycleConfig(this);
        seleniumSessionConfig = new DefaultSeleniumSessionConfig(this);
        concurrentTestingConfig = new DefaultConcurrentTestingConfig(this);
    }

    @Override
    public TestatooConfig install(TestatooModule... modules) {
        notNull(modules, "Testatoo module list");
        for (TestatooModule module : modules) {
            module.configure(this);
        }
        return this;
    }

    @Override
    public LifeCycleConfig lifecycle() {
        return lifeCycleConfig;
    }

    @Override
    public ContainerConfig containers() {
        return containerConfig;
    }

    @Override
    public ContainerType createContainer() {
        return new ContainerType() {
            @Override
            public ContainerBuilder implementedBy(Server type) {
                notNull(type, "Container type");
                return implementedBy(type.toString());
            }

            @Override
            public ContainerBuilder implementedBy(String containerClass) {
                notNull(containerClass, "Container class");
                return new DefaultContainerBuilder(containerClass);
            }
        };
    }

    @Override
    public SeleniumServerConfig seleniumServers() {
        return seleniumServerConfig;
    }

    @Override
    public SeleniumServerBuilder createSeleniumServer() {
        return new DefaultSeleniumServerBuilder();
    }

    @Override
    public SeleniumSessionConfig seleniumSessions() {
        return seleniumSessionConfig;
    }

    @Override
    public SeleniumSessionBuilder createSeleniumSession() {
        return new DefaultSeleniumSessionBuilder();
    }

    @Override
    public TestatooConfig useAnnotations() {
        add(new TestListenerAdapter() {
            @Override
            public void onTest(Object instance, Method method) {
                Class<?> c = instance.getClass();
                while (c != null && !c.equals(Object.class)) {
                    for (Field field : c.getDeclaredFields()) {
                        Implementation implementation = field.getAnnotation(Implementation.class);
                        if (implementation != null) {
                            field.setAccessible(true);
                            try {
                                field.set(instance, new CurrentImpl());
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e.getMessage(), e);
                            }
                        }
                    }
                    c = c.getSuperclass();
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
    public int findFreePort() {
        Random r = new Random();
        do {
            int p = 1025 + r.nextInt(64000);
            try {
                new ServerSocket(p).close();
                return p;
            } catch (IOException ignored) {
            }
        } while (true);
    }

    void register(EventListener eventListener) {
        eventListeners.add(eventListener);
    }

    void fire(Event event) throws Throwable {
        if (LOGGER.isLoggable(Level.FINE))
            LOGGER.fine("Firing event " + event + "...");
        for (EventListener listener : event.ordering().sort(new ArrayList<EventListener>(eventListeners))) {
            listener.on(event);
        }
    }

    void add(TestListener testListener) {
        testListeners.add(testListener);
    }

    void fireExecution(Object o, Method m) {
        for (TestListener testListener : testListeners) {
            testListener.onTest(o, m);
        }
    }

    void register(final MethodInterceptor aroundInterceptor) {
        final MethodInterceptor chain = interceptorChain;
        this.interceptorChain = new MethodInterceptor() {
            @Override
            public Object invoke(final MethodInvocation invocation) throws Throwable {
                return aroundInterceptor.invoke(new MethodInvocation() {
                    @Override
                    public Method getMethod() {
                        return invocation.getMethod();
                    }

                    @Override
                    public Object[] getArguments() {
                        return invocation.getArguments();
                    }

                    @Override
                    public Object proceed() throws Throwable {
                        return chain.invoke(invocation);
                    }

                    @Override
                    public Object getThis() {
                        return invocation.getThis();
                    }

                    @Override
                    public AccessibleObject getStaticPart() {
                        return invocation.getStaticPart();
                    }
                });
            }
        };
    }

    void fire(MethodInvocation testInvocation) throws Throwable {
        interceptorChain.invoke(testInvocation);
    }

    void scheduleTest(Class<?> testClass, Method testMethod, Runnable testBlock) {
        concurrentTestingConfig.scheduleTest(testClass, testMethod, testBlock);
    }

    private static final class CurrentImpl implements Current {
        @Override
        public Object get() {
            return EvaluatorHolder.get().implementation();
        }
    }
}
