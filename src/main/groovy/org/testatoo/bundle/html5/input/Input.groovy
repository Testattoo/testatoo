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
package org.testatoo.bundle.html5.input

import org.testatoo.core.ComponentException

import static org.testatoo.bundle.html5.helper.LabelHelper.label
import static org.testatoo.bundle.html5.helper.ValidityHelper.invalid
import static org.testatoo.bundle.html5.helper.ValidityHelper.valid
import static org.testatoo.core.Testatoo.config
import static org.testatoo.core.input.Key.BACK_SPACE

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
trait Input {
    String placeholder() {
        config.evaluator.eval(id(), "it.prop('placeholder')")
    }

    boolean empty() {
        config.evaluator.check(id(), "\$.trim(it.val()).length == 0")
    }

    boolean filled() {
        !empty()
    }

    boolean readOnly() {
        config.evaluator.check(id(), "it.prop('readonly')")
    }

    boolean required() {
        config.evaluator.check(id(), "it.prop('required')")
    }

    boolean optional() {
        !required()
    }

    void value(Object value) {
        if (this.disabled()) {
            throw new ComponentException("${this.class.simpleName} ${this} is disabled and cannot be filled")
        }
        config.evaluator.trigger(this.id(), 'blur')
        clear()
        config.evaluator.type([String.valueOf(value)])
        config.evaluator.trigger(this.id(), 'blur')
    }

    String label() {
        label(this)
    }

    void clear() {
        this.click()
        config.evaluator.runScript("\$('#${id()}').val(' ').change()")
        config.evaluator.type([BACK_SPACE])
        config.evaluator.trigger(id(), 'blur')
        this.click()
    }

    Object value() {
        config.evaluator.eval(id(), "it.val()")
    }

    boolean valid() {
        valid(this)
    }

    boolean invalid() {
        invalid(this)
    }
}
