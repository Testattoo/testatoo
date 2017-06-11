/**
 * Copyright © 2016 Ovea (d.avenante@gmail.com)
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

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.support.Selectable
import org.testatoo.core.support.UnSelectable
import org.testatoo.core.support.property.ValueSupport
import org.testatoo.core.support.state.SelectSupport

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ItemTest {
    @Test
    void should_have_expected_inheritance() {
        assert Item in Component
        assert Item in Selectable
        assert Item in UnSelectable
        assert Item in SelectSupport
        assert Item in ValueSupport
    }

    @Test
    void should_have_equality_and_hashcode_based_on_value() {
        Item item_1 = new TestItem('value_1')
        Item item_2 = new TestItem('value_2')
        Item item_3 = new TestItem('value_1')

        assert !item_1.equals(item_2)
        assert item_1.equals(item_3)

        assert item_1.hashCode() == 'value_1'.hashCode()
    }

    private class TestItem extends Item {
        private String value

        TestItem(String value) {
            this.value = value
        }

        void select() {}

        void unselect() {}

        Object value() {
            return value
        }

        boolean selected() {
            return false
        }
    }
}