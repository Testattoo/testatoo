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
import org.testatoo.core.component.datagrid.Column
import org.testatoo.core.support.property.ColumnSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.columns
import static org.testatoo.hamcrest.Matchers.has

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ColumnMatcherTest {
    @Test
    public void should_have_expected_matcher() {
        ColumnSupport cmp = mock(ColumnSupport)

        Column column_1 = mock(Column)
        when(column_1.title()).thenReturn('column_1')
        Column column_2 = mock(Column)
        when(column_2.title()).thenReturn('column_2')
        Column column_3 = mock(Column)
        when(column_3.title()).thenReturn('column_3')

        when(cmp.columns()).thenReturn([column_1, column_2])

        assertThat(cmp, has(columns('column_1', 'column_2')))
        assertThat(cmp, has(columns(column_1, column_2)))

        try {
            assertThat(cmp, has(columns('column_1', 'column_3')))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: has column(s) ["column_1", "column_3"]')
                    .appendText('\n     but: has column(s) ["column_1", "column_2"]');

            assert e.message == description.toString()
        }

        try {
            assertThat(cmp, has(columns(column_1, column_3)))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: has column(s) ["column_1", "column_3"]')
                    .appendText('\n     but: has column(s) ["column_1", "column_2"]');

            assert e.message == description.toString()
        }
    }
}
