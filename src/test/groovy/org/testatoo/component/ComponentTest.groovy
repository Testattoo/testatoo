/**
 * Copyright (C) 2014 Ovea (dev@ovea.com)
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
package org.testatoo.component

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Testatoo
import org.testatoo.core.component.Panel
import org.testatoo.core.component.Radio
import org.testatoo.core.component.input.TextField
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator
import org.testatoo.core.property.Size
import org.testatoo.core.property.Text
import org.testatoo.core.property.Title
import org.testatoo.core.state.Selected

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.property.Properties.*
import static org.testatoo.core.state.States.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ComponentTest {

    @BeforeClass
    public static void setup() {
        Testatoo.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        open 'http://localhost:8080/components.html'
    }

    @AfterClass
    public static void tearDown() { evaluator.close() }

    @Test
    public void can_override_a_state_and_property_directly_on_component_definition() {
        CustomPanel custom_panel = $('#custom-panel') as CustomPanel

        custom_panel.should { have title('CustomPanel Title') }
        custom_panel.should { be selected }

        // default size evaluation is on number of children
        custom_panel.should { have size(2) }
        // default text evaluation is on text of node
        custom_panel.should { have text('TEXT') }
    }

    @Test
    public void given_override_undefined_on_the_cartridge_evaluation_then_fallback_to_html5_cartridge() {
        CustomField custom_field = $('#custom-field') as CustomField

        // Property not overridden
        custom_field.should { have value('The Value') }

        // Property overridden
        custom_field.should { have label('Label overridden') }
    }

    @Test
    public void given_override_is_defined_but_return_undefined_on_the_cartridge_evaluation_then_fallback_to_html5_cartridge() {
        CustomField custom_field = $('#custom-field') as CustomField

        // Property not overridden
        custom_field.should { have value('The Value') }

        // Property overridden but fallback with the return of an undefined
        custom_field.should { have placeholder('The placeholder') }
    }

    @Test
    public void should_evaluate_component_equality() {
        Radio radio_1 = $('#radio') as Radio

        // The selector select the same component as radio_1
        Radio radio_2 = $('[type=radio]:checked') as Radio
        Radio radio_3 = $('#otherRadio') as Radio

        assert radio_1 == radio_2
        assert radio_1 != radio_3
    }

    @Test
    public void the_hashCode_of_a_component_is_based_on_its_id() {
        Radio radio_1 = $('#radio') as Radio

        assert radio_1.hashCode() == radio_1.id.hashCode()
    }

    class CustomPanel extends Panel {
        CustomPanel() {
            support Title, { return 'CustomPanel Title' }
            support Selected, { return true }
            support Size, Text
        }
    }

    class CustomField extends TextField {}
}
