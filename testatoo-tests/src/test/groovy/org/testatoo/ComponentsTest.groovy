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

        expect button is enabled
        expect button is visible

        expect button has text('Button')

        // input type=submit
        button = $('#submit') as Button
        expect button has text('Submit')

        // input type=reset
        button = $('#reset') as Button
        expect button has text('Reset')

        // button element
        button = $('#btn') as Button
        expect button has text('My Button Text')
    }

    @Test
    public void test_input_fields() {
        // Text field
        TextField textField = $('#text_field') as TextField

        expect textField is enabled
        expect textField is visible
        expect textField is optional

        expect textField has label('Text')
        expect textField has placeholder('Text')
        expect textField is empty

        on textField enter 'some value'

        expect textField has text('some value')
        expect textField has value('some value')
        expect textField is filled

        reset textField
        expect textField is empty

        // TextArea is treated as TextField
        textField = $('#text_area_field') as TextField

        expect textField is enabled
        expect textField is visible

        PasswordField passwordField = $('#password_field') as PasswordField

        expect passwordField is enabled
        expect passwordField is visible

        expect passwordField has label('Password')
        expect passwordField has text('?art')

        EmailField emailField = $('#email_field') as EmailField
        expect emailField is disabled

        PhoneField phoneField = $('#phone_field') as PhoneField
        expect phoneField is enabled
        expect phoneField has pattern('^((\\+\\d{1,3}(-| )?\\(?\\d\\)?(-| )?\\d{1,5})|(\\(?\\d{2,6}\\)?))(-| )?(\\d{3,4})(-| )?(\\d{4})(( x| ext)\\d{1,5}){0,1}$')

        URLField urlField = $('#url_field') as URLField
        expect urlField is enabled

        SearchField searchField = $('#search_field') as SearchField
        expect searchField is enabled

        NumberField numberField = $('#number_field') as NumberField
        expect numberField is enabled
        expect numberField has minimun(0)
        expect numberField has maximum(64)
        expect numberField has step(8)

        RangeField rangeField = $('#range_field') as RangeField
        expect rangeField is enabled
        expect rangeField has minimun(0)
        expect rangeField has maximum(50)
        expect rangeField has step(5)

        ColorField colorField = $('#color_field') as ColorField
        expect colorField is enabled
        expect colorField is optional

        MonthField monthField = $('#month_field') as MonthField
        expect monthField is enabled
        expect monthField is required

        WeekField weekField = $('#week_field') as WeekField
        expect weekField is enabled

        DateField dateField = $('#date_field') as DateField
        expect dateField is enabled
        expect dateField has minimun('2011-08-13')
        expect dateField has maximum('2012-06-25')

        TimeField timeField = $('#time_field') as TimeField
        expect timeField is enabled

        DateTimeField dateTimeField = $('#datetime_field') as DateTimeField
        expect dateTimeField is enabled
    }

    @Test
    public void test_checkbox() {
        CheckBox checkBox = $('#checkbox') as CheckBox
        expect checkBox is enabled
        expect checkBox is visible

        expect checkBox is unchecked
        expect checkBox has label('Check me out')

        check checkBox
        expect checkBox is checked

        clickOn checkBox
        expect checkBox is unchecked
    }

    @Test
    public void test_radio() {
        Radio radio = $('#radio') as Radio;

        expect radio is enabled
        expect radio is visible
        expect radio is checked

        expect radio has label('Radio label');
    }

    @Test
    public void test_link() {
        Link link = $('#link') as Link
        expect link is enabled
        expect link is visible

        expect link has text('Link to component page')
        expect link has reference.equalsTo('http://localhost:8080/components.html')
        expect link has reference('http://localhost:8080/components.html')
        expect link has reference.containing('component')
    }

    @Test
    public void test_image() {
        Image image = $('#image') as Image
        expect image is enabled
        expect image is visible

        expect image has source('http://localhost:8080/img/Montpellier.jpg')
        expect image has source.containing('Montpellier')
    }

    @Test
    public void test_panel() {
        Panel panel = $('#panel') as Panel
        expect panel is enabled
        expect panel is visible

        expect panel has title.equalsTo('')
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

        expect dropDown is enabled
        expect dropDown is visible

        expect dropDown has label('Elements list');

        expect dropDown has items.equalsTo('Helium', 'Boron', 'Polonium', 'Calcium', 'Radium')
        expect dropDown has items('Helium', 'Boron', 'Polonium', 'Calcium', 'Radium')

        expect dropDown has items.containing('Polonium', 'Calcium')

        expect dropDown has selectedItems('Helium')
        expect dropDown.items[2] is unSelected

        on dropDown select 'Polonium'

        expect dropDown has selectedItems('Polonium')
        expect dropDown.items[2] is selected

        assert dropDown.items.size == 5
        expect dropDown has 5.items

        expect dropDown.items[0] has label('H')
        expect dropDown.items[1] has label('B')
        expect dropDown.items[2] has label('Pol')
        expect dropDown.items[3] has label('Ca')
        expect dropDown.items[4] has label('Ra')

        select dropDown.items[4]
        expect dropDown has selectedItems('Radium')
        expect dropDown.items[4] is selected

        dropDown = $('#countries') as DropDown
        expect dropDown is disabled
        expect dropDown has items('Canada', 'France', 'Spain')
        expect dropDown.items[0] is disabled

        dropDown = $('#os') as DropDown
        expect dropDown has 8.items
        expect dropDown has items('None', 'Ubuntu', 'Fedora', 'Gentoo', 'XP', 'Vista', 'FreeBSD', 'OpenBSD')

        expect dropDown has 3.groupItems
        expect dropDown has groupItems('linux', 'win32', 'BSD')

        GroupItem group = dropDown.groupItems[0]
        expect group has label('linux')
        expect group has items('Ubuntu', 'Fedora', 'Gentoo')

        group = dropDown.groupItems[1]
        expect group has label('win32')
        expect group has items('XP', 'Vista')

        group = dropDown.groupItems[2]
        expect group has label('BSD')
        expect group has 2.items
        assert group.items.size == 2
        expect group has items('FreeBSD', 'OpenBSD')
    }

    @Test
    public void test_listBox() {
        ListBox listBox = $('#cities') as ListBox

        expect listBox has 6.items
        expect listBox has items('Montreal', 'Quebec', 'Montpellier', 'New York', 'Casablanca', 'Munich')

        expect listBox has selectedItems('New York', 'Munich')

        expect listBox has 3.visibleItems

        expect listBox is multiSelectable

        expect listBox.items[0] is enabled
        expect listBox.items[1] is disabled

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

        expect listBox has selectedItems('Montreal', 'Montpellier')

        listBox = $('#planets') as ListBox
        expect listBox is singleSelectable

        expect listBox has groupItems('Cat-1', 'Cat-2')

        GroupItem group = listBox.groupItems[0]
        expect group has label('Cat-1')
        expect group has 4.items
        assert group.items.size == 4
        expect group has items('Mercury', 'Venus', 'Earth', 'Mars')
        expect group.items[0] has value('Mercury')
    }

    @Test
    public void test_listView() {
        ListView listView = $('#list_view') as ListView

        expect listView is enabled
        expect listView is visible

        expect listView has size(5)
        assert listView.items.size == 5
        expect listView has 5.items

        expect listView has items('Item 1', 'Item 2', 'Item 3', 'Item 4', 'Item 5')
        expect listView.items[0] has value('Item 1')
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
        expect email_field has text('my@email.org')
        expect password_field has text('password')

        clickOn reset_button

        expect email_field has text('')
        expect password_field has text('')

        // By the DSL
        on email_field enter 'my@email.org'
        on password_field enter 'password'

        expect email_field has text('my@email.org')
        expect password_field has text('password')

        reset form

        expect email_field has text('')
        expect password_field has text('')

        // Can submit a form
        expect message has title('The form was submitted 0 time(s)')

        clickOn submit_button
        expect message has title('The form was submitted 1 time(s)')

        submit form
        expect message has title('The form was submitted 2 time(s)')
    }

    @Test
    public void test_datagrid() {
        DataGrid data_grid = $('#data_grid') as DataGrid

        expect data_grid is enabled
        expect data_grid is visible

        expect data_grid has 3.columns
        assert data_grid.columns.size == 3

        expect data_grid has 4.rows
        assert data_grid.rows.size == 4

        expect data_grid.columns[0] has title('Column 1 title')
        expect data_grid.columns[1] has title('Column 2 title')
        expect data_grid.columns[2] has title('Column 3 title')

        List<Column> columns = data_grid.columns

        expect columns[0] has 4.cells
        assert columns[0].cells.size == 4

        List<Cell> cells = columns[1].cells

        expect cells[0] has value('cell 12')
        expect cells[1] has value('cell 22')
        expect cells[2] has value('cell 32')
        expect cells[3] has value('cell 42')

        expect columns[2].cells[3] has value('cell 43')

        List<Row> rows = data_grid.rows

        expect rows[0] has 3.cells
        assert rows[0].cells.size == 3

        cells = rows[1].cells

        expect cells[0] has value('cell 21')
        expect cells[1] has value('cell 22')
        expect cells[2] has value('cell 23')

        expect rows[2].cells[1] has value('cell 32')
    }

    @Test
    public void test_heading() {
        Heading heading_1 = $('#heading_1') as Heading
        expect heading_1 has text('heading 1')

        Heading heading_2 = $('#heading_2') as Heading
        expect heading_2 has text('heading 2')

        Heading heading_3 = $('#heading_3') as Heading
        expect heading_3 has text('heading 3')

        Heading heading_4 = $('#heading_4') as Heading
        expect heading_4 has text('heading 4')

        Heading heading_5 = $('#heading_5') as Heading
        expect heading_5 has text('heading 5')

        Heading heading_6 = $('#heading_6') as Heading
        expect heading_6 has text('heading 6')
    }

    @Test
    public void test_article() {
        Article article = $('#article') as Article

        expect article is enabled
        expect article is visible
    }

    @Test
    public void test_aside() {
        Aside aside = $('#aside') as Aside

        expect aside is enabled
        expect aside is visible
    }

    @Test
    public void test_footer() {
        Footer footer = $('#footer') as Footer

        expect footer is enabled
        expect footer is visible
    }

    @Test
    public void test_header() {
        Header header = $('#header') as Header

        expect header is enabled
        expect header is visible
    }

    @Test
    public void test_section() {
        Section section = $('#section') as Section

        expect section is enabled
        expect section is visible
    }

    @Test
    public void can_redefine_a_state_and_property() {
        CustomPanel custom_panel = $('#custom_panel') as CustomPanel

        expect custom_panel has title('CustomPanel Title')
        expect custom_panel is selected
    }

    @Test
    public void test_some_default_properties_and_state() {
        CustomPanel custom_panel = $('#custom_panel') as CustomPanel
        expect custom_panel has size(2)
        expect custom_panel has text('TEXT')
    }

//    @Test
//    public void test_page() {
//        expect(page().has(title()).equalsTo('Testatoo Rocks'));
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



