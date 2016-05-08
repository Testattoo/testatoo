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
package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.component.datagrid.Row
import org.testatoo.core.support.property.RowSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.has
import static org.testatoo.hamcrest.Matchers.rows

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class RowMatcherTest {
    @Test
    public void should_have_expected_matcher() {
        RowSupport cmp = mock(RowSupport)

        Row row_1 = mock(Row)
        when(row_1.title()).thenReturn('row_1')
        Row row_2 = mock(Row)
        when(row_2.title()).thenReturn('row_2')
        Row row_3 = mock(Row)
        when(row_3.title()).thenReturn('row_3')

        when(cmp.rows()).thenReturn([row_1, row_2])

        assertThat(cmp, has(rows('row_1', 'row_2')))
        assertThat(cmp, has(rows(row_1, row_2)))

        try {
            assertThat(cmp, has(rows('row_1', 'row_3')))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: has row(s) ["row_1", "row_3"]')
                    .appendText('\n     but: has row(s) ["row_1", "row_2"]');

            assert e.message == description.toString()
        }

        try {
            assertThat(cmp, has(rows(row_1, row_3)))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: has row(s) ["row_1", "row_3"]')
                    .appendText('\n     but: has row(s) ["row_1", "row_2"]');

            assert e.message == description.toString()
        }
    }
}
