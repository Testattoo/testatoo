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
/**
 * @author David Avenante (d.avenante@gmail.com)
 */
trait InputSupport implements ValueSupport {

    String getPlaceholder() {
        eval("it.prop('placeholder')")
    }

    boolean isEmpty() {
        Boolean.parseBoolean(eval("\$.trim(it.val()).length == 0"))
    }

    boolean isFilled() {
        !empty
    }

    boolean isReadOnly() {
        Boolean.parseBoolean(eval("it.prop('readonly')"))
    }

    boolean isRequired() {
        Boolean.parseBoolean(eval("it.prop('required')"))
    }

    boolean isOptional() {
        !required
    }

    boolean isValid() {
        !invalid
    }

    boolean isInvalid() {
        Boolean.parseBoolean(eval("it.is(':invalid')"))
    }

//    Value
}
