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
import org.testatoo.core.component.datagrid.Cell
import org.testatoo.core.support.property.CellSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.cells
import static org.testatoo.hamcrest.Matchers.has

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class CellMatcherTest {
    @Test
    public void should_have_expected_matcher() {
        CellSupport cmp = mock(CellSupport)

        Cell cell_1 = mock(Cell)
        when(cell_1.value()).thenReturn('cell_1')
        Cell cell_2 = mock(Cell)
        when(cell_2.value()).thenReturn('cell_2')
        Cell cell_3 = mock(Cell)
        when(cell_3.value()).thenReturn('cell_3')

        when(cmp.cells()).thenReturn([cell_1, cell_2])

        assertThat(cmp, has(cells('cell_1', 'cell_2')))
        assertThat(cmp, has(cells(cell_1, cell_2)))

        try {
            assertThat(cmp, has(cells('cell_1', 'cell_3')))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription()
            description.appendText('\nExpected: has cell(s) ["cell_1", "cell_3"]')
                    .appendText('\n     but: has cell(s) ["cell_1", "cell_2"]');

            assert e.message == description.toString()
        }

        try {
            assertThat(cmp, has(cells(cell_1, cell_3)))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: has cell(s) ["cell_1", "cell_3"]')
                    .appendText('\n     but: has cell(s) ["cell_1", "cell_2"]');

            assert e.message == description.toString()
        }
    }
}
