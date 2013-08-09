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

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.testatoo.config.lifecycle.TestInvocation;
import org.testatoo.config.testatoo.Testatoo;

import java.lang.reflect.Method;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

public final class TestatooJunitRunner extends BlockJUnit4ClassRunner {

    private final ThreadLocal<Object> createdTest = new ThreadLocal<>();
    private Testatoo testatoo;

    public TestatooJunitRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected void runChild(final FrameworkMethod method, final RunNotifier notifier) {
        testatoo.scheduleTest(getTestClass().getJavaClass(), method.getMethod(), new Runnable() {
            @Override
            public void run() {
                TestatooJunitRunner.super.runChild(method, notifier);
            }
        });
    }

    @Override
    protected Statement classBlock(RunNotifier notifier) {
        final Statement statement = super.classBlock(notifier);
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                testatoo = Testatoo.configure(getTestClass().getJavaClass().getAnnotation(TestatooModules.class));
                testatoo.start();
                try {
                    statement.evaluate();
                } finally {
                    testatoo.stop();
                }
            }
        };
    }

    @Override
    protected Object createTest() throws Exception {
        Object o = super.createTest();
        createdTest.set(o);
        return o;
    }

    @Override
    protected Statement methodBlock(final FrameworkMethod method) {
        final Statement statement = super.methodBlock(method);
        final Object test = createdTest.get();
        createdTest.remove();
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    testatoo.on(test, method.getMethod());
                    statement.evaluate();
                } catch (Throwable throwable) {
                    throw new RuntimeException(throwable.getMessage(), throwable);
                }
            }
        };
    }

    @Override
    protected final Statement methodInvoker(final FrameworkMethod method, final Object test) {
        return new Statement() {
            @SuppressWarnings({"ThrowableResultOfMethodCallIgnored"})
            @Override
            public void evaluate() throws Throwable {
                testatoo.executeTestMethod(new TestInvocation() {
                    @Override
                    public Method getMethod() {
                        return method.getMethod();
                    }

                    @Override
                    public void proceed() throws Throwable {
                        TestatooJunitRunner.super.methodInvoker(method, test).evaluate();
                    }

                    @Override
                    public Object getTestInstance() {
                        return test;
                    }
                });
            }
        };
    }

}
