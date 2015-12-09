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
package org.testatoo.bundle.html5.components.fields

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.ComponentException
import org.testatoo.core.support.InputSupport
import org.testatoo.core.support.LabelSupport
import org.testatoo.core.support.RangeSupport
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.junit.Assert.fail
import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class FieldsTest {

    @BeforeClass
    public static void setup() {
        config.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        browser.open 'http://localhost:8080/components.html'
    }

    @AfterClass
    public static void tearDown() { config.evaluator.close() }

    @Test
    // TODO generify
    public void input_should_have_expected_behaviours() {
        EmailField email = $('#email') as EmailField
        assert email.empty
        assert email.optional
        assert !email.filled
        assert !email.readOnly
        assert !email.required
        assert email.valid
        assert !email.invalid
        assert email.value == ''

        TextField text = $('#text_field') as TextField
        assert text.placeholder == 'Placeholder'

        text = $('#read_only_and_filled') as TextField
        assert text.filled
        assert text.readOnly
        assert text.value == 'Filled'

        try {
            text.value = 'New Value'
            fail()
        } catch (ComponentException e) {
            assert e.message == 'TextField TextField:read_only_and_filled is disabled and cannot be filled'
        }

        PasswordField password = $('#password') as PasswordField
        assert password.required
        assert !password.optional
        // Invalid cause required
        assert password.invalid

        password.value = 'My Password'
        assert password.value == 'My Password'
    }

    @Test
    // TODO generify
    public void color_field_should_have_expected_behaviours() {
        assert ColorField in TextField

        ColorField colorField = $('#color_field') as ColorField
        assert colorField.label == 'Color'

        // Fail on CI
//        assert colorField.value == '#000000'
        colorField.value = '#ff0000'
        assert colorField.value == '#ff0000'
        assert colorField.valid
    }

    @Test
    // TODO generify
    public void date_field_should_have_expected_behaviours() {
        DateField in TextField
        DateField in RangeSupport

        DateField date = $('#date_field') as DateField
        assert date.value == ''
        assert date.step == 0
        assert date.inRange
        assert !date.outOfRange
        assert date.minimum == '2011-08-13'
        assert date.maximum == '2012-06-25'

        date.value = '2010-06-25'
        assert date.value == '2010-06-25'
    }

    @Test
    // TODO generify
    public void dateTime_field_should_have_expected_behaviours() {
        DateTimeField in TextField
        DateTimeField in RangeSupport

        DateTimeField dateTime = $('#datetime_field') as DateTimeField

        assert dateTime.value == ''
        dateTime.value = '2010-06-25'
        assert dateTime.value == '2010-06-25'
    }

    @Test
    // TODO generify
    public void email_field_should_have_expected_behaviours() {
        assert EmailField in TextField

        EmailField email = $('#email_field') as EmailField
        assert email.label == 'Email'
    }

    @Test
    // TODO generify
    public void month_field_should_have_expected_behaviours() {
        assert MonthField in TextField

        MonthField month = $('#month_field') as MonthField
        assert month.label == 'Month'
    }

    @Test
    // TODO generify
    public void number_field_should_have_expected_behaviours() {
        assert NumberField in TextField
        assert NumberField in RangeSupport

        NumberField number = $('#number_field') as NumberField
        assert number.label == 'Number'

        assert number.maximum == 64
        assert number.minimum == 0
        assert number.step == 8
        assert number.value == 0
        assert number.inRange

        number.value = 150
        assert number.value == 150
        // Fail on CI
//        assert number.outOfRange
    }

    @Test
    // TODO generify
    public void password_field_should_have_expected_behaviours() {
        assert PasswordField in LabelSupport
        assert PasswordField in InputSupport

        PasswordField password = $('#password_field') as PasswordField
        assert password.label == 'Password'
    }

    @Test
    // TODO generify
    public void phone_field_should_have_expected_behaviours() {
        assert PhoneField in TextField

        PhoneField phone = $('#phone_field') as PhoneField
        assert phone.pattern == '^((\\+\\d{1,3}(-| )?\\(?\\d\\)?(-| )?\\d{1,5})|(\\(?\\d{2,6}\\)?))(-| )?(\\d{3,4})(-| )?(\\d{4})(( x| ext)\\d{1,5}){0,1}$'
    }

    @Test
    // TODO generify
    public void range_field_should_have_expected_behaviours() {
        RangeField in TextField
        RangeField in RangeSupport

        RangeField range = $('#range_field') as RangeField
        assert range.maximum == 50
        assert range.minimum == 0
        assert range.step == 5
        assert range.inRange
        assert !range.outOfRange

        assert range.value == 10
        range.value = 40
        assert range.value == 40

        // Cause step 5
        range.value = 42
        assert range.value == 40
    }

    @Test
    // TODO generify
    public void search_field_should_have_expected_behaviours() {
        assert SearchField in TextField

        SearchField searchField = $('#search_field') as SearchField
        assert searchField.label == 'Search'

        searchField.value == ''
        searchField.value = 'my search'
        assert searchField.value == 'my search'
    }

    @Test
    // TODO generify
    public void text_field_should_have_expected_behaviours() {
        assert TextField in LabelSupport
        assert TextField in InputSupport

        TextField text = $('#text_field') as TextField
        assert text.label == 'Text'
    }

    @Test
    // TODO generify
    public void time_field_should_have_expected_behaviours() {
        assert TimeField in TextField

        TimeField time = $('#time_field') as TimeField
        assert time.label == 'Time'

        assert time.value == ''
        time.value = '14:45'
        assert time.value == '14:45'
    }

    @Test
    // TODO generify
    public void url_field_should_have_expected_behaviours() {
        assert URLField in TextField

        URLField url = $('#url_field') as URLField
        assert url.label == 'URL'

        assert url.value == ''
        url.value = 'http://mysite.org'
        assert url.value == 'http://mysite.org'
    }

    @Test
    // TODO generify
    public void week_field_should_have_expected_behaviours() {
        assert WeekField in TextField

        WeekField week = $('#week_field') as WeekField
        assert week.label == 'Week'

        assert week.value == ''
        week.value = '54'
        assert week.value == '54'
    }
}
