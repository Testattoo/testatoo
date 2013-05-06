/**
 * Copyright (C) 2008 Ovea <dev@ovea.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.experimental.dsl

import org.testatoo.core.EvaluatorException

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-03
 */
class jQueryId implements Id {
    private final String expression
    private final long timeout

    jQueryId(String expression, long timeout) {
        this.expression = expression
        this.timeout = timeout
    }

    @Override
    String getValue(Evaluator evaluator) {
        String[] ids = waitUntilIds(evaluator)
        if (ids.length == 1) return ids[0]
        if (ids.length == 0) throw new ComponentException("Component defined by ${expression} not found.")
        throw new ComponentException("Component defined by ${expression} not unique")
    }



    private String[] waitUntilIds(Evaluator evaluator) {
        Throwable ex = null
        try {
            final long step = 500
            long timeout = this.timeout
            for (; timeout > 0 && !Thread.currentThread().isInterrupted(); timeout -= step) {
                try {
                    return evaluator.getElementsIds(expression)
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

    @Override
    public String toString() { '$(' + expression + ')';
}
}
