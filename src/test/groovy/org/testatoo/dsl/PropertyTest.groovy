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
package org.testatoo.dsl

import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.bundle.stub.ButtonStub
import org.testatoo.bundle.stub.DropDownStub
import org.testatoo.bundle.stub.GroupStub
import org.testatoo.bundle.stub.ItemStub
import org.testatoo.bundle.stub.LinkStub
import org.testatoo.bundle.stub.ListBoxStub
import org.testatoo.bundle.stub.datagrid.CellStub
import org.testatoo.bundle.stub.datagrid.ColumnStub
import org.testatoo.bundle.stub.datagrid.DataGridStub
import org.testatoo.bundle.stub.datagrid.RowStub
import org.testatoo.bundle.stub.field.RangeFieldStub
import org.testatoo.bundle.stub.field.TextFieldStub
import org.testatoo.core.Evaluator
import org.testatoo.core.MetaDataProvider
import org.testatoo.core.MetaInfo
import org.testatoo.core.component.Button
import org.testatoo.core.component.Component
import org.testatoo.core.component.Dropdown
import org.testatoo.core.component.Group
import org.testatoo.core.component.Item
import org.testatoo.core.component.Link
import org.testatoo.core.component.ListBox
import org.testatoo.core.component.datagrid.Cell
import org.testatoo.core.component.datagrid.Column
import org.testatoo.core.component.datagrid.DataGrid
import org.testatoo.core.component.datagrid.Row
import org.testatoo.core.component.field.RangeField
import org.testatoo.core.component.field.TextField

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.when
import static org.testatoo.core.Testatoo.cells
import static org.testatoo.core.Testatoo.cells
import static org.testatoo.core.Testatoo.columns
import static org.testatoo.core.Testatoo.columns
import static org.testatoo.core.Testatoo.getConfig
import static org.testatoo.core.Testatoo.groups
import static org.testatoo.core.Testatoo.groups
import static org.testatoo.core.Testatoo.items
import static org.testatoo.core.Testatoo.items
import static org.testatoo.core.Testatoo.label
import static org.testatoo.core.Testatoo.length
import static org.testatoo.core.Testatoo.maximum
import static org.testatoo.core.Testatoo.minimum
import static org.testatoo.core.Testatoo.placeholder
import static org.testatoo.core.Testatoo.reference
import static org.testatoo.core.Testatoo.rows
import static org.testatoo.core.Testatoo.rows
import static org.testatoo.core.Testatoo.selectedItem
import static org.testatoo.core.Testatoo.selectedItem
import static org.testatoo.core.Testatoo.selectedItems
import static org.testatoo.core.Testatoo.step
import static org.testatoo.core.Testatoo.text
import static org.testatoo.core.Testatoo.title
import static org.testatoo.core.Testatoo.value

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class PropertyTest {
    private static MetaDataProvider meta
    private static MetaInfo metaInfo = new MetaInfo(id: 'id', node: 'node')

    @BeforeClass
    static void before() {
        meta = mock(MetaDataProvider)
        when(meta.metaInfo(any(Component))).thenReturn(metaInfo)
        config.evaluator = mock(Evaluator)
    }

    @Test
    void should_support_label_and_placeholder() {
        TextField field = spy(new TextFieldStub())
        field.meta = meta

        doReturn('Label').when(field).label()
        field.should { have label('Label') }

        doReturn('placeholder').when(field).placeholder()
        field.should { have placeholder('placeholder') }
    }

    @Test
    void should_support_maximum_minimum_and_step() {
        RangeField field = spy(new RangeFieldStub())
        field.meta = meta

        doReturn(5).when(field).minimum()
        doReturn(10).when(field).maximum()
        field.should {
            have minimum(5)
            have maximum(10)
        }

        doReturn(2).when(field).step()
        field.should { have step(2) }
    }

    @Test
    void should_support_value() {
        TextField field = spy(new TextFieldStub())
        field.meta = meta

        doReturn('Value').when(field).value()
        field.should { have value('Value') }
    }

    @Test
    void should_support_text() {
        Button button = spy(new ButtonStub())
        button.meta = meta

        doReturn('Text').when(button).text()
        button.should { have text('Text') }
    }

    @Test
    void should_support_length() {
        TextFieldStub other_field = spy(new TextFieldStub())
        other_field.meta = meta

        doReturn(25).when(other_field).length()
        other_field.should { have length(25) }
    }

    @Test
    void should_support_reference() {
        Link link = spy(new LinkStub())
        link.meta = meta

        doReturn('http://reference').when(link).reference()
        link.should { have reference('http://reference') }
    }

    @Test
    void should_support_datagrid() {
        DataGrid datagrid = spy(new DataGridStub())
        datagrid.meta = meta

        Column column_1 = spy(new ColumnStub())
        column_1.meta = meta
        Column column_2 = spy(new ColumnStub())
        column_2.meta = meta

        doReturn([column_1, column_2]).when(datagrid).columns()
        datagrid.should { have columns(column_1, column_2) }

        doReturn('Column 1').when(column_1).title()
        doReturn('Column 2').when(column_2).title()
        datagrid.should { have columns('Column 1', 'Column 2') }

        Row row_1 = spy(new RowStub())
        row_1.meta = meta
        Row row_2 = spy(new RowStub())
        row_2.meta = meta

        doReturn([row_1, row_2]).when(datagrid).rows()
        datagrid.should { have rows(row_1, row_2) }

        doReturn('Row 1').when(row_1).title()
        doReturn('Row 2').when(row_2).title()
        datagrid.should { have rows('Row 1', 'Row 2') }

        row_1.should { have title('Row 1') }

        Cell cell_1 = spy(new CellStub())
        cell_1.meta = meta
        Cell cell_2 = spy(new CellStub())
        cell_2.meta = meta

        doReturn([cell_1, cell_2]).when(row_1).cells()
        row_1.should { have cells(cell_1, cell_2) }

        doReturn('Cell 1').when(cell_1).value()
        doReturn('Cell 2').when(cell_2).value()
        row_1.should { have cells('Cell 1', 'Cell 2') }
    }

    @Test
    void should_support_dropdown() {
        Dropdown dropdown = spy(new DropDownStub())
        dropdown.meta = meta

        Group group_1 = spy(new GroupStub())
        group_1.meta = meta
        Group group_2 = spy(new GroupStub())
        group_2.meta = meta

        doReturn([group_1, group_2]).when(dropdown).groups()
        dropdown.should { have groups(group_1, group_2) }

        doReturn('Group 1').when(group_1).value()
        doReturn('Group 2').when(group_2).value()
        dropdown.should { have groups('Group 1', 'Group 2') }

        Item item_1 = spy(new ItemStub())
        item_1.meta = meta

        Item item_2 = spy(new ItemStub())
        item_2.meta = meta

        doReturn([item_1, item_2]).when(dropdown).items()
        dropdown.should { have items(item_1, item_2) }

        doReturn('Item 1').when(item_1).value()
        doReturn('Item 2').when(item_2).value()
        dropdown.should { have items('Item 1', 'Item 2') }

        doReturn(item_1).when(dropdown).selectedItem()
        dropdown.should {
            have selectedItem(item_1)
            have selectedItem('Item 1')
        }
    }

    @Test
    void should_support_listbox() {
        ListBox listBox = spy(new ListBoxStub())
        listBox.meta = meta

        Item item_1 = spy(new ItemStub())
        item_1.meta = meta

        Item item_2 = spy(new ItemStub())
        item_2.meta = meta

        doReturn('Item 1').when(item_1).value()
        doReturn('Item 2').when(item_2).value()
        doReturn([item_1, item_2]).when(listBox).selectedItems()
        listBox.should {
            have selectedItems(item_1, item_2)
            have selectedItems('Item 1', 'Item 2')
        }
    }
}
