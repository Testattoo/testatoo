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
package org.testatoo.bundle.html5.components.table

import org.junit.BeforeClass
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.WebDriverConfig
import org.testatoo.bundle.html5.table.Table
import org.testatoo.bundle.html5.table.Tr
import org.testatoo.core.component.datagrid.Cell
import org.testatoo.core.component.datagrid.Column
import org.testatoo.core.component.datagrid.DataGrid

import static org.testatoo.core.Testatoo.$
import static org.testatoo.core.Testatoo.getBrowser

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class TableTest {
    @ClassRule
    public static WebDriverConfig driver = new WebDriverConfig()

    @BeforeClass
    public static void before() {
        browser.open 'http://localhost:8080/components.html'
    }

    @Test
    public void should_have_expected_behaviours() {
        assert Table in DataGrid

        Table data_grid = $('#data_grid') as Table

        assert data_grid.columns().size() == 4
        assert data_grid.rows().size() == 4

        List<Column> columns = data_grid.columns()

        assert columns[0].title() == ''
        assert columns[1].title() == 'Column 1 title'
        assert columns[2].title() == 'Column 2 title'
        assert columns[3].title() == 'Column 3 title'

        assert columns[1].cells().size() == 4

        List<Cell> cells = columns[1].cells()

        assert cells[0].value() == 'cell 11'
        assert cells[1].value() == 'cell 21'
        assert cells[2].value() == 'cell 31'
        assert cells[3].value() == 'cell 41'

        columns[2].cells()[3].value() == 'cell 42'
        columns[2].cell('cell 42').value() == 'cell 42'

        List<Tr> rows = data_grid.rows()
        assert rows[0].cells().size() == 3

        assert rows[0].title() == 'Row 1'
        assert rows[1].title() == 'Row 2'
        assert rows[2].title() == 'Row 3'
        assert rows[3].title() == 'Row 4'

        cells = rows[1].cells()

        assert cells[0].value() == 'cell 21'
        assert cells[1].value() == 'cell 22'
        assert cells[2].value() == 'cell 23'

        rows[2].cells()[1].value() == 'cell 32'
        rows[2].cell('cell 32').value() == 'cell 32'
    }

    @Test
    public void should_access_to_column_by_title() {
        DataGrid data_grid = $('#data_grid') as Table

        data_grid.column('').title() == ''
        data_grid.column('Column 1 title').title() == 'Column 1 title'
        data_grid.column('Column 2 title').title() == 'Column 2 title'
        data_grid.column('Column 3 title').title() == 'Column 3 title'
    }

    @Test
    public void should_access_to_row_by_title() {
        DataGrid data_grid = $('#data_grid') as Table

        data_grid.row('Row 1').title() == 'Row 1'
        data_grid.row('Row 2').title() == 'Row 2'
        data_grid.row('Row 3').title() == 'Row 3'
        data_grid.row('Row 4').title() == 'Row 4'
    }
}
