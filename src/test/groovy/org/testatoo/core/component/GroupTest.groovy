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
package org.testatoo.core.component

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@DisplayName("Group")
class GroupTest {
    @Test
    @DisplayName("Should have expected inheritance")
    void inheritance() {
        assert Group in Component
    }

    @Test
    @DisplayName("Should have equality and hashcode based on group value")
    void equality_and_hashcode() {
        Group group_1 = new TestGroup('value_1')
        Group group_2 = new TestGroup('value_2')
        Group group_3 = new TestGroup('value_1')

        assert group_1 != group_2
        assert group_1 == group_3

        assert group_1.hashCode() == 'value_1'.hashCode()
    }

    private class TestGroup extends Group {
        private String value;

        TestGroup(String value) {
            this.value = value
        }

        List<Item> items() {
            return null
        }

        Item item(String value) {
            return null
        }

        Object value() {
            return value
        }
    }
}
