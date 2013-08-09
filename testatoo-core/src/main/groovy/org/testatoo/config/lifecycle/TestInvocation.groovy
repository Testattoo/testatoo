package org.testatoo.config.lifecycle

import java.lang.reflect.Method

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
public interface TestInvocation {
    Method getMethod()

    void proceed() throws Throwable

    Object getTestInstance()
}

