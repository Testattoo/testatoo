/**
 * Copyright (C) 2014 Ovea (dev@ovea.com)
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
package org.testatoo.bundle.html5

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.bundle.html5.datagrid.Cell
import org.testatoo.bundle.html5.datagrid.Column
import org.testatoo.bundle.html5.datagrid.DataGrid
import org.testatoo.bundle.html5.datagrid.Row
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.property.Properties.*
import static org.testatoo.core.state.States.*
import static org.testatoo.core.action.Actions.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class DataGridTest {

    @BeforeClass
    public static void setup() {
        evaluator = new WebDriverEvaluator(new FirefoxDriver())
        visit 'http://localhost:8080/components.html'
    }

    @AfterClass
    public static void tearDown() { evaluator.close() }

    @Test
    public void datagrid_should_have_expected_behaviours() {
        DataGrid data_grid = $('#data_grid') as DataGrid

        data_grid.should { be enabled }
        data_grid.should { be visible }

        data_grid.should { have 4.columns }
        assert data_grid.columns.size == 4

        data_grid.should { have 4.rows }
        assert data_grid.rows.size == 4

        List<Column> columns = data_grid.columns

        columns[0].should { have title('') }
        columns[1].should { have title('Column 1 title') }
        columns[2].should { have title('Column 2 title') }
        columns[3].should { have title('Column 3 title') }

        columns[1].should { have 4.cells }
        assert columns[1].cells.size == 4

        List<Cell> cells = columns[1].cells

        cells[0].should { have value('cell 11') }
        cells[1].should { have value('cell 21') }
        cells[2].should { have value('cell 31') }
        cells[3].should { have value('cell 41') }

        columns[2].cells[3].should { have value('cell 42') }

        List<Row> rows = data_grid.rows
        rows[0].should { have 3.cells }
        assert rows[0].cells.size == 3

        rows[0].should { have title('row 1') }
        rows[1].should { have title('row 2') }
        rows[2].should { have title('row 3') }
        rows[3].should { have title('row 4') }

        cells = rows[1].cells
        cells[0].should { have value('cell 21') }
        cells[1].should { have value('cell 22') }
        cells[2].should { have value('cell 23') }

        rows[2].cells[1].should { have value('cell 32') }
    }

    @Test
    public void should_access_to_column_by_title() {
        DataGrid data_grid = $('#data_grid') as DataGrid

        data_grid.column('').should { have title('') }
        data_grid.column('Column 1 title').should { have title('Column 1 title') }
        data_grid.column('Column 2 title').should { have title('Column 2 title') }
        data_grid.column('Column 3 title').should { have title('Column 3 title') }
    }
}
