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

        button.should { have text('My Button Text')}
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
        }
        // end::dropdown[]


        //

//            have 8.items
//            have items('None', 'Ubuntu', 'Fedora', 'Gentoo', 'XP', 'Vista', 'FreeBSD', 'OpenBSD')
//
//            have 3.groupItems
//            have groupItems('linux', 'win32', 'BSD')
//        }

//        on dropdown select 'Ubuntu'   // Selects the 'Ubuntu' in the dropdown or throws an exception if the item is disabled



//        [source, java]
//        -------------------------------------------------------------------------------
//                DropDown dropdown = $('#dropdown') as DropDown
//        dropdown.should {
//            have label('OS')
//            have selectedItems('None')
//
//            have 8.items
//            have items('None', 'Ubuntu', 'Fedora', 'Gentoo', 'XP', 'Vista', 'FreeBSD', 'OpenBSD')
//
//            have 3.groupItems
//            have groupItems('linux', 'win32', 'BSD')
//        }
//
//        on dropdown select 'Ubuntu'   // Selects the 'Ubuntu' in the dropdown or throws an exception if the item is disabled
//        -------------------------------------------------------------------------------

    }




}
