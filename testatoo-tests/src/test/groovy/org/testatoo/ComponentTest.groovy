package org.testatoo

import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.testatoo.config.TestatooJunitRunner
import org.testatoo.config.TestatooModules

import org.testatoo.core.component.*
import org.testatoo.core.component.input.CheckBox
import org.testatoo.core.component.input.PasswordField
import org.testatoo.core.component.input.Radio
import org.testatoo.core.component.input.TextField
import org.testatoo.core.component.list.DropDown

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

        // TODO Math ... is it a state or a property !!!
        assertThat dropDown has selectedItems('Helium')
        on dropDown select 'Polonium'
        assertThat dropDown has selectedItems('Polonium')

        dropDown.items.size = 5
        assertThat dropDown has 5.items

        select dropDown.items[4]
        assertThat dropDown has selectedItems('Radium')

//        and(it(), has(not(selectedValues())));

    }

//    @Test
//    public void select_usage_through_language() {
//        Select select = component(Select.class, "elements");
//
//        assertThat(select, has(5, options()));
//        and(containsValues("Polonium", "Radium", "Calcium"));
//        and(it(), has(not(selectedValues())));
//
//        on(select).select("Calcium");
//        assertThat(select, has(selectedValues("Calcium")));
//
//        select = component(Select.class, "os");
//
//        assertThat(select, has(8, options()));
//        assertThat(select, has(3, optionGroups()));
//
//        select = component(Select.class, "elements");
//        assertThat(select, has(2, visibleRows()));
    }

//    @Test
//    public void select_usage_through_language() {
//        Select select = component(Select.class, "elements");
//
//        assertThat(select, has(5, options()));
//        and(containsValues("Polonium", "Radium", "Calcium"));
//        and(it(), has(not(selectedValues())));
//
//        on(select).select("Calcium");
//        assertThat(select, has(selectedValues("Calcium")));
//
//        select = component(Select.class, "os");
//
//        assertThat(select, has(8, options()));
//        assertThat(select, has(3, optionGroups()));
//
//        select = component(Select.class, "elements");
//        assertThat(select, has(2, visibleRows()));
//    }


//    @Test
//    public void test_if_multiple_select() {
//        assertThat(component(Select.class, $("#cities")).isMultiple(), is(true));
//    }
//
//    @Test
//    public void test_number_of_visible_rows() {
//        assertThat(component(Select.class, $("#elements")).visibleRows(), is(2));
//    }

