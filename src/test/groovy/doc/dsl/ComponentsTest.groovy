/**
 * Copyright (C) 2016 Ovea (dev@ovea.com)
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
            have label('Radio label checked')
            be checked
        }

        unchecked_radio.should {
            have label('Radio label unchecked')
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
//        Dropdown dropdown = $('#os') as Select
//        dropdown.should {
//            have label('OS')
//            have selectedItems('None')
//        }

//
//            have 8.items
//            have items('None', 'Ubuntu', 'Fedora', 'Gentoo', 'XP', 'Vista', 'FreeBSD', 'OpenBSD')
//
//            have 3.groupItems
//            have groupItems('linux', 'win32', 'BSD')
//        }
//
//        on dropdown select 'Ubuntu'   // Selects the 'Ubuntu' in the dropdown or throws an exception if the item is disabled
        // end::dropdown[]
    }




}
