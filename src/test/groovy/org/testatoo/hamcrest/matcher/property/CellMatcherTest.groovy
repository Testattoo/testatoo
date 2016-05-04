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