//    @Test
//    public void can_retrieve_all_options() {
//        Selection<Option> options = component(Select.class, $("#cities")).options();
//
//        assertThat(component(Select.class, $("#cities")).optionGroups(), has(size(0)));
//        assertThat(options, has(size(6)));
//
//        assertThat(options.get(0).content(), is("Montreal"));
//        assertThat(options.get(1).content(), is("Quebec"));
//        assertThat(options.get(2).content(), is("Montpellier"));
//        assertThat(options.get(3).content(), is("New York"));
//        assertThat(options.get(4).content(), is("Casablanca"));
//        assertThat(options.get(5).content(), is("Munich"));
//    }
//
//    @Test
//    public void can_retrieve_optionGroups() {
//        Selection<OptionGroup> optionGroups = component(Select.class, $("#os")).optionGroups();
//
//        assertThat(optionGroups, has(size(3)));
//
//        assertThat(optionGroups.get(0), has(label("linux")));
//        assertThat(optionGroups.get(1), has(label("win32")));
//        assertThat(optionGroups.get(2), has(label("BSD")));
//    }
//
//    @Test
//    public void can_retrieve_options_from_a_optionGroup() {
//        Selection<OptionGroup> optionGroups = component(Select.class, $("#os")).optionGroups();
//
//        Selection<Option> options = optionGroups.get(1).options();
//        assertThat(options, has(size(2)));
//
//        assertThat(options.get(0).label(), is("XPLabel"));
//        assertThat(options.get(0).value(), is("XPValue"));
//        assertThat(options.get(0).content(), is("XP"));
//
//        assertThat(options.get(1).label(), is("VistaLabel"));
//        assertThat(options.get(1).value(), is("VistaValue"));
//        assertThat(options.get(1).content(), is("Vista"));
//    }
//
//    @Test
//    public void can_select_options() {
//        // Single
//        Select osSelect = component(Select.class, $("#os"));
//
//        assertThat(osSelect.optionGroups().get(0), has(label("linux")));
//
//        assertThat(osSelect.options(), has(size(8)));
//        osSelect.select("Kubuntu");
//
//        assertThat(osSelect.selectedOptions(), has(size(1)));
//        Option osSelectedOption = osSelect.selectedOptions().get(0);
//
//        assertThat(osSelectedOption.label(), is("KubuntuLabel"));
//        assertThat(osSelectedOption.value(), is("KubuntuValue"));
//        assertThat(osSelectedOption.content(), is("Kubuntu"));
//
//        osSelect.select("FreeBSD");
//
//        assertThat(osSelect.selectedOptions(), has(size(1)));
//        osSelectedOption = osSelect.selectedOptions().get(0);
//
//        assertThat(osSelectedOption.label(), is("FreeBSDLabel"));
//        assertThat(osSelectedOption.value(), is("FreeBSDValue"));
//        assertThat(osSelectedOption.content(), is("FreeBSD"));
//
//        // Multiple
//        Select planetsSelect = component(Select.class, $("#planets"));
//        planetsSelect.select("Earth");
//        planetsSelect.select("Jupiter");
//
//        assertThat(planetsSelect.selectedOptions(), has(size(2)));
//        Selection<Option> planetsSelectedOptions = planetsSelect.selectedOptions();
//
//        assertThat(planetsSelectedOptions.get(0).label(), is("Earth"));
//        assertThat(planetsSelectedOptions.get(0).value(), is("3"));
//        assertThat(planetsSelectedOptions.get(0).content(), is("Earth"));
//
//        assertThat(planetsSelectedOptions.get(1).label(), is("Jupiter"));
//        assertThat(planetsSelectedOptions.get(1).value(), is("5"));
//        assertThat(planetsSelectedOptions.get(1).content(), is("Jupiter"));
//    }
//
//    @Test
//    public void can_retrieve_values() {
//        // List without explicit values (in this case, the value is set with the content)
//        Select countriesList = component(Select.class, $("#countries"));
//        assertThat(countriesList.values(), hasItems("Canada", "France", "Spain"));
//
//        // List with explicit values
//        Select oceansList = component(Select.class, $("#oceans"));
//        assertThat(oceansList.values(), hasItems("Arctic", "Atlantic", "Pacific", "Indian", "Caribbean"));
//        assertThat(oceansList.values(), hasItems("Caribbean", "Atlantic", "Pacific", "Indian", "Arctic"));
//        assertThat(oceansList.values(), not(hasItems("Baltic", "Mediterranean", "Caspian", "Indian", "Caribbean")));
//    }
//
//    @Test
//    public void can_retrieve_selected_values() {
//        // List without explicit values (in this case, the value is set with the content)
//        Select countriesList = component(Select.class, $("#countries"));
//        countriesList.select("France");
//        assertThat(countriesList.selectedValues(), hasItems("France"));
//
//        // Single
//        Select componentsList = component(Select.class, $("#elements"));
//        componentsList.select("Helium");
//        assertThat(componentsList.selectedValues().get(0), is("Helium"));
//        componentsList.select("Calcium");
//        assertThat(componentsList.selectedValues().get(0), is("Calcium"));
//        assertThat(componentsList.selectedValues(), hasItems("Calcium"));
//
//        try {
//            componentsList.select("Oxygen");
//        } catch (Exception e) {
//            assertThat(e.getMessage(), is("ERROR: Option with value 'Oxygen' not found"));
//        }
//
//        // Multiple with simple values
//        Select planetsSelect = component(Select.class, $("#planets"));
//        planetsSelect.select("Venusnew TimeDuration(0, 0, 0, self.intValue(), 0)");
//        planetsSelect.select("Mars");
//        planetsSelect.select("Saturn");
//        assertThat(planetsSelect.selectedValues().get(0), is("Venus"));
//        assertThat(planetsSelect.selectedValues().get(1), is("Mars"));
//        assertThat(planetsSelect.selectedValues().get(2), is("Saturn"));
//
//        // Multiple with complex values
//        Select oceansList = component(Select.class, $("#oceans"));
//        oceansList.select("Pacific");
//        oceansList.select("Indian");
//        assertThat(oceansList.selectedValues().get(0), is("Pacific"));
//        assertThat(oceansList.selectedValues().get(1), is("Indian"));
//        assertThat(oceansList.selectedValues(), hasItems("Pacific", "Indian"));
//    }
//
//    @Test
//    public void can_un_select_options() {
//        // Single
//        Select osSelect = component(Select.class, $("#os"));
//
//        assertThat(osSelect.selectedOptions(), has(size(1)));
//        assertThat(osSelect.selectedOptions().get(0), has(value("none")));
//
//        osSelect.select("Kubuntu");
//
//        assertThat(osSelect.selectedOptions(), has(size(1)));
//        assertThat(osSelect.selectedOptions().get(0), has(value("KubuntuValue")));
//
//        // Cannot unselect an component in a single select
//        try {
//            osSelect.unselect("Kubuntu");
//            fail();
//        } catch (ComponentException e) {
//            assertThat(true, is(true));
//        }
//
//        // Multiple
//        Select planetsSelect = component(Select.class, $("#planets"));
//        planetsSelect.select("Earth");
//        planetsSelect.select("Jupiter");
//
//        assertThat(planetsSelect.selectedOptions(), has(size(2)));
//
//        planetsSelect.unselect("Jupiter");
//        assertThat(planetsSelect.selectedOptions(), has(size(1)));
//
//        planetsSelect.unselect("Earth");
//        assertThat(planetsSelect.selectedOptions(), has(size(0)));
//
//        osSelect = component(Select.class, $("#os"));
//        assertThat(osSelect.selectedOptions(), has(size(1)));
//
//        try {
//            osSelect.unselectAll();
//            fail();
//        } catch (ComponentException e) {
//            assertThat(true, is(true));
//        }
//
//        planetsSelect = component(Select.class, $("#planets"));
//        planetsSelect.select("Mercury");
//        planetsSelect.select("Venus");
//        assertThat(planetsSelect.selectedOptions(), has(size(2)));
//
//        planetsSelect.unselectAll();
//        assertThat(planetsSelect.selectedOptions(), has(size(0)));
//    }











