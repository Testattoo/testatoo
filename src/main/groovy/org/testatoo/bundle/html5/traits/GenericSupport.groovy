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
package org.testatoo.bundle.html5.traits

import org.testatoo.core.ComponentException

import static org.testatoo.core.Testatoo.config

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
trait GenericSupport {

    boolean isEnabled() {
        !disabled
    }

    boolean isDisabled() {
        Boolean.parseBoolean(config.evaluator.eval(this, "it.is(':disabled') || !!it.attr('disabled')"))
    }

    boolean isAvailable() {
        !missing
    }

    boolean isMissing() {
        try {
            this.meta.idProvider.getMetaInfos()
            return false
        } catch (ComponentException ignored) {
            return true
        }

    }

    boolean isHidden() {
        Boolean.parseBoolean(config.evaluator.eval(this, "it.is(':hidden')"))
    }

    boolean isVisible() {
        !hidden
    }

}