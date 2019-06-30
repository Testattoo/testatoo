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
package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.testatoo.core.component.Item
import org.testatoo.core.support.property.SelectedItemsSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.junit.jupiter.api.Assertions.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.has
import static org.testatoo.hamcrest.Matchers.selectedItems

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@DisplayName("Selected Items Property Matcher")
class SelectedItemsMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        SelectedItemsSupport cmp = mock(SelectedItemsSupport)
        Item itemSelected_1 = mock(Item)
        Item itemSelected_2 = mock(Item)

        when(itemSelected_1.value()).thenReturn('selected_value_1')
        when(itemSelected_2.value()).thenReturn('selected_value_2')
        when(cmp.selectedItems()).thenReturn([itemSelected_1, itemSelected_2])

        assertThat(cmp, has(selectedItems('selected_value_1', 'selected_value_2')))
        assertThat(cmp, has(selectedItems(itemSelected_1, itemSelected_1)))

        try {
            assertThat(cmp, has(selectedItems('no_selected_item_1', 'no_selected_item_2')))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription()
            description.appendText('\nExpected: has selected item(s) ["no_selected_item_1", "no_selected_item_2"]')
                .appendText('\n     but: has selected item(s) ["selected_value_1", "selected_value_2"]')

            assert e.message == description.toString()
        }

        Item item_1 = mock(Item)
        Item item_2 = mock(Item)
        when(item_1.value()).thenReturn('no_selected_item_1')
        when(item_2.value()).thenReturn('no_selected_item_2')

        try {
            assertThat(cmp, has(selectedItems(item_1, item_2)))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription()
            description.appendText('\nExpected: has selected item(s) ["no_selected_item_1", "no_selected_item_2"]')
                .appendText('\n     but: has selected item(s) ["selected_value_1", "selected_value_2"]')

            assert e.message == description.toString()
        }
    }
}
