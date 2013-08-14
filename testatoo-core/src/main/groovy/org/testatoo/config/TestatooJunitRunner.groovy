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

import org.junit.runner.notification.RunNotifier
import org.junit.runners.BlockJUnit4ClassRunner
import org.junit.runners.model.FrameworkMethod
import org.junit.runners.model.Statement
import org.testatoo.config.lifecycle.TestInvocation

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
final class TestatooJunitRunner extends BlockJUnit4ClassRunner {

    private final ThreadLocal<Object> createdTest = new ThreadLocal<>()
    private TestatooStarter testatoo

    public TestatooJunitRunner(Class<?> klass) {
        super(klass)
    }

    @Override
    protected void runChild(final FrameworkMethod method, final RunNotifier notifier) {
        testatoo.scheduleTest(testClass.javaClass, method.method, { super.runChild(method, notifier) } as Runnable);
    }

    @Override
    protected Statement classBlock(RunNotifier notifier) {
        final Statement statement = super.classBlock(notifier)
        return {
            testatoo = TestatooStarter.configure(testClass.javaClass.getAnnotation(TestatooModules.class))
            testatoo.start()
            try {
                statement.evaluate()
            } finally {
                testatoo.stop()
            }
        } as Statement
    }

    @Override
    protected Object createTest() throws Exception {
        Object o = super.createTest()
        createdTest.set(o)
        return o
    }

    @Override
    protected Statement methodBlock(final FrameworkMethod method) {
        final Statement statement = super.methodBlock(method)
        final Object test = createdTest.get()
        createdTest.remove()
        return {
            testatoo.on(test, method.method)
            statement.evaluate()
        } as Statement
    }

    @Override
    protected final Statement methodInvoker(final FrameworkMethod method, final Object test) {
        Statement delegate = super.methodInvoker(method, test)
        return {
            testatoo.executeTestMethod([
                getMethod: { return method.getMethod() },
                proceed: { delegate.evaluate() },
                getTestInstance: { return test }
            ] as TestInvocation)
        } as Statement
    }

}