//
//    // Test specific for Html
//    @Test
//    public void can_select_item() {
//        // List without explicit values (in this case, the value is set with the content)
//        DropDown countriesList = component(DropDown.class, "countries");
//        countriesList.select("France");
//        assertThat(countriesList.selectedValue(), is("France"));
//        countriesList.select("Spain");
//        assertThat(countriesList.selectedValue(), is("Spain"));
//
//        // List with explicit values
//        DropDown elementsList = component(DropDown.class, "elements");
//        elementsList.select("Polonium");
//        assertThat(elementsList.selectedValue(), is("Polonium"));
//        elementsList.select("Calcium");
//        assertThat(elementsList.selectedValue(), is("Calcium"));
//    }
//

//    http://en.wikipedia.org/wiki/List_box
//    @Test
//    public void test_listBox() {
//        ListBox listBox = $('cities') as ListBox
//
//    }

//    @Test
//    public void test_list() {
//        List list = $('#list_view') as List
//
//        assert list.is(filled);
//
////        assert list.contains(item1, item2);
//
////        assert list.has(5.items);
//        assert list.has(size.equalsTo(5));
//        assert list.items().size == 5;
//
//
////        assert list.has(values(['Item 1', 'Item 2', 'Item 3', 'Item 4', 'Item 5']))
//    }
//
//    @Test
//    public void test_contains() {
//
//    }

    // ===================================================

//

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
