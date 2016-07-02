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
package org.testatoo.core.component

import org.junit.Test
import org.junit.experimental.categories.Category
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.category.NoGui

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
@Category(NoGui)
class GroupTest {
    @Test
    public void should_have_expected_inheritance() {
        assert Group in Component
    }

    @Test
    public void should_have_equality_and_hashcode_based_on_value() {
        Group group_1 = new TestGroup('value_1')
        Group group_2 = new TestGroup('value_2')
        Group group_3 = new TestGroup('value_1')

        assert !group_1.equals(group_2)
        assert group_1.equals(group_3)

        assert group_1.hashCode() == 'value_1'.hashCode()
    }

    private class TestGroup extends Group {
        private String value;

        public TestGroup(String value) {
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
