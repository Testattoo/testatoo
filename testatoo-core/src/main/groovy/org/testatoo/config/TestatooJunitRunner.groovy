package org.testatoo.config

import org.junit.runner.notification.RunNotifier
import org.junit.runners.BlockJUnit4ClassRunner
import org.junit.runners.model.FrameworkMethod
import org.junit.runners.model.InitializationError
import org.junit.runners.model.Statement
import org.testatoo.config.lifecycle.TestInvocation

import java.lang.reflect.Method

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
final class TestatooJunitRunner extends BlockJUnit4ClassRunner {

    private final ThreadLocal<Object> createdTest = new ThreadLocal<>()
    private TestatooStarter testatoo

    public TestatooJunitRunner(Class<?> klass) throws InitializationError {
        super(klass)
    }

    @Override
    protected void runChild(final FrameworkMethod method, final RunNotifier notifier) {
        testatoo.scheduleTest(getTestClass().javaClass, method.method, { super.runChild(method, notifier) } as Runnable);

//        testatoo.scheduleTest(testClass.javaClass, method.method, new Runnable() {
//            @Override
//            public void run() {
//                TestatooJunitRunner.super.runChild(method, notifier)
//            }
//        });
    }

    @Override
    protected Statement classBlock(RunNotifier notifier) {
        final Statement statement = super.classBlock(notifier)
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                testatoo = TestatooStarter.configure(getTestClass().javaClass.getAnnotation(TestatooModules.class))
                testatoo.start()
                try {
                    statement.evaluate()
                } finally {
                    testatoo.stop()
                }
            }
        };
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
            try {
                testatoo.on(test, method.method)
                statement.evaluate()
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable.message, throwable)
            }
        } as Statement
    }

    @Override
    protected final Statement methodInvoker(final FrameworkMethod method, final Object test) {
        Statement delegate = super.methodInvoker(method, test)
        return {
            testatoo.executeTestMethod(new TestInvocation() {
                @Override
                public Method getMethod() {
                    return method.getMethod()
                }

                @Override
                public void proceed() throws Throwable {
                    delegate.evaluate()
                }

                @Override
                public Object getTestInstance() {
                    return test
                }
            });
        } as Statement
    }

}
