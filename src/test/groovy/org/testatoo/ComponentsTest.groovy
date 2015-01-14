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
package org.testatoo

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Testatoo
import org.testatoo.core.component.*
import org.testatoo.core.component.datagrid.Cell
import org.testatoo.core.component.datagrid.Column
import org.testatoo.core.component.datagrid.DataGrid
import org.testatoo.core.component.datagrid.Row
import org.testatoo.core.component.input.*
import org.testatoo.core.component.list.Dropdown
import org.testatoo.core.component.list.GroupItem
import org.testatoo.core.component.list.ListBox
import org.testatoo.core.component.list.ListView
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator
import org.testatoo.core.property.Size
import org.testatoo.core.property.Text
import org.testatoo.core.property.Title
import org.testatoo.core.state.Selected

import static org.junit.Assert.fail
import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Mouse.clickOn
import static org.testatoo.core.property.Properties.*
import static org.testatoo.core.state.States.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ComponentsTest {

    @BeforeClass
    public static void setup() {
        Testatoo.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        open 'http://localhost:8080/components.html'
    }

    @AfterClass
    public static void tearDown() { evaluator.close() }

    @Test
    public void test_button() {
        // input type=button
        Button button = $('#button') as Button

        button.should { be enabled }
        button.should { be visible }

        button.should { have text('Button') }

        // input type=submit
        button = $('#submit') as Button
        button.should { have text('Submit') }

        // input type=reset
        button = $('#reset') as Button
        button.should { have text('Reset') }

        // button element
        button = $('#btn') as Button
        button.should { have text('My Button Text') }
    }

    @Test
    public void test_input_fields() {
        // Text field
        TextField textField = $('#text_field') as TextField

        textField.should { be enabled }
        textField.should { be visible }
        textField.should { be optional }

        textField.should { have label('Text') }
        textField.should { have label.containing('xt') }

        textField.should { have placeholder('Text') }
        textField.should { have placeholder.containing('xt') }
        textField.should { be empty }

        on textField enter 'some value'

        textField.should { have text('some value') }
        textField.should { have text.containing('value') }

        textField.should { have value('some value') }
        textField.should { have value.containing('value') }
        textField.should { be filled }

        reset textField
        textField.should { be empty }

        // TextArea is treated as TextField
        textField = $('#text_area_field') as TextField

        textField.should { be enabled }
        textField.should { be visible }

        PasswordField passwordField = $('#password_field') as PasswordField

        passwordField.should { be enabled }
        passwordField.should { be visible }

        passwordField.should { have label('Password') }
        passwordField.should { have text('?art') }

        EmailField emailField = $('#email_field') as EmailField
        emailField.should { be disabled }

        PhoneField phoneField = $('#phone_field') as PhoneField
        phoneField.should { be enabled }
        phoneField.should {
            have pattern('^((\\+\\d{1,3}(-| )?\\(?\\d\\)?(-| )?\\d{1,5})|(\\(?\\d{2,6}\\)?))(-| )?(\\d{3,4})(-| )?(\\d{4})(( x| ext)\\d{1,5}){0,1}$')
        }

        URLField urlField = $('#url_field') as URLField
        urlField.should { be enabled }

        SearchField searchField = $('#search_field') as SearchField
        searchField.should { be enabled }

        NumberField numberField = $('#number_field') as NumberField
        numberField.should { be enabled }
        numberField.should { have minimun(0) }
        numberField.should { have maximum(64) }
        numberField.should { have step(8) }

        RangeField rangeField = $('#range_field') as RangeField
        rangeField.should { be enabled }
        rangeField.should { have minimun(0)}
        rangeField.should { have maximum(50) }
        rangeField.should { have step(5) }

        ColorField colorField = $('#color_field') as ColorField
        colorField.should { be enabled }
        colorField.should { be optional }

        MonthField monthField = $('#month_field') as MonthField
        monthField.should { be enabled }
        monthField.should { be required }

        WeekField weekField = $('#week_field') as WeekField
        weekField.should { be enabled }

        DateField dateField = $('#date_field') as DateField
        dateField.should { be enabled }
        dateField.should { have minimun('2011-08-13') }
        dateField.should { have maximum('2012-06-25') }

        TimeField timeField = $('#time_field') as TimeField
        timeField.should { be enabled }

        DateTimeField dateTimeField = $('#datetime_field') as DateTimeField
        dateTimeField.should { be enabled }
    }

    @Test
    public void test_checkbox() {
        Checkbox checkBox = $('#checkbox') as Checkbox
        checkBox.should { be enabled }
        checkBox.should { be visible }

        checkBox.should { be unchecked }
        checkBox.should { have label('Check me out') }

        check checkBox
        checkBox.should { be checked }

        clickOn checkBox
        checkBox.should { be unchecked }
    }

    @Test
    public void test_radio() {
        Radio radio = $('#radio') as Radio;

        radio.should { be enabled }
        radio.should { be visible }
        radio.should { be checked }

        radio.should { have label('Radio label checked') }
    }

    @Test
    public void test_link() {
        Link link = $('#link') as Link
        link.should { be enabled }
        link.should { be visible }

        link.should { have text('Link to component page') }

        link.should { have reference.equalsTo('http://localhost:8080/components.html') }
        link.should { have reference('http://localhost:8080/components.html') }
        link.should { have reference.containing('component') }
    }

    @Test
    public void test_image() {
        Image image = $('#image') as Image
        image.should { be enabled }
        image.should { be visible }

        image.should { have source('http://localhost:8080/img/Montpellier.jpg') }
        image.should { have source.containing('Montpellier') }
    }

    @Test
    public void test_panel() {
        Panel panel = $('#panel') as Panel
        panel.should { be enabled }
        panel.should { be visible }

        panel.should { have title.equalsTo('') }

        panel = $('#disabled-panel') as Panel
        panel.should { be disabled }
    }

    // http://en.wikipedia.org/wiki/Combo_box
    @Test
    public void test_comboBox() {
        // Not available in HTML
    }

    // http://en.wikipedia.org/wiki/Drop-down_list
    @Test
    public void test_dropDown() {
        Dropdown dropDown = $('#elements') as Dropdown

        dropDown.should { be enabled }
        dropDown.should { be visible }

        dropDown.should { have label('Elements list') }

        dropDown.should { have items.equalsTo('Helium', 'Boron', 'Polonium', 'Calcium', 'Radium') }
        dropDown.should { have items('Helium', 'Boron', 'Polonium', 'Calcium', 'Radium') }

        dropDown.should { have items.containing('Polonium', 'Calcium') }

        dropDown.should { have selectedItems('Helium') }
        dropDown.items[2].should { be unSelected }

        on dropDown select 'Polonium'

        dropDown.should { have selectedItems('Polonium') }
        dropDown.items[2].should { be selected }

        assert dropDown.items.size == 5
        dropDown.should { have 5.items }

        dropDown.items[0].should { have label('H') }
        dropDown.items[1].should { have label('B') }
        dropDown.items[2].should { have label('Pol') }
        dropDown.items[3].should { have label('Ca') }
        dropDown.items[4].should { have label('Ra') }

        select dropDown.items[4]
        dropDown.should { have selectedItems('Radium') }
        dropDown.items[4].should { be selected }

        dropDown = $('#countries') as Dropdown
        dropDown.should { be disabled }
        dropDown.should { have items('Canada', 'France', 'Spain') }
        dropDown.items[0].should { be disabled }

        dropDown = $('#os') as Dropdown
        dropDown.should { have 8.items }
        dropDown.should { have items('None', 'Ubuntu', 'Fedora', 'Gentoo', 'XP', 'Vista', 'FreeBSD', 'OpenBSD') }

        dropDown.should { have 3.groupItems }
        dropDown.should { have groupItems('linux', 'win32', 'BSD') }
        dropDown.should { have groupItems.containing('linux') }

        GroupItem group = dropDown.groupItems[0]
        group.should { have label('linux') }
        group.should { have items('Ubuntu', 'Fedora', 'Gentoo') }

        group = dropDown.groupItems[1]
        group.should { have label('win32') }
        group.should { have items('XP', 'Vista') }

        group = dropDown.groupItems[2]
        group.should { have label('BSD') }
        group.should { have 2.items }

        assert group.items.size == 2

        group.should { have items('FreeBSD', 'OpenBSD') }
    }

    @Test
    public void test_listBox() {
        ListBox listBox = $('#cities') as ListBox

        listBox.should { have 6.items }
        listBox.should { have items('Montreal', 'Quebec', 'Montpellier', 'New York', 'Casablanca', 'Munich') }

        listBox.should { have selectedItems('New York', 'Munich') }

        listBox.should { have 3.visibleItems }
        listBox.should { be multiSelectable }

        listBox.items[0].should { be enabled }
        listBox.items[1].should { be disabled }

        on listBox unselect 'New York'
        on listBox unselect 'Munich'

        on listBox select 'Montreal'
        on listBox select 'Montpellier'

        try {
            on listBox select 'Quebec'
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Item Quebec is disabled and cannot be selected'
        }

        listBox.should { have selectedItems('Montreal', 'Montpellier') }

        listBox = $('#planets') as ListBox
        listBox.should { be singleSelectable }

        listBox.should { have groupItems('Cat-1', 'Cat-2') }

        GroupItem group = listBox.groupItems[0]
        group.should { have label('Cat-1') }
        group.should { have 4.items }

        assert group.items.size == 4

        group.should { have items('Mercury', 'Venus', 'Earth', 'Mars') }
        group.items[0].should { have value('Mercury') }
    }

    @Test
    public void test_listView() {
        ListView listView = $('#list_view') as ListView

        listView.should { be enabled }
        listView.should { be visible }

        listView.should { have size(5) }
        assert listView.items.size == 5
        listView.should { have 5.items }

        listView.should { have items('Item 1', 'Item 2', 'Item 3', 'Item 4', 'Item 5') }
        listView.items[0].should { have value('Item 1') }
    }

    @Test
    public void test_form() {
        Form form = $('#form') as Form
        EmailField email_field = $('#form [type=email]') as EmailField
        PasswordField password_field = $('#form [type=password]') as PasswordField

        Button submit_button = $('#form [type=submit]') as Button
        Button reset_button = $('#form [type=reset]') as Button

        Message message = $('#form .alert') as Message

        // Can reset a form
        on email_field enter 'my@email.org'
        on password_field enter 'password'

        // By clicking on the button
        email_field.should { have text('my@email.org') }
        password_field.should { have text('password') }

        clickOn reset_button

        email_field.should { have text('') }
        password_field.should { have text('') }

        // By the DSL
        on email_field enter 'my@email.org'
        on password_field enter 'password'

        email_field.should { have text('my@email.org') }
        password_field.should { have text('password') }

        reset form

        email_field.should { have text('') }
        password_field.should { have text('') }

        // Can submit a form
        message.should { have title('The form was submitted 0 time(s)') }
        message.should { have title.containing('The form was submitted') }

        clickOn submit_button
        message.should { have title('The form was submitted 1 time(s)') }

        submit form
        message.should { have title('The form was submitted 2 time(s)') }
    }

    @Test
    public void test_datagrid() {
        DataGrid data_grid = $('#data_grid') as DataGrid

        data_grid.should { be enabled }
        data_grid.should { be visible }

        data_grid.should { have 3.columns }
        assert data_grid.columns.size == 3

        data_grid.should { have 4.rows }
        assert data_grid.rows.size == 4

        data_grid.columns[0].should { have title('Column 1 title') }
        data_grid.columns[1].should { have title('Column 2 title') }
        data_grid.columns[2].should { have title('Column 3 title') }

        List<Column> columns = data_grid.columns

        columns[0].should { have 4.cells }
        assert columns[0].cells.size == 4

        List<Cell> cells = columns[1].cells

        cells[0].should { have value('cell 12') }
        cells[1].should { have value('cell 22') }
        cells[2].should { have value('cell 32') }
        cells[3].should { have value('cell 42') }

        columns[2].cells[3].should { have value('cell 43') }

        List<Row> rows = data_grid.rows
        rows[0].should { have 3.cells }
        assert rows[0].cells.size == 3

        cells = rows[1].cells

        cells[0].should { have value('cell 21') }
        cells[1].should { have value('cell 22') }
        cells[2].should { have value('cell 23') }

        rows[2].cells[1].should { have value('cell 32') }
    }

    @Test
    public void test_heading() {
        Heading heading_1 = $('#heading_1') as Heading
        heading_1.should { have text('heading 1') }

        Heading heading_2 = $('#heading_2') as Heading
        heading_2.should { have text('heading 2') }

        Heading heading_3 = $('#heading_3') as Heading
        heading_3.should { have text('heading 3') }

        Heading heading_4 = $('#heading_4') as Heading
        heading_4.should { have text('heading 4') }

        Heading heading_5 = $('#heading_5') as Heading
        heading_5.should { have text('heading 5') }

        Heading heading_6 = $('#heading_6') as Heading
        heading_6.should { have text('heading 6') }
    }

    @Test
    public void test_article() {
        Article article = $('#article') as Article

        article.should { be enabled }
        article.should { be visible }
    }

    @Test
    public void test_aside() {
        Aside aside = $('#aside') as Aside

        aside.should { be enabled }
        aside.should { be visible }
    }

    @Test
    public void test_footer() {
        Footer footer = $('#footer') as Footer

        footer.should { be enabled }
        footer.should { be visible }
    }

    @Test
    public void test_header() {
        Header header = $('#header') as Header

        header.should { be enabled }
        header.should { be visible }
    }

    @Test
    public void test_section() {
        Section section = $('#section') as Section

        section.should { be enabled }
        section.should { be visible }
    }

    @Test
    public void can_redefine_a_state_and_property() {
        CustomPanel custom_panel = $('#custom_panel') as CustomPanel

        custom_panel.should { have title('CustomPanel Title') }
        custom_panel.should { be selected }
    }

    @Test
    public void test_some_default_properties_and_state() {
        CustomPanel custom_panel = $('#custom_panel') as CustomPanel

        custom_panel.should { have size(2) }
        custom_panel.should { have text('TEXT') }
    }

    @Test
    public void test_component_equality() {
        Radio radio_1 = $('#radio') as Radio
        // The selector select the same component as radio_1
        Radio radio_2 = $('[type=radio]:checked') as Radio
        Radio radio_3  = $('#otherRadio') as Radio

        assert radio_1 == radio_2
        assert radio_1 != radio_3
    }

    class Message extends Panel {
        Message() {
            support Title, { Component c -> c.evaluator.getString("\$('#${id}').text()") }
        }
    }

    class CustomPanel extends Panel {
        CustomPanel() {
            support Title, { return 'CustomPanel Title' }
            support Selected, { return true }
            support Size, Text
        }
    }

}



