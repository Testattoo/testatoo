package org.testatoo.experimental.dsl

import java.util.concurrent.TimeoutException

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-06
 */
class Util {
    static <V> V waitUntil(final long timeout, long interval, Closure<V> c) throws TimeoutException {
        Throwable ex = null
        long t = timeout
        for (; t > 0; t -= interval) {
            try {
                return c.call()
            } catch (RuntimeException e) {
                ex = e
            }
            Thread.sleep(interval)
        }
        throw new TimeoutException("Unable to reach the condition within ${timeout} seconds (${ex.message})")
    }
}
