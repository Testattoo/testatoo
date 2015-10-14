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
package org.testatoo.core.traits

import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
trait RangeSupport implements ValueSupport {

    Object getMinimun() {
        config.evaluator.eval(id, "it.prop('min')") as BigDecimal
    }

    Object getMaximum() {
        config.evaluator.eval(id, "it.prop('max')") as BigDecimal
    }

    Object getStep() {
        config.evaluator.eval(id, "it.prop('step')") as BigDecimal
    }

    boolean isInRange() {
        !outOfRange
    }

    boolean isOutOfRange() {
        config.evaluator.check(id, "it.is(':out-of-range')")
    }
}