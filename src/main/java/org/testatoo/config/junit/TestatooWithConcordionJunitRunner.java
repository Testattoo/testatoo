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
package org.testatoo.config.junit;

import org.aopalliance.intercept.MethodInvocation;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.testatoo.config.annotation.TestatooModules;
import org.testatoo.config.testatoo.Testatoo;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

public final class TestatooWithConcordionJunitRunner extends ConcordionRunner {

    private Testatoo testatoo;

    public TestatooWithConcordionJunitRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected void runChild(final FrameworkMethod method, final RunNotifier notifier) {
        testatoo.scheduleTest(getTestClass().getJavaClass(), method.getMethod(), new Runnable() {
            @Override
            public void run() {
                TestatooWithConcordionJunitRunner.super.runChild(method, notifier);
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
    protected final Statement methodInvoker(final FrameworkMethod method, final Object test) {
        return new Statement() {
            @SuppressWarnings({"ThrowableResultOfMethodCallIgnored"})
            @Override
            public void evaluate() throws Throwable {
                testatoo.executeTestMethod(new MethodInvocation() {
                    @Override
                    public Method getMethod() {
                        return method.getMethod();
                    }

                    @Override
                    public Object[] getArguments() {
                        return new Object[0];
                    }

                    @Override
                    public Object proceed() throws Throwable {
                        testatoo.on(test, method.getMethod());
                        TestatooWithConcordionJunitRunner.super.methodInvoker(method, test).evaluate();
                        return null;
                    }

                    @Override
                    public Object getThis() {
                        return test;
                    }

                    @Override
                    public AccessibleObject getStaticPart() {
                        return method.getMethod();
                    }
                });
            }
        };
    }

}