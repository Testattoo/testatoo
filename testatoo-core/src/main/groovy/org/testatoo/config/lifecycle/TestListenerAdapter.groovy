package org.testatoo.config.lifecycle

import java.lang.reflect.Method

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class TestListenerAdapter implements TestListener {
    @Override
    public void onTest(Object instance, Method method) {}
}
