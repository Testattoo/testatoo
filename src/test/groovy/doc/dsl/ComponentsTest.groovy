package doc.dsl

import org.junit.Before
import org.junit.BeforeClass
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.WebDriverConfig
import org.testatoo.bundle.html5.*
import org.testatoo.bundle.html5.heading.H1
import org.testatoo.bundle.html5.heading.H6
import org.testatoo.bundle.html5.input.*
import org.testatoo.bundle.html5.list.MultiSelect
import org.testatoo.bundle.html5.list.Select
import org.testatoo.bundle.html5.list.Ul
import org.testatoo.bundle.html5.table.Table
import org.testatoo.core.component.*
import org.testatoo.core.component.datagrid.Column
import org.testatoo.core.component.datagrid.DataGrid
import org.testatoo.core.component.datagrid.Row
import org.testatoo.core.component.field.*

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
        Component component = $('#button') as Component
        // tag::component[]
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
        Button button = $('#btn') as Button
        // tag::button[]
        button.should { have text('My Button Text') }
        // end::button[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_checkbox() {
        CheckBox checkbox = $('#checkbox') as CheckBox
        // tag::checkbox[]
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
        Radio checked_radio = $('#radio_1') as Radio
        Radio unchecked_radio = $('#radio_2') as Radio
        // tag::radio[]
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
        Dropdown os_list = $('#os') as Select
        // tag::dropdown[]
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
        // end::dropdown[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_group() {
        Dropdown os_list = $('#os') as Select
        // tag::group[]
        Group linux_group = os_list.group('linux') // Or os_list.groups[0]

        linux_group.should {
            have value('linux')
            have items('Ubuntu', 'Fedora', 'Gentoo')
        }
        // end::group[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_listbox() {
        ListBox cities = $('#cities') as MultiSelect
        // tag::listbox[]
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
        Dropdown os_list = $('#os') as Select
        // tag::item[]
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
        TextField textfield = $('#text_field') as InputTypeText
        // tag::textfield[]
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
        DateField date = $('#date_field') as InputTypeDate
        // tag::datefield[]
        date.should {
            be inRange
            have value('')
            have step(0)
            have maximum('2012-06-25')
            have minimum('2011-08-13')
        }

        set date to '2011-06-26'
        date.should { have value('2011-06-26') }
        // end::datefield[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_numberfield() {
        NumberField number = $('#number_field') as InputTypeNumber
        // tag::numberfield[]
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
        RangeField range = $('#range_field') as InputTypeRange
        // tag::rangefield[]
        range.should {
            have minimum(0)
            have maximum(50)
            have value(10)
            have step(5)
            is inRange
        }

        set range to 40

        range.should { have value(40) }
        // end::rangefield[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_form() {
        Form form = $('#form') as org.testatoo.bundle.html5.Form
        EmailField email_field = $('#email') as InputTypeEmail
        PasswordField password_field = $('#password') as InputTypePassword
        // tag::form[]
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
        Heading first_heading = $('#h1') as H1
        Heading last_heading = $('#h6') as H6
        // tag::heading[]
        first_heading.should { have text('heading 1') }
        last_heading.should { have text('heading 6') }
        // end::heading[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_image() {
        Image image = $('#image') as Img
        // tag::image[]
        image.should { have reference('http://localhost:8080/img/Montpellier.jpg') }
        // end::image[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_link() {
        Link link = $('#link') as A
        // tag::link[]
        link.should {
            have text('Link to dsl page')
            have reference('http://localhost:8080/dsl.html')
        }
        // end::link[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_listview() {
        ListView listview = $('#unordered_list') as Ul
        // tag::listview[]
        listview.should {
            have 5.items
            have items('Item 1', 'Item 2', 'Item 3', 'Item 4', 'Item 4')
        }
        // end::listview[]
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_panel() {
        Panel panel = $('#panel') as Div
        try {
            // tag::panel[]
            panel.should { have title('My panel title') }
            // end::panel[]
        } catch (AssertionError e) {
            assert e.message != null // The title is not available in Html (Div)
        }
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_datagrid() {
        DataGrid datagrid = $('#data_grid') as Table
        // tag::datagrid[]
        datagrid.should {
            have 4.columns
            have columns('', 'Column 1 title', 'Column 2 title', 'Column 3 title')

            have 4.rows
            have rows('Row 1', 'Row 2', 'Row 3', 'Row 4')
        }
        // end::datagrid[]

        // tag::column[]
        Column column = datagrid.column('Column 2 title') // Or datagrid.columns[2]
        column.should {
            have title('Column 2 title')

            have 4.cells
            have cells('cell 12', 'cell 22', 'cell 32', 'cell 42')
        }
        // end::column[]

        // tag::row[]
        Row row = datagrid.row('Row 3') // Or datagrid.row[2]
        row.should {
            have title('Row 3')

            have 3.cells
            have cells('cell 31', 'cell 32', 'cell 33')
        }
        // end::row[]

        // tag::cell[]
        datagrid.rows()[2].cells()[1].should {
            have value('cell 32')
        }
        // end::cell[]
    }
}
