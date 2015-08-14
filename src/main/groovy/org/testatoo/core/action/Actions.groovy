package org.testatoo.core.action

import groovy.time.TimeDuration
import org.testatoo.core.Blocks
import org.testatoo.core.Component
import org.testatoo.core.Log

import java.util.concurrent.TimeoutException

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Actions {

    static final Component check(Component c) {
        c.execute(new Check())
        return c
    }

    static final Component uncheck(Component c) {
        c.execute(new Uncheck())
        return c
    }

    static final Component select(Component c) {
        c.execute(new Select())
        return c
    }

    static final Component unselect(Component c) {
        c.execute(new Unselect())
        return c
    }

    static final Component on(Component c) {
        return c
    }

    static final Component reset(Component c) {
        c.execute(new Reset())
        return c
    }

    static final Component submit(Component c) {
        c.execute(new Submit())
        return c
    }

    static void waitUntil(TimeDuration duration = 5.seconds, Closure c) {
        c()
        try {
            _waitUntil duration.toMilliseconds(), 500, {
                Log.testatoo "waitUntil: ${c}"
                Blocks.run(Blocks.compose(Blocks.pending()))
            }
        } catch (TimeoutException e) {
            throw new RuntimeException("${e.message}")
        }
    }

    private static <V> V _waitUntil(final long timeout, long interval, Closure<V> c) throws TimeoutException {
        Throwable ex = null
        long t = timeout
        for (; t > 0; t -= interval) {
            try {
                return c.call()
            } catch (Throwable e) {
                ex = e
            }
            Thread.sleep(interval)
        }
        throw new TimeoutException("Unable to reach the condition within ${timeout / 1000} seconds (${ex.message})")
    }



    //
//
//
//    static Input reset(Input input) {
//        input.evaluator.click(input.id);
//        input.reset()
//        evaluator.trigger(input.id, 'blur')
//        return input
//    }
//
//    static Form submit(Form form) {
//        form.submit()
//        return form
//    }
}
