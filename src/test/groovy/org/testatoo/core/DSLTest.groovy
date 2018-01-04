/**
 * Copyright © 2017 Ovea (d.avenante@gmail.com)
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
package org.testatoo.core

import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.bundle.stub.ButtonStub
import org.testatoo.bundle.stub.datagrid.CellStub
import org.testatoo.bundle.stub.datagrid.ColumnStub
import org.testatoo.bundle.stub.datagrid.DataGridStub
import org.testatoo.bundle.stub.DropDownStub
import org.testatoo.bundle.stub.FormStub
import org.testatoo.bundle.stub.GroupStub
import org.testatoo.bundle.stub.ItemStub
import org.testatoo.bundle.stub.LinkStub
import org.testatoo.bundle.stub.ListBoxStub
import org.testatoo.bundle.stub.datagrid.RowStub
import org.testatoo.bundle.stub.field.TextFieldStub
import org.testatoo.core.component.Button
import org.testatoo.core.component.Dropdown
import org.testatoo.core.component.Form
import org.testatoo.core.component.Group
import org.testatoo.core.component.Heading
import org.testatoo.core.component.Item
import org.testatoo.core.component.Link
import org.testatoo.core.component.ListBox
import org.testatoo.core.component.Panel
import org.testatoo.core.component.Radio
import org.testatoo.core.component.datagrid.Cell
import org.testatoo.core.component.datagrid.Column
import org.testatoo.core.component.datagrid.DataGrid
import org.testatoo.core.component.datagrid.Row
import org.testatoo.core.component.field.ColorField
import org.testatoo.core.component.field.DateField
import org.testatoo.core.component.field.DateTimeField
import org.testatoo.core.component.field.EmailField
import org.testatoo.core.component.field.MonthField
import org.testatoo.core.component.field.NumberField
import org.testatoo.core.component.field.PasswordField
import org.testatoo.core.component.field.PhoneField
import org.testatoo.core.component.field.SearchField
import org.testatoo.core.component.field.TextField
import org.testatoo.core.component.field.TimeField
import org.testatoo.core.component.field.URLField
import org.testatoo.core.component.field.WeekField

import static org.junit.Assert.*
import org.testatoo.bundle.stub.CheckBoxStub
import org.testatoo.bundle.stub.field.RangeFieldStub
import org.testatoo.core.component.CheckBox
import org.testatoo.core.component.Component
import org.testatoo.core.component.field.RangeField

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*
import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class DSLTest {
    private static MetaDataProvider meta
    private static MetaInfo metaInfo = new MetaInfo(id: 'id', node: 'node')

    @BeforeClass
    static void before() {
        meta = mock(MetaDataProvider)
        when(meta.metaInfo(any(Component))).thenReturn(metaInfo)
    }

    @Test
    void should_verify_states_on_components() {
        Component cmp = spy(new Component(meta))

        doReturn(true).when(cmp).available()
        doReturn(true).when(cmp).enabled()
        doReturn(true).when(cmp).visible()

        cmp.should {
            be available
            be enabled
            be visible
        }

        doReturn(false).when(cmp).available()
        doReturn(false).when(cmp).enabled()
        doReturn(false).when(cmp).visible()

        cmp.should {
            be missing
            be disabled
            be hidden
        }

        CheckBox checkbox = spy(new CheckBoxStub())
        checkbox.meta = meta

        doReturn(false).when(checkbox).checked()
        checkbox.should { be unchecked }

        check checkbox
        verify(checkbox, times(1)).check()

        doReturn(true).when(checkbox).checked()
        checkbox.should { be checked }

        uncheck checkbox
        verify(checkbox, times(1)).uncheck()

        RangeField field = spy(new RangeFieldStub())
        field.meta = meta

        doReturn(true).when(field).empty()
        field.should { be empty }

        fill field with 'Some value'
        verify(field, times(1)).value('Some value')

        set field to 10
        verify(field, times(1)).value(10)

        doReturn(false).when(field).empty()
        field.should { be filled }

        doReturn(true).when(field).valid()
        field.should { be valid }

        doReturn(false).when(field).valid()
        field.should { be invalid }

        doReturn(false).when(field).required()
        field.should { be optional }

        doReturn(true).when(field).required()
        field.should { be required }

        doReturn(true).when(field).inRange()
        field.should { be inRange }

        doReturn(false).when(field).inRange()
        field.should { be outOfRange }


        doReturn(true).when(field).readOnly()
        field.should { be readOnly }

        doReturn(true).when(field).focused()
        field.should { be focused }

        doReturn(false).when(field).readOnly()
        try {
            field.should { be readOnly }
            fail()
        } catch (AssertionError e) {
            // Good
        }

        doReturn(false).when(field).focused()
        try {
            field.should { be focused }
            fail()
        } catch (AssertionError e) {
            // Good
        }

        Item item = spy(new ItemStub())
        item.meta = meta

        doReturn(false).when(item).selected()
        item.should { be unselected }

        select item
        verify(item, times(1)).select()

        doReturn(true).when(item).selected()
        item.should { be selected }

        deselect item
        verify(item, times(1)).unselect()
    }

    @Test
    void should_verify_properties_on_components() {
        RangeField field = spy(new RangeFieldStub())
        field.meta = meta

        doReturn('Label').when(field).label()
        field.should { have label('Label') }

        doReturn('placeholder').when(field).placeholder()
        field.should { have placeholder('placeholder') }

        doReturn(5).when(field).minimum()
        doReturn(10).when(field).maximum()
        field.should {
            have minimum(5)
            have maximum(10)
        }

        doReturn(2).when(field).step()
        field.should { have step(2) }

        doReturn('Value').when(field).value()
        field.should { have value('Value')}

        doReturn(true).when(field).focused()
        field.should { have focus }

        TextFieldStub other_field = spy(new TextFieldStub())
        other_field.meta = meta

        doReturn(25).when(other_field).length()
        other_field.should { have length(25)}

        Button button = spy(new ButtonStub())
        button.meta = meta

        doReturn('Text').when(button).text()
        button.should { have text('Text') }

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

        Link link = spy(new LinkStub())
        link.meta = meta

        doReturn('http://reference').when(link).reference()
        link.should { have reference('http://reference') }

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

        ListBox listBox = spy(new ListBoxStub())
        listBox.meta = meta

        doReturn([item_1, item_2]).when(listBox).selectedItems()
        listBox.should {
            have selectedItems(item_1, item_2)
            have selectedItems('Item 1', 'Item 2')
        }
    }

    @Test
    void should_verify_contained_components_on_component() {
        Component cmp = spy(new Component(meta))
        config.evaluator = mock(Evaluator)

        when(config.evaluator.getJson(any(String))).thenReturn([])
        cmp.should { contain mock(Button) }
    }

    @Test
    void should_be_able_to_visit() {
        config.evaluator = mock(Evaluator)

        verify(config.evaluator, times(0)).open('http://myUrl')
        visit('http://myUrl')
        verify(config.evaluator, times(1)).open('http://myUrl')
    }

    @Test
    void should_be_able_to_select_items_in_components_containing_items() {
        ListBox listBox = spy(new ListBoxStub())
        listBox.meta = meta

        Item item_1 = spy(new ItemStub())
        item_1.meta = meta

        Item item_2 = spy(new ItemStub())
        item_2.meta = meta

        doReturn([item_1, item_2]).when(listBox).items()
        listBox.select(item_1)
        verify(item_1, times(1)).select()
        verify(item_2, times(0)).select()

        listBox.unselect(item_1)
        verify(item_1, times(1)).unselect()
        verify(item_2, times(0)).unselect()

        reset(item_1)
        reset(item_2)
        doReturn('Item_1').when(item_1).value()
        doReturn('Item_2').when(item_2).value()

        listBox.select('Item_2')
        verify(item_1, times(0)).select()
        verify(item_2, times(1)).select()

        listBox.unselect('Item_1', 'Item_2')
        verify(item_1, times(1)).unselect()
        verify(item_2, times(1)).unselect()
    }

    @Test
    void should_be_able_to_submit_and_reset_form() {
        Form form = spy(new FormStub())
        form.meta = meta

        Testatoo.reset form // Explicit call to forbid Mockito reset method call
        verify(form, times(1)).reset()

        submit form
        verify(form, times(1)).submit()
    }

    @Test
    void should_be_able_to_type_text() {
        config.evaluator = mock(Evaluator)

        verify(config.evaluator, times(0)).type(['data'])
        type('data')
        verify(config.evaluator, times(1)).type(['data'])
    }

    @Test
    void should_have_on_as_placeholder() {
        CheckBox checkBox = spy(new CheckBoxStub())
        assert on(checkBox).is(checkBox)
    }

    @Test
    void should_have_generic_components_factory() {
        config.evaluator = mock(Evaluator)

        when(config.evaluator.metaInfo("\$('ButtonStub')")).thenReturn([metaInfo])
        Button button = button 'Ok'
        button.should { have text('Ok') }

        when(config.evaluator.metaInfo("\$('RadioStub')")).thenReturn([metaInfo])
        Radio radio = radio 'Radio Label'
        radio.should { have label('Radio Label') }

        when(config.evaluator.metaInfo("\$('CheckBoxStub')")).thenReturn([metaInfo])
        CheckBox checkBox = checkbox 'Checkbox Label'
        checkBox.should { have label('Checkbox Label') }

        when(config.evaluator.metaInfo("\$('DropDownStub')")).thenReturn([metaInfo])
        Dropdown dropdown = dropdown 'DropDown Label'
        dropdown.should { have label('DropDown Label') }

        when(config.evaluator.metaInfo("\$('ListBoxStub')")).thenReturn([metaInfo])
        ListBox listBox = listBox 'ListBox Label'
        listBox.should { have label('ListBox Label') }

        when(config.evaluator.metaInfo("\$('GroupStub')")).thenReturn([metaInfo])
        Group group = group 'Group Value'
        group.should { have value('Group Value') }

        when(config.evaluator.metaInfo("\$('ItemStub')")).thenReturn([metaInfo])
        Item item = item 'Item Value'
        item.should { have value('Item Value') }

        when(config.evaluator.metaInfo("\$('HeadingStub')")).thenReturn([metaInfo])
        Heading heading = heading 'Heading Text'
        heading.should { have text('Heading Text') }

        when(config.evaluator.metaInfo("\$('PanelStub')")).thenReturn([metaInfo])
        Panel panel = panel 'Panel Title'
        panel.should { have title('Panel Title') }

        when(config.evaluator.metaInfo("\$('LinkStub')")).thenReturn([metaInfo])
        Link link = link 'Link Text'
        link.should { have text('Link Text') }

        when(config.evaluator.metaInfo("\$('PasswordFieldStub')")).thenReturn([metaInfo])
        PasswordField password = passwordField 'Password Field Label'
        password.should { have label('Password Field Label') }

        password = passwordField 'Password Field Placeholder'
        password.should { have placeholder('Password Field Placeholder') }

        when(config.evaluator.metaInfo("\$('TextFieldStub')")).thenReturn([metaInfo])
        TextField text = textField 'Text Field Label'
        text.should { have label('Text Field Label') }

        text = textField 'Text Field Placeholder'
        text.should { have placeholder('Text Field Placeholder') }

        when(config.evaluator.metaInfo("\$('SearchFieldStub')")).thenReturn([metaInfo])
        SearchField search = searchField 'Search Field Label'
        search.should { have label('Search Field Label') }

        search = searchField 'Search Field Placeholder'
        search.should { have placeholder('Search Field Placeholder') }

        when(config.evaluator.metaInfo("\$('EmailFieldStub')")).thenReturn([metaInfo])
        EmailField email = emailField 'Email Field Label'
        email.should { have label('Email Field Label') }

        email = emailField 'Email Field Placeholder'
        email.should { have placeholder('Email Field Placeholder') }

        when(config.evaluator.metaInfo("\$('UrlFieldStub')")).thenReturn([metaInfo])
        URLField url = urlField 'Url Field Label'
        url.should { have label('Url Field Label') }

        url = urlField 'Url Field Placeholder'
        url.should { have placeholder('Url Field Placeholder') }

        when(config.evaluator.metaInfo("\$('NumberFieldStub')")).thenReturn([metaInfo])
        NumberField number = numberField 'Number Field Label'
        number.should { have label('Number Field Label') }

        number = numberField 'Number Field Placeholder'
        number.should { have placeholder('Number Field Placeholder') }

        when(config.evaluator.metaInfo("\$('RangeFieldStub')")).thenReturn([metaInfo])
        RangeField range = rangeField 'Range Field Label'
        range.should { have label('Range Field Label') }

        range = rangeField 'Range Field Placeholder'
        range.should { have placeholder('Range Field Placeholder') }

        when(config.evaluator.metaInfo("\$('DateFieldStub')")).thenReturn([metaInfo])
        DateField date = dateField 'Date Field Label'
        date.should { have label('Date Field Label') }

        date = dateField 'Date Field Placeholder'
        date.should { have placeholder('Date Field Placeholder') }

        when(config.evaluator.metaInfo("\$('ColorFieldStub')")).thenReturn([metaInfo])
        ColorField color = colorField 'Color Field Label'
        color.should { have label('Color Field Label') }

        color = colorField 'Color Field Placeholder'
        color.should { have placeholder('Color Field Placeholder') }

        when(config.evaluator.metaInfo("\$('DateTimeFieldStub')")).thenReturn([metaInfo])
        DateTimeField dateTime = dateTimeField 'DateTime Field Label'
        dateTime.should { have label('DateTime Field Label') }

        dateTime = dateTimeField 'DateTime Field Placeholder'
        dateTime.should { have placeholder('DateTime Field Placeholder') }

        when(config.evaluator.metaInfo("\$('MonthFieldStub')")).thenReturn([metaInfo])
        MonthField month = monthField 'Month Field Label'
        month.should { have label('Month Field Label') }

        month = monthField 'Month Field Placeholder'
        month.should { have placeholder('Month Field Placeholder') }

        when(config.evaluator.metaInfo("\$('PhoneFieldStub')")).thenReturn([metaInfo])
        PhoneField phone = phoneField 'Phone Field Label'
        phone.should { have label('Phone Field Label') }

        phone = phoneField 'Phone Field Placeholder'
        phone.should { have placeholder('Phone Field Placeholder') }

        when(config.evaluator.metaInfo("\$('TimeFieldStub')")).thenReturn([metaInfo])
        TimeField time = timeField 'Time Field Label'
        time.should { have label('Time Field Label') }

        time = timeField 'Time Field Placeholder'
        time.should { have placeholder('Time Field Placeholder') }

        when(config.evaluator.metaInfo("\$('WeekFieldStub')")).thenReturn([metaInfo])
        WeekField week = weekField 'Week Field Label'
        week.should { have label('Week Field Label') }

        week = weekField 'Week Field Placeholder'
        week.should { have placeholder('Week Field Placeholder') }
    }
}