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
import org.testatoo.bundle.html5.input.ColorField
import org.testatoo.bundle.html5.input.DateField
import org.testatoo.bundle.html5.input.DateTimeField
import org.testatoo.bundle.html5.input.EmailField
import org.testatoo.bundle.html5.input.MonthField
import org.testatoo.bundle.html5.input.NumberField
import org.testatoo.bundle.html5.input.PasswordField
import org.testatoo.bundle.html5.input.PhoneField
import org.testatoo.bundle.html5.input.RangeField
import org.testatoo.bundle.html5.input.SearchField
import org.testatoo.bundle.html5.input.TextField
import org.testatoo.bundle.html5.input.TimeField
import org.testatoo.bundle.html5.input.URLField
import org.testatoo.bundle.html5.input.WeekField
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Keyboard.*
import static org.testatoo.core.input.Mouse.*
import static org.testatoo.core.property.Properties.*
import static org.testatoo.core.state.States.*
import static org.testatoo.core.action.Actions.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class FieldsTest {

    @BeforeClass
    public static void setup() {
        evaluator = new WebDriverEvaluator(new FirefoxDriver())
        visit 'http://localhost:8080/components.html'
    }

    @AfterClass
    public static void tearDown() { evaluator.close() }

    // TODO add states : read-only and read-write out-of-range

    @Test
    public void should_have_expected_behaviours() {
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

        click_on textField
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

        PhoneField phoneField = $('#phone_field') as PhoneField
        phoneField.should {
            have pattern('^((\\+\\d{1,3}(-| )?\\(?\\d\\)?(-| )?\\d{1,5})|(\\(?\\d{2,6}\\)?))(-| )?(\\d{3,4})(-| )?(\\d{4})(( x| ext)\\d{1,5}){0,1}$')
            be enabled
            be invalid
        }

        click_on phoneField
        type 'bad phone number'
        phoneField.should { be invalid }

        clear phoneField

        click_on phoneField
        type '5146666666'
        phoneField.should { be valid }

        URLField urlField = $('#url_field') as URLField
        urlField.should { be enabled }

        SearchField searchField = $('#search_field') as SearchField
        searchField.should { be enabled }

        NumberField numberField = $('#number_field') as NumberField
        numberField.should { be enabled }
        numberField.should { have minimun(0) }
        numberField.should { have maximum(64) }
        numberField.should { have step(8) }

        RangeField rangeField = $('#range_field') as RangeField
        rangeField.should { be enabled }
        rangeField.should { have minimun(0)}
        rangeField.should { have maximum(50) }
        rangeField.should { have step(5) }

        ColorField colorField = $('#color_field') as ColorField
        colorField.should { be enabled }
        colorField.should { be optional }

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
