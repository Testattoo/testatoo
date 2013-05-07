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
import org.testatoo.experimental.dsl.component.ComponentException

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-03
 */
class jQueryId implements Id {
    private final String expression
    private final long timeout

    jQueryId(String expression, long timeout) {
        this.expression = expression.startsWith('$') ? expression : ('$(\'' + expression + '\')')
        this.timeout = timeout
    }

    @Override
    String getValue(Evaluator evaluator) throws ComponentException {
        println "get: ${expression}"
        try {
            String[] ids = evaluator.getElementsIds('jquery:' + expression)
            if (ids.length == 1) return ids[0]
            if (ids.length == 0) throw new ComponentException("Component defined by ${expression} not found.")
            throw new ComponentException("Component defined by ${expression} not unique")
        } catch (EvaluatorException e) {
            throw new ComponentException("Component defined by ${expression} cannot be found: ${e.message}")
        }
    }

    @Override
    public String toString() { expression }
}
