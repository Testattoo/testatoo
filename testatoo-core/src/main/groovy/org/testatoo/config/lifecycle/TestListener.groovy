package org.testatoo.config.lifecycle

import java.lang.reflect.Method

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

interface TestListener {
    void onTest(Object instance, Method method)
}