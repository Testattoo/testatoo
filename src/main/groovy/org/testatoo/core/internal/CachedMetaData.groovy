/**
 * Copyright (C) 2016 Ovea (dev@ovea.com)
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
import org.testatoo.core.MetaDataProvider
import org.testatoo.core.MetaInfo
import org.testatoo.core.component.Component
import static org.testatoo.core.Testatoo.config

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class CachedMetaData implements MetaDataProvider {

    @Delegate
    private MetaInfo metaInfo

    IdProvider idProvider

    @Override
    MetaInfo getMetaInfo(Component c) {
        if (!metaInfo) {
                MetaInfo info = idProvider.getMetaInfos()[0]
                if (c.class != Component) {
                    String identifyingExpr = Identifiers.getIdentifyingExpression(c.class)
                    if (!(config.evaluator.check(info.id, identifyingExpr))) {
                        Class<Component> type = config.componentTypes.find {
                            config.evaluator.check(info.id, Identifiers.getIdentifyingExpression(it))
                        }
                        throw new ComponentException("Expected a ${c.class.simpleName} for component with id '${info.id}', but was: ${type?.simpleName ?: 'unknown'}")
                    }
                }
                metaInfo = info
            }
            return metaInfo
    }

    @Override
    List<MetaInfo> getMetaInfos() throws ComponentException {
        idProvider.getMetaInfos()
    }
}
