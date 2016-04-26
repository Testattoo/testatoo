package doc.dsl

import org.junit.BeforeClass
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.WebDriverConfig
import org.testatoo.bundle.html5.Button
import org.testatoo.bundle.html5.CheckBox
import org.testatoo.bundle.html5.Radio
import org.testatoo.bundle.html5.list.Select
import org.testatoo.core.component.Component
import org.testatoo.core.component.Dropdown
import org.testatoo.core.component.Form
import org.testatoo.core.component.Group
import org.testatoo.core.component.Heading
import org.testatoo.core.component.Image
import org.testatoo.core.component.Item
import org.testatoo.core.component.Link
import org.testatoo.core.component.ListBox
import org.testatoo.core.component.ListView
import org.testatoo.core.component.Panel
import org.testatoo.core.component.datagrid.DataGrid
import org.testatoo.core.component.field.TextField

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.Actions.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ComponentsTest {

    @ClassRule
    public static WebDriverConfig driver = new WebDriverConfig()

    @BeforeClass
    public static void before() {
        browser.open 'http://localhost:8080/components.html'
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
        // tag::dropdown[]
        Dropdown dropdown = $('#os') as Select
        dropdown.should {
            have label('OS')
            have 8.items
            have selectedItem('None')
            have items('None', 'Ubuntu', 'Fedora', 'Gentoo', 'XP', 'Vista', 'FreeBSD', 'OpenBSD')
            have 3.groups
            have groups('linux', 'win32', 'BSD')
        }

        on dropdown select 'Ubuntu'
        dropdown.should { have selectedItem('Ubuntu') }
        // end::dropdown[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_group() {
        // tag::group[]
        Dropdown dropdown = $('#dropdown') as Dropdown
        Group group = dropdown.group('linux') // Or dropdown.groups[0]

        group.should {
            have value('linux')
            have items('Ubuntu', 'Fedora', 'Gentoo')
        }
        // end::group[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_listbox() {
        // tag::listbox[]
        ListBox listbox = $('#cities') as ListBox
        listbox.should {
            have label('Cities list')
            have 6.items
            have items('Montreal', 'Quebec', 'Montpellier', 'New York', 'Casablanca', 'Munich')
            have selectedItems('Montreal')

            have 3.visibleItems     // See the first image at the begin of this section
        }

// Try to select another item with a control + click
        CTRL.click listBox.item('Montpellier')
//  OR
        select listBox.item('Montpellier')
//  OR
        on listBox select 'Montpellier'

        listbox.should { have selectedItems('Montreal', 'Montpellier') }

        on listBox select 'New York', 'Casablanca'


        select listBox.items[2]     // Throws an exception if we try to select a disabled element

        unselect listBox.items[0]   // Can unselect an item
        listbox.should {
            have selectedItems('Montpellier')
        }

        // end::listbox[]

    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_item() {
        // tag::item[]
        Dropdown dropdown = $('#dropdown') as Dropdown
        Item item = dropdown.item('Fedora')  // Or dropdown.items[1]
        item.should {
            have value('Fedora')
            be unselected
        }

        ListBox listBox = $('#cities') as ListBox
        Item city = listBox.item('Montreal')
        city.should {
            have value('Montreal')
            be unselected
        }
        // end::item[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_textfield() {
        // tag::textfield[]
        TextField textfield = $('#text_field') as TextField
        texfield.should {
            be empty
            be required
        }
        on textfield fill('1234')
        texfield.should {
            be filled
            have value('1234')
        }
        // end::textfield[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_form() {
        // tag::form[]
        Form form = $('#form') as Form
        form.should {
            be valid
        }
        // TODO
        // end::form[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_heading() {
        // tag::heading[]
        Heading heading = $('#heading') as Heading
        heading.should {
            have text ('page_title')
        }

        // end::heading[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_image() {
        // tag::image[]
        Image image = $('#image') as Image
        image.should {
            have source ('image_source_file.jpg')
        }

        // end::image[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_link() {
        // tag::link[]
        Link link = $('#link') as Link
        link.should {
            have text ('this is a link towards another page')
            // TODO
            have reference()
        }

        // end::link[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_listview() {
        // tag::listview[]
        ListView listview = $('#listview') as ListView
        listview.should {
            have items('item1', 'ítem2', 'item3')
        }
        // end::listview[]
    }


    @Test
    public void should_have_expected_properties_and_states_supported_by_panel() {
        // tag::panel[]
        Panel panel = $('#panel') as Panel
        panel.should {
            have title('panel_title')
        }
        // end::panel[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_datagrid() {
        // tag::datagrid[]
        DataGrid datagrid = $('#datagrid') as DataGrid
        datagrid.should {
            have rows('TODO')
            have columns('TODO')
        }
        // end::datagrid[]
    }
}
