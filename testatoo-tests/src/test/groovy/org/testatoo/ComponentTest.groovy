package org.testatoo

import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.testatoo.config.TestatooJunitRunner
import org.testatoo.config.TestatooModules
import org.testatoo.core.component.Button
import org.testatoo.core.component.ComponentException
import org.testatoo.core.component.Link
import org.testatoo.core.component.Panel
import org.testatoo.core.component.input.CheckBox
import org.testatoo.core.component.input.PasswordField
import org.testatoo.core.component.input.Radio
import org.testatoo.core.component.input.TextField
import org.testatoo.core.component.list.DropDown
import org.testatoo.core.component.list.GroupItem
import org.testatoo.core.component.list.ListBox

import static org.junit.Assert.fail
import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.property.Properties.*
import static org.testatoo.core.state.States.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(TestatooJunitRunner.class)
@TestatooModules(TestModule)
class ComponentTest {

    @BeforeClass
    public static void openTestPage() {
        open('/component.html')
    }

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
        TextField textField = $('#text_field') as TextField

        assertThat textField is enabled
        assertThat textField is visible

        assertThat textField has label('Text')
        assertThat textField is empty

        textField.enter 'some value'
        assertThat textField has text('some value')
        assertThat textField has value('some value')
        assertThat textField is filled

        PasswordField passwordField = $('#password_field') as PasswordField

        assertThat passwordField is enabled
        assertThat passwordField is visible

        assertThat passwordField has label('Password')
        assertThat passwordField has text('?art')

        // TODO Textarea
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
        assertThat link has reference.containing('/component.html')
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
        on dropDown select 'Polonium'
        assertThat dropDown has selectedItems('Polonium')

        assert dropDown.items.size == 5
        assertThat dropDown has 5.items

        assertThat dropDown.items[0] has label('H')
        assertThat dropDown.items[1] has label('B')
        assertThat dropDown.items[2] has label('Pol')
        assertThat dropDown.items[3] has label('Ca')
        assertThat dropDown.items[4] has label('Ra')

        select dropDown.items[4]
        assertThat dropDown has selectedItems('Radium')

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
        assertThat listBox has selectedItems('New York', 'Munich')

        assertThat listBox has 3.visibleItems

//        assertThat listBox is multiSelectable
//        assertThat listBox !support singleSelectable

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
            assert e.message == 'Item Quebec is disabled and cannot be selected '
        }

        assertThat listBox has selectedItems('Montreal', 'Montpellier')
    }

}

//
//    @Test
//    public void test_contains() {
//
//    }

// ===================================================

//    @Test
//    public void test_page() {
//        open('/component.html');
//
//        assertThat(page().has(title()).equalsTo('Testatoo Rocks'));
//    }
//
//    @Test
//    public void test_contains() {
//        open('/component.html');
//
//        assertThat(page().contains(
//                new Button('button'),
//                new TextField('text_field'),
//                new Radio('radio')
//        ));
//
//        Panel panel = new Panel('panel');
//        assertThat(panel.contains(
//                new Button('button_in_visible_panel'),
//                new Button('invisible_button_in_visible_panel')
//        ));
//
//        Panel invisible_panel = new Panel('invisible_panel');
//        try {
//            assertThat(invisible_panel.contains(
//                    new Button('button_in_visible_panel')
//            ));
//        } catch (AssertionError e) {
//            assertEquals('Component Panel with id: \'invisible_panel\' does no contains component Button with id: \'button_in_visible_panel\'', e.getMessage());
//        }
//    }
//
//    @Test
//    public void test_displays() {
//        open('/component.html');
//
//        assertThat(page().displays(
//                new Button('button'),
//                new TextField('text_field'),
//                new Radio('radio')
//        ));
//
//        Panel panel = new Panel('panel');
//        assertThat(panel.displays(
//                new Button('button_in_visible_panel')
//        ));
//
//        try {
//            assertThat(panel.displays(
//                    new Button('button_in_visible_panel'),
//                    new Button('invisible_button_in_visible_panel')
//            ));
//        } catch (AssertionError e) {
//            assertEquals('Component Panel with id: \'panel\' does no displays component Button with id: \'invisible_button_in_visible_panel\'', e.getMessage());
//        }
//    }
//}
