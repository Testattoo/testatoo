package org.testatoo.experimental.dsl

import org.testatoo.core.EvaluatorHolder

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-01
 */
class Testatoo {

    void assertThat(Closure<?> c) {
        c()
    }

    void open(String uri) { EvaluatorHolder.get().open(uri) }

    Component $(String jQuery, long timeout = 2000) { new Component(new jQueryId(jQuery, timeout)) }

    void waitUntil(Closure<?> c, long timeout = 5000) {
        //TODO: support better optional params for timeout
        final long step = 500
        Throwable ex = null
        try {
            for (; timeout > 0; timeout -= step) {
                try {
                    c()
                    return
                } catch (Throwable e) {
                    ex = e
                }
                Thread.sleep(step)
            }
        } catch (InterruptedException iex) {
            throw new RuntimeException("Interrupted exception", iex)
        }
        throw new RuntimeException("Unable to reach the condition in 5 seconds", ex)
    }

    State visible = { Component c ->
        Assert.ensure c, EvaluatorHolder.get().isVisible(c), [e: 'visible', w: 'hidden']
    } as State


}
