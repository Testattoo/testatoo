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
package org.testatoo.bundle.html5

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.bundle.html5.input.*
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.$
import static org.testatoo.core.Testatoo.getConfig
import static org.testatoo.core.action.Actions.clear
import static org.testatoo.core.action.Actions.visit
import static org.testatoo.core.dsl.Mouse.clickOn
import static org.testatoo.core.property.Properties.*
import static org.testatoo.core.state.States.getReadonly

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class FieldsTest {

    @BeforeClass
    public static void setup() {
        config.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        visit 'http://localhost:8080/components.html'
    }

    @AfterClass
    public static void tearDown() { config.evaluator.close() }

    @Test
    public void text_field_should_have_expected_behaviours() {
        // Text field
        TextField textField = $('#text_field') as TextField

        textField.should { be enabled }
        textField.should { be visible }
        textField.should { be optional }

        textField.should { have label('Text') }
        textField.should { have label.containing('xt') }

        textField.should { have placeholder('Text') }
        textField.should { have placeholder.containing('xt') }
        textField.should { be empty }

        clickOn textField
        type 'some value'

        textField.should { have value('some value') }
        textField.should { have value.containing('value') }
        textField.should { be filled }

        clear textField
        textField.should { be empty }

        // TextArea is treated as TextField
        textField = $('#text_area_field') as TextField

        textField.should { be enabled }
        textField.should { be visible }

        PasswordField passwordField = $('#password_field') as PasswordField

        passwordField.should { be enabled }
        passwordField.should { be visible }

        passwordField.should { have label('Password') }
        passwordField.should { have value('?art') }

        EmailField emailField = $('#email_field') as EmailField
        emailField.should { be disabled }
    }

    @Test
    public void phone_field_should_have_expected_behaviours() {
        PhoneField phoneField = $('#phone_field') as PhoneField
        phoneField.should {
            have pattern('^((\\+\\d{1,3}(-| )?\\(?\\d\\)?(-| )?\\d{1,5})|(\\(?\\d{2,6}\\)?))(-| )?(\\d{3,4})(-| )?(\\d{4})(( x| ext)\\d{1,5}){0,1}$')
            be enabled
            be invalid
        }

        clickOn phoneField
        type 'bad phone number'
        phoneField.should { be invalid }

        clear phoneField

        clickOn phoneField
        type '5146666666'
        phoneField.should { be valid }
    }

    @Test
    public void url_fields_should_have_expected_behaviours() {
        URLField urlField = $('#url_field') as URLField
        urlField.should { be enabled }
    }

    @Test
    public void search_fields_should_have_expected_behaviours() {
        SearchField searchField = $('#search_field') as SearchField
        searchField.should { be enabled }
    }

    @Test
    public void number_fields_should_have_expected_behaviours() {
        NumberField numberField = $('#number_field') as NumberField
        numberField.should { be enabled }
        numberField.should { be inRange }
        numberField.should { have minimun(0) }
        numberField.should { have maximum(64) }
        numberField.should { have step(8) }

        // Fail on CI
//        fill numberField with '65'
//        numberField.should { be outOfRange }
    }

    @Test
    public void range_field_should_have_expected_behaviours() {
        RangeField rangeField = $('#range_field') as RangeField
        rangeField.should { be enabled }
        rangeField.should { have minimun(0) }
        rangeField.should { have maximum(50) }
        rangeField.should { have step(5) }
    }

    @Test
    public void color_field_should_have_expected_behaviours() {
        ColorField colorField = $('#color_field') as ColorField
        colorField.should { be enabled }
        colorField.should { be optional }
    }

    @Test
    public void date_fields_should_have_expected_behaviours() {
        MonthField monthField = $('#month_field') as MonthField
        monthField.should { be enabled }
        monthField.should { be required }

        WeekField weekField = $('#week_field') as WeekField
        weekField.should { be enabled }

        DateField dateField = $('#date_field') as DateField
        dateField.should { be enabled }
        dateField.should { have minimun('2011-08-13') }
        dateField.should { have maximum('2012-06-25') }

        TimeField timeField = $('#time_field') as TimeField
        timeField.should { be enabled }

        DateTimeField dateTimeField = $('#datetime_field') as DateTimeField
        dateTimeField.should { be enabled }
        dateTimeField.should { be readonly }
    }

}
