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
import org.testatoo.core.MetaInfo

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class MetaInfoProvider implements IdProvider {

    final MetaInfo metaInfo

    MetaInfoProvider(MetaInfo metaInfo) {
        this.metaInfo = metaInfo
    }

    @Override
    List<MetaInfo> getMetaInfos() throws ComponentException {
        return [metaInfo]
    }
}
