package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.testatoo.core.component.datagrid.Cell
import org.testatoo.core.support.property.CellSupport
import org.testatoo.hamcrest.PropertyMatcher
import org.testatoo.hamcrest.matcher.property.dummy.DummyCell

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class CellMatcher extends PropertyMatcher<CellSupport> {
    private List<String> values = new ArrayList<>()
    private List<Cell> cells = new ArrayList<>()

    CellMatcher(String... values) {
        this.values = values
    }

    CellMatcher(Cell... cells) {
        this.cells = cells
    }

    @Override
    protected boolean matchesSafely(CellSupport component) {
        if (values) {
            cells.clear()
            values.each { cells.add(new DummyCell(it)) }
        }
        values.clear()
        cells.each { values.add(String.valueOf(it.value())) }
        component.cells().size() == cells.size() && component.cells().containsAll(cells)
    }

    @Override
    void describeTo(Description description) {
        List<String> expectedCells = new ArrayList<>()
        cells.each { expectedCells.add(String.valueOf(it.value())) }

        description.appendText('cell(s) [')
        description.appendText(expectedCells.join(', '))
        description.appendText(']')
    }

    @Override
    protected void describeMismatchSafely(CellSupport component, Description mismatchDescription) {
        List<String> componentCells = new ArrayList<>()
        component.cells().each { componentCells.add(String.valueOf(it.value())) }

        mismatchDescription.appendText('has cell(s) [')
        mismatchDescription.appendText(componentCells.join(', '))
        mismatchDescription.appendText(']')
    }
}
