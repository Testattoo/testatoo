package org.testatoo.config.lifecycle;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-07-19
 */
public interface TestInterceptor {
    void invoke(TestInvocation invocation) throws Throwable;
}
