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
package org.testatoo.bundle.html5.components

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Testatoo
import org.testatoo.bundle.html5.components.input.*
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator
import org.testatoo.core.property.Properties
import org.testatoo.core.state.States

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Keyboard.type
import static org.testatoo.core.input.Mouse.click_on
import static org.testatoo.core.property.Properties.*
import static org.testatoo.core.state.States.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class FieldsTest {

    @BeforeClass
    public static void setup() {
        Testatoo.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        open 'http://localhost:8080/components.html'
    }

    @AfterClass
    public static void tearDown() { Testatoo.evaluator.close() }

    // TODO add states : read-only and read-write out-of-range

    @Test
    public void should_have_expected_behaviours() {
        // Text field
        TextField textField = $('#text_field') as TextField

        textField.should { be States.enabled }
        textField.should { be States.visible }
        textField.should { be States.optional }

        textField.should { have label('Text') }
        textField.should { have Properties.label.containing('xt') }

        textField.should { have placeholder('Text') }
        textField.should { have Properties.placeholder.containing('xt') }
        textField.should { be States.empty }

        click_on textField
        type 'some value'

        textField.should { have value('some value') }
        textField.should { have Properties.value.containing('value') }
        textField.should { be States.filled }

        textField.reset()
        textField.should { be States.empty }

        // TextArea is treated as TextField
        textField = $('#text_area_field') as TextField

        textField.should { be States.enabled }
        textField.should { be States.visible }

        PasswordField passwordField = $('#password_field') as PasswordField

        passwordField.should { be States.enabled }
        passwordField.should { be States.visible }

        passwordField.should { have label('Password') }
        passwordField.should { have value('?art') }

        EmailField emailField = $('#email_field') as EmailField
        emailField.should { be States.disabled }

        PhoneField phoneField = $('#phone_field') as PhoneField
        phoneField.should {
            have pattern('^((\\+\\d{1,3}(-| )?\\(?\\d\\)?(-| )?\\d{1,5})|(\\(?\\d{2,6}\\)?))(-| )?(\\d{3,4})(-| )?(\\d{4})(( x| ext)\\d{1,5}){0,1}$')
            be States.enabled
            be States.invalid
        }

        click_on phoneField
        type 'bad phone number'
        phoneField.should {
            be States.invalid
        }

        phoneField.reset()

        click_on phoneField
        type '5146666666'
        phoneField.should {
            be States.valid
        }

        URLField urlField = $('#url_field') as URLField
        urlField.should { be States.enabled }

        SearchField searchField = $('#search_field') as SearchField
        searchField.should { be States.enabled }

        NumberField numberField = $('#number_field') as NumberField
        numberField.should { be States.enabled }
        numberField.should { have minimun(0) }
        numberField.should { have maximum(64) }
        numberField.should { have step(8) }

        RangeField rangeField = $('#range_field') as RangeField
        rangeField.should { be States.enabled }
        rangeField.should { have minimun(0)}
        rangeField.should { have maximum(50) }
        rangeField.should { have step(5) }

        ColorField colorField = $('#color_field') as ColorField
        colorField.should { be States.enabled }
        colorField.should { be States.optional }

        MonthField monthField = $('#month_field') as MonthField
        monthField.should { be States.enabled }
        monthField.should { be States.required }

        WeekField weekField = $('#week_field') as WeekField
        weekField.should { be States.enabled }

        DateField dateField = $('#date_field') as DateField
        dateField.should { be States.enabled }
        dateField.should { have minimun('2011-08-13') }
        dateField.should { have maximum('2012-06-25') }

        TimeField timeField = $('#time_field') as TimeField
        timeField.should { be States.enabled }

        DateTimeField dateTimeField = $('#datetime_field') as DateTimeField
        dateTimeField.should { be States.enabled }
    }

}
