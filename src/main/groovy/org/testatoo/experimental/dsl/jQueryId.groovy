package org.testatoo.experimental.dsl

import org.testatoo.core.EvaluatorException
import org.testatoo.core.EvaluatorHolder

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-03
 */
class jQueryId implements Id {
    private final String expression
    private final long timeout

    @Override
    MetaClass getMetaClass() {
        return super.getMetaClass()
    }

    jQueryId(String expression, long timeout) {
        this.expression = expression
        this.timeout = timeout
    }

    @Override
    String getValue() { waitUntilId() }

    private String waitUntilId() {
        String[] ids = waitUntilIds()
        if (ids.length > 1)
            throw new ComponentException("Find more than one component defined by jQueryExpression=" + expression.substring(7))
        else
            return ids[0]
    }

    private String[] waitUntilIds() {
        Throwable ex = null
        try {
            final long step = 500
            long timeout = this.timeout
            for (; timeout > 0 && !Thread.currentThread().isInterrupted(); timeout -= step) {
                try {
                    return EvaluatorHolder.get().elementsId(expression)
                } catch (RuntimeException e) {
                    ex = e
                }
                Thread.sleep(step)
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt()
            ex = e
        }

        if (ex instanceof EvaluatorException) {
            if (expression.startsWith("jquery:"))
                throw new ComponentException("Cannot find component defined by jQueryExpression=" + expression.substring(7))
            else
                throw new ComponentException("Cannot find component defined by id=" + expression)
        }
        throw new RuntimeException("Unable to reach the condition in 2 seconds", ex)
    }
}
