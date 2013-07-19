package org.testatoo.config.lifecycle;

import java.lang.reflect.Method;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-07-19
 */
public interface TestInvocation {
    Method getMethod();

    void proceed() throws Throwable;

    Object getTestInstance();
}
