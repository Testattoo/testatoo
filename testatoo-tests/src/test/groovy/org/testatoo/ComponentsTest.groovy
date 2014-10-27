/**
 * Copyright (C) 2013 Ovea (dev@ovea.com)
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

import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Testatoo
import org.testatoo.core.component.*
import org.testatoo.core.component.datagrid.*
import org.testatoo.core.component.input.*
import org.testatoo.core.component.list.*
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator
import org.testatoo.core.property.*
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
        Testatoo.evaluator =  new WebDriverEvaluator(new FirefoxDriver())
        open 'http://localhost:8080/components.html'
    }
    @AfterClass public static void tearDown() { evaluator.close() }

    @Test
    public void test_button() {
        // input type=button
        Button button = $('#button') as Button

        assertThat button is enabled
        assertThat button is visible

        assertThat button has text('Button')

        // input type=submit
        button = $('#submit') as Button
        assertThat button has text('Submit')

        // input type=reset
        button = $('#reset') as Button
        assertThat button has text('Reset')

        // button element
        button = $('#btn') as Button
        assertThat button has text('My Button Text')
    }

    @Test
    public void test_input_fields() {
        // Text field
        TextField textField = $('#text_field') as TextField

        assertThat textField is enabled
        assertThat textField is visible
        assertThat textField is optional

        assertThat textField has label('Text')
        assertThat textField has placeholder('Text')
        assertThat textField is empty

        on textField enter 'some value'

        assertThat textField has text('some value')
        assertThat textField has value('some value')
        assertThat textField is filled

        reset textField
        assertThat textField is empty

        // TextArea is treated as TextField
        textField = $('#text_area_field') as TextField

        assertThat textField is enabled
        assertThat textField is visible

        PasswordField passwordField = $('#password_field') as PasswordField

        assertThat passwordField is enabled
        assertThat passwordField is visible

        assertThat passwordField has label('Password')
        assertThat passwordField has text('?art')

        EmailField emailField = $('#email_field') as EmailField
        assertThat emailField is disabled

        PhoneField phoneField = $('#phone_field') as PhoneField
        assertThat phoneField is enabled
        assertThat phoneField has pattern('^((\\+\\d{1,3}(-| )?\\(?\\d\\)?(-| )?\\d{1,5})|(\\(?\\d{2,6}\\)?))(-| )?(\\d{3,4})(-| )?(\\d{4})(( x| ext)\\d{1,5}){0,1}$')

        URLField urlField = $('#url_field') as URLField
        assertThat urlField is enabled

        SearchField searchField = $('#search_field') as SearchField
        assertThat searchField is enabled

        NumberField numberField = $('#number_field') as NumberField
        assertThat numberField is enabled
        assertThat numberField has minimun(0)
        assertThat numberField has maximum(64)
        assertThat numberField has step(8)

        RangeField rangeField = $('#range_field') as RangeField
        assertThat rangeField is enabled
        assertThat rangeField has minimun(0)
        assertThat rangeField has maximum(50)
        assertThat rangeField has step(5)

        ColorField colorField = $('#color_field') as ColorField
        assertThat colorField is enabled
        assertThat colorField is optional

        MonthField monthField = $('#month_field') as MonthField
        assertThat monthField is enabled
        assertThat monthField is required

        WeekField weekField = $('#week_field') as WeekField
        assertThat weekField is enabled

        DateField dateField = $('#date_field') as DateField
        assertThat dateField is enabled
        assertThat dateField has minimun('2011-08-13')
        assertThat dateField has maximum('2012-06-25')

        TimeField timeField = $('#time_field') as TimeField
        assertThat timeField is enabled

        DateTimeField dateTimeField = $('#datetime_field') as DateTimeField
        assertThat dateTimeField is enabled
    }

    @Test
    public void test_checkbox() {
        CheckBox checkBox = $('#checkbox') as CheckBox
        assertThat checkBox is enabled
        assertThat checkBox is visible

        assertThat checkBox is unchecked
        assertThat checkBox has label('Check me out')

        check checkBox
        assertThat checkBox is checked

        clickOn checkBox
        assertThat checkBox is unchecked
    }

    @Test
    public void test_radio() {
        Radio radio = $('#radio') as Radio;

        assertThat radio is enabled
        assertThat radio is visible
        assertThat radio is checked

        assertThat radio has label('Radio label');
    }

    @Test
    public void test_link() {
        Link link = $('#link') as Link
        assertThat link is enabled
        assertThat link is visible

        assertThat link has text('Link to component page')
        assertThat link has reference.equalsTo('http://localhost:8080/components.html')
        assertThat link has reference('http://localhost:8080/components.html')
        assertThat link has reference.containing('component')
    }

    @Test
    public void test_image() {
        Image image = $('#image') as Image
        assertThat image is enabled
        assertThat image is visible

        assertThat image has source('http://localhost:8080/img/Montpellier.jpg')
        assertThat image has source.containing('Montpellier')
    }

    @Test
    public void test_panel() {
        Panel panel = $('#panel') as Panel
        assertThat panel is enabled
        assertThat panel is visible

        assertThat panel has title.equalsTo('')
    }

    // http://en.wikipedia.org/wiki/Combo_box
    @Test
    public void test_comboBox() {
        // Not available in HTML
    }

    // http://en.wikipedia.org/wiki/Drop-down_list
    @Test
    public void test_dropDown() {
        DropDown dropDown = $('#elements') as DropDown

        assertThat dropDown is enabled
        assertThat dropDown is visible

        assertThat dropDown has label('Elements list');

        assertThat dropDown has items.equalsTo('Helium', 'Boron', 'Polonium', 'Calcium', 'Radium')
        assertThat dropDown has items('Helium', 'Boron', 'Polonium', 'Calcium', 'Radium')

        assertThat dropDown has items.containing('Polonium', 'Calcium')

        assertThat dropDown has selectedItems('Helium')
        assertThat dropDown.items[2] is unSelected

        on dropDown select 'Polonium'

        assertThat dropDown has selectedItems('Polonium')
        assertThat dropDown.items[2] is selected

        assert dropDown.items.size == 5
        assertThat dropDown has 5.items

        assertThat dropDown.items[0] has label('H')
        assertThat dropDown.items[1] has label('B')
        assertThat dropDown.items[2] has label('Pol')
        assertThat dropDown.items[3] has label('Ca')
        assertThat dropDown.items[4] has label('Ra')

        select dropDown.items[4]
        assertThat dropDown has selectedItems('Radium')
        assertThat dropDown.items[4] is selected

        dropDown = $('#countries') as DropDown
        assertThat dropDown is disabled
        assertThat dropDown has items('Canada', 'France', 'Spain')
        assertThat dropDown.items[0] is disabled

        dropDown = $('#os') as DropDown
        assertThat dropDown has 8.items
        assertThat dropDown has items('None', 'Ubuntu', 'Fedora', 'Gentoo', 'XP', 'Vista', 'FreeBSD', 'OpenBSD')

        assertThat dropDown has 3.groupItems
        assertThat dropDown has groupItems('linux', 'win32', 'BSD')

        GroupItem group = dropDown.groupItems[0]
        assertThat group has label('linux')
        assertThat group has items('Ubuntu', 'Fedora', 'Gentoo')

        group = dropDown.groupItems[1]
        assertThat group has label('win32')
        assertThat group has items('XP', 'Vista')

        group = dropDown.groupItems[2]
        assertThat group has label('BSD')
        assertThat group has 2.items
        assert group.items.size == 2
        assertThat group has items('FreeBSD', 'OpenBSD')
    }

    @Test
    public void test_listBox() {
        ListBox listBox = $('#cities') as ListBox

        assertThat listBox has 6.items
        assertThat listBox has items('Montreal', 'Quebec', 'Montpellier', 'New York', 'Casablanca', 'Munich')

        assertThat listBox has selectedItems('New York', 'Munich')

        assertThat listBox has 3.visibleItems

        assertThat listBox is multiSelectable

        assertThat listBox.items[0] is enabled
        assertThat listBox.items[1] is disabled

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

        assertThat listBox has selectedItems('Montreal', 'Montpellier')

        listBox = $('#planets') as ListBox
        assertThat listBox is singleSelectable

        assertThat listBox has groupItems('Cat-1', 'Cat-2')

        GroupItem group = listBox.groupItems[0]
        assertThat group has label('Cat-1')
        assertThat group has 4.items
        assert group.items.size == 4
        assertThat group has items('Mercury', 'Venus', 'Earth', 'Mars')
        assertThat group.items[0] has value('Mercury')
    }

    @Test
    public void test_listView() {
        ListView listView = $('#list_view') as ListView

        assertThat listView is enabled
        assertThat listView is visible

        assertThat listView has size(5)
        assert listView.items.size == 5
        assertThat listView has 5.items

        assertThat listView has items('Item 1', 'Item 2', 'Item 3', 'Item 4', 'Item 5')
        assertThat listView.items[0] has value('Item 1')
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
        assertThat email_field has text('my@email.org')
        assertThat password_field has text('password')

        clickOn reset_button

        assertThat email_field has text('')
        assertThat password_field has text('')

        // By the DSL
        on email_field enter 'my@email.org'
        on password_field enter 'password'

        assertThat email_field has text('my@email.org')
        assertThat password_field has text('password')

        reset form

        assertThat email_field has text('')
        assertThat password_field has text('')

        // Can submit a form
        assertThat message has title('The form was submitted 0 time(s)')

        clickOn submit_button
        assertThat message has title('The form was submitted 1 time(s)')

        submit form
        assertThat message has title('The form was submitted 2 time(s)')
    }

    @Test
    public void test_datagrid() {
        DataGrid data_grid = $('#data_grid') as DataGrid

        assertThat data_grid is enabled
        assertThat data_grid is visible

        assertThat data_grid has 3.columns
        assert data_grid.columns.size == 3

        assertThat data_grid has 4.rows
        assert data_grid.rows.size == 4

        assertThat data_grid.columns[0] has title('Column 1 title')
        assertThat data_grid.columns[1] has title('Column 2 title')
        assertThat data_grid.columns[2] has title('Column 3 title')

        List<Column> columns = data_grid.columns

        assertThat columns[0] has 4.cells
        assert columns[0].cells.size == 4

        List<Cell> cells = columns[1].cells

        assertThat cells[0] has value('cell 12')
        assertThat cells[1] has value('cell 22')
        assertThat cells[2] has value('cell 32')
        assertThat cells[3] has value('cell 42')

        assertThat columns[2].cells[3] has value('cell 43')

        List<Row> rows = data_grid.rows

        assertThat rows[0] has 3.cells
        assert rows[0].cells.size == 3

        cells = rows[1].cells

        assertThat cells[0] has value('cell 21')
        assertThat cells[1] has value('cell 22')
        assertThat cells[2] has value('cell 23')

        assertThat rows[2].cells[1] has value('cell 32')
    }

    @Test
    public void test_heading() {
        Heading heading_1 = $('#heading_1') as Heading
        assertThat heading_1 has text('heading 1')

        Heading heading_2 = $('#heading_2') as Heading
        assertThat heading_2 has text('heading 2')

        Heading heading_3 = $('#heading_3') as Heading
        assertThat heading_3 has text('heading 3')

        Heading heading_4 = $('#heading_4') as Heading
        assertThat heading_4 has text('heading 4')

        Heading heading_5 = $('#heading_5') as Heading
        assertThat heading_5 has text('heading 5')

        Heading heading_6 = $('#heading_6') as Heading
        assertThat heading_6 has text('heading 6')
    }

    @Test
    public void test_article() {
        Article article = $('#article') as Article

        assertThat article is enabled
        assertThat article is visible
    }

    @Test
    public void test_aside() {
        Aside aside = $('#aside') as Aside

        assertThat aside is enabled
        assertThat aside is visible
    }

    @Test
    public void test_footer() {
        Footer footer = $('#footer') as Footer

        assertThat footer is enabled
        assertThat footer is visible
    }

    @Test
    public void test_header() {
        Header header = $('#header') as Header

        assertThat header is enabled
        assertThat header is visible
    }

    @Test
    public void test_section() {
        Section section = $('#section') as Section

        assertThat section is enabled
        assertThat section is visible
    }

    @Test
    public void can_redefine_a_state_and_property() {
        CustomPanel custom_panel = $('#custom_panel') as CustomPanel

        assertThat custom_panel has title('CustomPanel Title')
        assertThat custom_panel is selected
    }

    @Test
    public void test_some_default_properties_and_state() {
        CustomPanel custom_panel = $('#custom_panel') as CustomPanel
        assertThat custom_panel has size(2)
        assertThat custom_panel has text('TEXT')
    }

//    @Test
//    public void test_page() {
//        assertThat(page().has(title()).equalsTo('Testatoo Rocks'));
//    }

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



