/**
 * Copyright © 2018 Ovea (d.avenante@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.bundle.stub.field

import org.testatoo.core.CssIdentifier
import org.testatoo.core.component.field.SearchField

@CssIdentifier('SearchFieldStub')
class SearchFieldStub extends SearchField {
    String placeholder() {
        return 'Search Field Placeholder'
    }

    void value(Object value) {
    }

    void clear() {
    }

    String label() {
        return 'Search Field Label'
    }

    Object length() {
        return null
    }

    Object value() {
        return null
    }

    boolean empty() {
        return false
    }

    boolean focused() {
        return false
    }

    boolean readOnly() {
        return false
    }

    boolean required() {
        return false
    }

    boolean valid() {
        return false
    }
}
