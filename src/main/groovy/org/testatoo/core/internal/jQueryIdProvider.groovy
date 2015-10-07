/**
 * Copyright (C) 2014 Ovea (dev@ovea.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.core.internal

import org.testatoo.core.ComponentException
import org.testatoo.core.IdProvider
import org.testatoo.core.MetaInfo
import org.testatoo.core.Testatoo

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class jQueryIdProvider implements IdProvider {
    final String expression
    final long timeout
    final boolean singleElement

    jQueryIdProvider(String expression, long timeout, boolean singleElement) {
        this.expression = expression.startsWith('$') ? expression : ('$(\'' + expression + '\')')
        this.timeout = timeout
        this.singleElement = singleElement
    }

    @Override
    List<MetaInfo> getMetaInfos() throws ComponentException {
        Log.log "getMetaInfos: ${expression}"
        List<MetaInfo> metaInfos = Testatoo.config.evaluator.getMetaInfo(expression)
        if (singleElement) {
            if (metaInfos.size() == 1) return metaInfos
            if (metaInfos.size() == 0) throw new ComponentException("Component defined by expression ${expression} not found.")
            throw new ComponentException("Component defined by expression ${expression} is not unique: got ${metaInfos.size()}")
        } else {
            return metaInfos
        }
    }

}
