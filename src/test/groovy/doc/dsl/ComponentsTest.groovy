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
import org.testatoo.bundle.html5.heading.H1
import org.testatoo.bundle.html5.input.InputTypeDate
import org.testatoo.bundle.html5.input.InputTypeEmail
import org.testatoo.bundle.html5.input.InputTypeNumber
import org.testatoo.bundle.html5.input.InputTypePassword
import org.testatoo.bundle.html5.input.InputTypeRange
import org.testatoo.bundle.html5.input.InputTypeText
import org.testatoo.bundle.html5.list.MultiSelect
import org.testatoo.bundle.html5.list.Select
import org.testatoo.core.component.*
import org.testatoo.core.component.field.DateField
import org.testatoo.core.component.field.EmailField
import org.testatoo.core.component.field.NumberField
import org.testatoo.core.component.field.PasswordField
import org.testatoo.core.component.field.RangeField
import org.testatoo.core.component.field.TextField

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
        // tag::os_list[]
        Dropdown os_list = $('#os') as Select
        os_list.should {
            have label('OS')
            have 8.items
            have selectedItem('None')
            have items('None', 'Ubuntu', 'Fedora', 'Gentoo', 'XP', 'Vista', 'FreeBSD', 'OpenBSD')
            have 3.groups
            have groups('linux', 'win32', 'BSD')
        }

        on os_list select 'Ubuntu'
        os_list.should { have selectedItem('Ubuntu') }
        // end::os_list[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_group() {
        // tag::group[]
        Dropdown os_list = $('#os') as Select
        Group linux_group = os_list.group('linux') // Or os_list.groups[0]

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
        Dropdown os_list = $('#os') as Select
        Item os = os_list.item('Gentoo')  // Or os_list.items[1]
        os.should {
            have value('Gentoo')
            be unselected
        }

        select os
        os.should { be selected }

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

    @Test
    public void should_have_expected_properties_and_states_supported_by_textfield() {
        // tag::textfield[]
        TextField textfield = $('#text_field') as InputTypeText
        textfield.should {
            be empty
            be required
            have placeholder('Placeholder')
        }

        fill textfield with '1234'

        textfield.should {
            be filled
            have value('1234')
        }
        // end::textfield[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_datefield() {
        // tag::datefield[]
        DateField date = $('#date_field') as InputTypeDate
        date.should {
            be inRange
            have value('')
            have step(0)
            have maximum('2012-06-25')
            have minimum('2011-08-13')
        }

        fill date with '2011-06-26'
        date.should { have value('2011-06-26') }
        // end::datefield[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_numberfield() {
        // tag::numberfield[]
        NumberField number = $('#number_field') as InputTypeNumber

        number.should {
            have minimum(0)
            have maximum(64)
            have step(8)
            have value(2)
            be inRange
        }

        fill number with 150
        number.should { be outOfRange }
        // end::numberfield[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_rangefield() {
        // tag::rangefield[]
        RangeField range = $('#range_field') as InputTypeRange
        range.should {
            have minimum(0)
            have maximum(50)
            have value(10)
            have step(5)
            is inRange
        }

        fill range with 40

        range.should { have value(40) }
        // end::rangefield[]
    }


    @Test
    public void should_have_expected_properties_and_states_supported_by_form() {
        // tag::form[]
        Form form = $('#form') as org.testatoo.bundle.html5.Form
        EmailField email_field = $('#email') as InputTypeEmail
        PasswordField password_field = $('#password') as InputTypePassword

        email_field.should { be optional }
        password_field.should { be required }

        form.should {
            contains(email_field, password_field)
            be invalid
        }

        fill email_field with 'invalid_email'
        fill password_field with 'a password'

        form.should { be invalid }

        fill email_field with 'valid@email.org'

        form.should { be valid }
        // end::form[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_heading() {
        // tag::heading[]
        Heading heading = $('#h1') as H1
        heading.should {
            have text('heading 1')
        }
        // end::heading[]
    }

    public void should_have_expected_properties_and_states_supported_by_image() {
        // tag::image[]
        Image image = $('#image') as Image
        image.should {
            have source('img/Montpellier.jpg')
        }
        // end::image[]
    }

    // =================================================
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
