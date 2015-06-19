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
import org.testatoo.core.component.*
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.junit.Assert.fail
import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.property.Properties.text
import static org.testatoo.core.state.States.enabled

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
    public void should_identify_component_by_css() {

    }

    @Test
    public void should_identify_component_by_js() {

    }

    @Test
    public void component_without_identifier_fail() {
        try {
            ($('#button') as CustomButton).should { be enabled }
            fail()
        } catch (e) {
            assert e instanceof ComponentException
            assert e.message == "Missing @Identifier annotation on type $CustomButton.name" as String
        }
    }

    // custom component without identifier

    @Test
    public void on_bad_component_definition_an_error_it_thrown() {
        try {
            ($('#radio') as Button).should { be enabled }
            fail()
        } catch (e) {
            assert e instanceof ComponentException
            assert e.message == "Expected a $Button.simpleName for component with id 'radio', but was: $Radio.simpleName"
        }
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
    public void should_be_able_to_find_nested_component() {
        Section section = $('#section') as Section
        //TODO; david improve
        Paragraph paragraph = section.find('p:first')[0] as Paragraph

        paragraph.should { have text('Paragraph 1')}
    }

    @Test
    public void the_hashCode_of_a_component_is_based_on_its_id() {
        Radio radio_1 = $('#radio') as Radio
        assert radio_1.hashCode() == radio_1.id.hashCode()
    }

//    @Test
//    public void should_be_able_to_manage_inheritance() {
//        Button button = $('#btn_default') as Button
//        button.should { have text('Default') }
//
//        PrimaryButton primary_button = $('#btn_primary') as PrimaryButton
//        primary_button.should { have text('Primary') }
//
//        SuccessButton success_button = $('#btn_success') as SuccessButton
//        success_button.should { have text('Success') }
//
//        InfoButton info_button = $('#btn_info') as InfoButton
//        info_button.should { have text('Info') }
//
//        WarningButton warning_button = $('#btn_warning') as WarningButton
//        warning_button.should { have text('Warning') }
//
//        DangerButton danger_button = $('#btn_danger') as DangerButton
//        danger_button.should { have text('Danger') }
//
//        Button button_1 = $('#btn_primary') as Button
//        button_1.should { have text('Primary') }
//        // TODO update test on inheritance
////        try {
////            primary_button = $('#btn_warning') as PrimaryButton
////            primary_button.should { have text('Primary') }
////            fail()
////        } catch (Exception e) {
////            assert e.message == "Expected Text 'Primary' but was 'Warning'"
////        }
//    }




//    @IdentifiedByJs("it.is('button') && it.hasClass('btn-primary')")
//    private class PrimaryButton extends Button {}
//
//    @IdentifiedByJs("it.is('button') && it.hasClass('btn-success')")
//    private class SuccessButton extends  Button {}
//
//    @IdentifiedByJs("it.is('button') && it.hasClass('btn-info')")
//    private class InfoButton extends  Button {}
//
//    @IdentifiedByJs("it.is('button') && it.hasClass('btn-warning')")
//    private class WarningButton extends  Button {}
//
//    @IdentifiedByJs("it.is('button') && it.hasClass('btn-danger')")
//    private class DangerButton extends  Button {}

    private class CustomButton extends Button {}

//    private class CustomRadio extends Component {}


}
