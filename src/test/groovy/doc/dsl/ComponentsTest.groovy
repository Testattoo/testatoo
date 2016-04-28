package doc.dsl

import org.junit.Before
import org.junit.BeforeClass
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.WebDriverConfig
import org.testatoo.bundle.html5.Button
import org.testatoo.bundle.html5.CheckBox
import org.testatoo.bundle.html5.Radio
import org.testatoo.bundle.html5.list.MultiSelect
import org.testatoo.bundle.html5.list.Select
import org.testatoo.core.component.*

import static org.testatoo.core.Actions.*
import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ComponentsTest {

    @ClassRule
    public static WebDriverConfig driver = new WebDriverConfig()

    @BeforeClass
    public static void before() {
        visit 'http://localhost:8080/components.html'
    }

    @Before
    public void setUp() {
        browser.navigate.refresh()
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_component() {
        // tag::component[]
        Component component = $('#button') as Component

        component.should {
            be available
            be enabled
            be visible
        }

        component = $('#submit') as Component
        component.should { be disabled }

        component = $('#hidden_panel') as Component
        component.should { be hidden }

        component = $('#non_existing_id') as Component
        component.should { be missing }
        // end::component[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_button() {
        // tag::button[]
        Button button = $('#btn') as Button

        button.should { have text('My Button Text') }
        // end::button[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_checkbox() {
        // tag::checkbox[]
        CheckBox checkbox = $('#checkbox') as CheckBox
        checkbox.should {
            have label('Check me out')
            be unchecked
        }

        check checkbox
        checkbox.should { be checked }

        uncheck checkbox
        checkbox.should { be unchecked }
        // end::checkbox[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_radio() {
        // tag::radio[]
        Radio checked_radio = $('#radio_1') as Radio
        Radio unchecked_radio = $('#radio_2') as Radio

        checked_radio.should {
            have label('Radio checked')
            be checked
        }

        unchecked_radio.should {
            have label('Radio unchecked')
            be unchecked
        }

        check unchecked_radio

        unchecked_radio.should { be checked }
        checked_radio.should { be unchecked }
        // end::radio[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_dropdown() {
        // tag::operating_system[]
        Dropdown operating_system = $('#os') as Select
        operating_system.should {
            have label('OS')
            have 8.items
            have selectedItem('None')
            have items('None', 'Ubuntu', 'Fedora', 'Gentoo', 'XP', 'Vista', 'FreeBSD', 'OpenBSD')
            have 3.groups
            have groups('linux', 'win32', 'BSD')
        }

        on operating_system select 'Ubuntu'
        operating_system.should { have selectedItem('Ubuntu') }
        // end::operating_system[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_group() {
        // tag::group[]
        Dropdown operating_system = $('#os') as Select
        Group linux_group = operating_system.group('linux') // Or operating_system.groups[0]

        linux_group.should {
            have value('linux')
            have items('Ubuntu', 'Fedora', 'Gentoo')
        }
        // end::group[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_listbox() {
        // tag::listbox[]
        ListBox cities = $('#cities') as MultiSelect
        cities.should {
            have label('Cities list')
            have 6.items
            have items('Montreal', 'Quebec', 'Montpellier', 'New York', 'Casablanca', 'Munich')
            have selectedItems('Montreal')

            have 3.visibleItems     // See the first image at the begin of this section
        }

        on cities select 'Montpellier', 'New York'
        cities.should { have selectedItems('Montreal', 'Montpellier', 'New York') }
        // end::listbox[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_item() {
        // tag::item[]
        Dropdown operating_system = $('#os') as Select
        Item gentoo = operating_system.item('Gentoo')  // Or operating_system.items[1]
        gentoo.should {
            have value('Gentoo')
            be unselected
        }

        select gentoo
        gentoo.should { be selected }

        ListBox cities = $('#cities') as MultiSelect
        Item city = cities.item('Montpellier')
        city.should {
            have value('Montpellier')
            be unselected
        }

        select city
        city.should { be selected }
        // end::item[]
    }

    // =================================================

//    @Test
//    public void should_have_expected_properties_and_states_supported_by_textfield() {
//        // tag::textfield[]
//        TextField textfield = $('#text_field') as TextField
//        texfield.should {
//            be empty
//            be required
//        }
//        on textfield fill('1234')
//        texfield.should {
//            be filled
//            have value('1234')
//        }
//        // end::textfield[]
//    }
//
//    @Test
//    public void should_have_expected_properties_and_states_supported_by_form() {
//        // tag::form[]
//        Form form = $('#form') as Form
//        form.should {
//            be valid
//        }
//        // TODO
//        // end::form[]
//    }
//
//    @Test
//    public void should_have_expected_properties_and_states_supported_by_heading() {
//        // tag::heading[]
//        Heading heading = $('#heading') as Heading
//        heading.should {
//            have text ('page_title')
//        }
//
//        // end::heading[]
//    }
//
//    @Test
//    public void should_have_expected_properties_and_states_supported_by_image() {
//        // tag::image[]
//        Image image = $('#image') as Image
//        image.should {
//            have source ('image_source_file.jpg')
//        }
//
//        // end::image[]
//    }
//
//    @Test
//    public void should_have_expected_properties_and_states_supported_by_link() {
//        // tag::link[]
//        Link link = $('#link') as Link
//        link.should {
//            have text ('this is a link towards another page')
//            // TODO
//            have reference()
//        }
//
//        // end::link[]
//    }
//
//    @Test
//    public void should_have_expected_properties_and_states_supported_by_listview() {
//        // tag::listview[]
//        ListView listview = $('#listview') as ListView
//        listview.should {
//            have items('item1', 'ítem2', 'item3')
//        }
//        // end::listview[]
//    }
//
//
//    @Test
//    public void should_have_expected_properties_and_states_supported_by_panel() {
//        // tag::panel[]
//        Panel panel = $('#panel') as Panel
//        panel.should {
//            have title('panel_title')
//        }
//        // end::panel[]
//    }
//
//    @Test
//    public void should_have_expected_properties_and_states_supported_by_datagrid() {
//        // tag::datagrid[]
//        DataGrid datagrid = $('#datagrid') as DataGrid
//        datagrid.should {
//            have rows('TODO')
//            have columns('TODO')
//        }
//        // end::datagrid[]
//    }
}
