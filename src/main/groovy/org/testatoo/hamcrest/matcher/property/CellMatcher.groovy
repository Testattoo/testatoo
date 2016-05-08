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
        description.appendText('cell(s) ')
        description.appendValueList('[', ', ', ']', expectedCells)
    }

    @Override
    protected void describeMismatchSafely(CellSupport component, Description mismatchDescription) {
        List<String> componentCells = new ArrayList<>()
        component.cells().each { componentCells.add(String.valueOf(it.value())) }

        mismatchDescription.appendText('has cell(s) ')
        mismatchDescription.appendValueList('[', ', ', ']', componentCells)
    }
}
