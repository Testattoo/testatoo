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
package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.component.Item
import org.testatoo.core.support.property.SelectedItemSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.has
import static org.testatoo.hamcrest.Matchers.selectedItem

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class SelectedItemMatcherTest {
    @Test
    void should_have_expected_matcher() {
        SelectedItemSupport cmp = mock(SelectedItemSupport)
        Item itemSelected = mock(Item)

        when(itemSelected.value()).thenReturn('selected')
        when(cmp.selectedItem()).thenReturn(itemSelected)

        assertThat(cmp, has(selectedItem('selected')))
        assertThat(cmp, has(selectedItem(itemSelected)))

        try {
            assertThat(cmp, has(selectedItem('no selected')))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription()
            description.appendText('\nExpected: has selected item "no selected"')
                    .appendText('\n     but: has selected item "selected"')

            assert e.message == description.toString()
        }

        Item item = mock(Item)
        when(item.value()).thenReturn('no selected')

        try {
            assertThat(cmp, has(selectedItem(item)))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription()
            description.appendText('\nExpected: has selected item "no selected"')
                    .appendText('\n     but: has selected item "selected"')

            assert e.message == description.toString()
        }
    }
}