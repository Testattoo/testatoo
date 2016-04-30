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
package org.testatoo.bundle.html5.components.input

import org.junit.BeforeClass
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.WebDriverConfig
import org.testatoo.bundle.html5.input.Input
import org.testatoo.bundle.html5.input.InputTypeColor
import org.testatoo.bundle.html5.input.InputTypeDate
import org.testatoo.bundle.html5.input.InputTypeDateTime
import org.testatoo.bundle.html5.input.InputTypeEmail
import org.testatoo.bundle.html5.input.InputTypeMonth
import org.testatoo.bundle.html5.input.InputTypeNumber
import org.testatoo.bundle.html5.input.InputTypePassword
import org.testatoo.bundle.html5.input.InputTypeRange
import org.testatoo.bundle.html5.input.InputTypeSearch
import org.testatoo.bundle.html5.input.InputTypeTel
import org.testatoo.bundle.html5.input.InputTypeText
import org.testatoo.bundle.html5.input.InputTypeTime
import org.testatoo.bundle.html5.input.InputTypeURL
import org.testatoo.bundle.html5.input.InputTypeWeek
import org.testatoo.core.ComponentException
import org.testatoo.core.component.field.*

import static org.junit.Assert.fail
import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */

@RunWith(JUnit4)
class InputFieldTest {

    @ClassRule
    public static WebDriverConfig driver = new WebDriverConfig()

    @BeforeClass
    public static void before() {
        browser.open 'http://localhost:8080/components.html'
    }

    @Test
    public void input_should_have_expected_behaviours() {
        InputTypeEmail in EmailField
        InputTypeEmail in Input

        InputTypeEmail email = $('#email') as InputTypeEmail
        assert email.empty()
        assert email.optional()
        assert !email.filled()
        assert !email.readOnly()
        assert !email.required()
        assert email.valid()
        assert !email.invalid()
        assert email.value() == ''

        TextField text = $('#text_field') as InputTypeText
        assert text.placeholder() == 'Placeholder'

        text = $('#read_only_and_filled') as InputTypeText
        assert text.filled()
        assert text.readOnly()
        assert text.value() == 'Filled'

        try {
            text.value('New Value')
            fail()
        } catch (ComponentException e) {
            assert e.message == 'InputTypeText InputTypeText:read_only_and_filled is disabled and cannot be filled'
        }

        PasswordField password = $('#password') as InputTypePassword
        assert password.required()
        assert !password.optional()
        // Invalid cause required
        assert password.invalid()

        password.value('My Password')
        assert password.value() == 'My Password'
    }

    @Test
    public void color_field_should_have_expected_behaviours() {
        InputTypeColor in ColorField
        InputTypeColor in Input

        InputTypeColor colorField = $('#color_field') as InputTypeColor
        assert colorField.label() == 'Color'

        // Fail on CI
        assert colorField.value() == '#000000'
        colorField.value('#ff0000')
        assert colorField.value() == '#ff0000'
        assert colorField.valid()
    }

    @Test
    public void date_field_should_have_expected_behaviours() {
        InputTypeColor in DateField
        InputTypeColor in Input

        InputTypeDate date = $('#date_field') as InputTypeDate
        assert date.value() == ''
        assert date.step() == 0
        assert date.inRange()
        assert !date.outOfRange()
        assert date.minimum() == '2011-08-13'
        assert date.maximum() == '2012-06-25'

        date.value('2010-06-25')
        assert date.value() == '2010-06-25'
    }

    @Test
    public void dateTime_field_should_have_expected_behaviours() {
        InputTypeDateTime in DateTimeField
        InputTypeDateTime in Input

        DateTimeField dateTime = $('#datetime_field') as InputTypeDateTime

        assert dateTime.value() == ''
        dateTime.value('2010-06-25')
        assert dateTime.value() == '2010-06-25'
    }

    @Test
    public void email_field_should_have_expected_behaviours() {
        InputTypeEmail in EmailField
        InputTypeEmail in Input

        InputTypeEmail email = $('#email_field') as InputTypeEmail
        assert email.label() == 'Email'
    }

    @Test
    public void month_field_should_have_expected_behaviours() {
        InputTypeMonth in MonthField
        InputTypeMonth in Input

        InputTypeMonth month = $('#month_field') as InputTypeMonth
        assert month.label() == 'Month'
    }

    @Test
    public void number_field_should_have_expected_behaviours() {
        InputTypeNumber in NumberField
        InputTypeNumber in Input

        InputTypeNumber number = $('#number_field') as InputTypeNumber
        assert number.label() == 'Number'

        assert number.maximum() == 64
        assert number.minimum() == 0
        assert number.step() == 8
        assert number.value() == 2
        assert number.inRange()

        number.value('150')
        assert number.value() == 150
        assert number.outOfRange()
    }

    @Test
    public void password_field_should_have_expected_behaviours() {
        InputTypePassword in PasswordField
        InputTypePassword in Input

        InputTypePassword password = $('#password_field') as InputTypePassword
        assert password.label() == 'Password'
    }

    @Test
    public void phone_field_should_have_expected_behaviours() {
        InputTypeTel in PhoneField
        InputTypeTel in Input

        InputTypeTel phone = $('#phone_field') as InputTypeTel
        assert phone.pattern == '^((\\+\\d{1,3}(-| )?\\(?\\d\\)?(-| )?\\d{1,5})|(\\(?\\d{2,6}\\)?))(-| )?(\\d{3,4})(-| )?(\\d{4})(( x| ext)\\d{1,5}){0,1}$'
    }

    @Test
    public void range_field_should_have_expected_behaviours() {
        InputTypeRange in RangeField
        InputTypeRange in Input

        InputTypeRange range = $('#range_field') as InputTypeRange
        assert range.maximum() == 50
        assert range.minimum() == 0
        assert range.step() == 5
        assert range.inRange()
        assert !range.outOfRange()

        assert range.value() == 10
        range.value(40)
        assert range.value() == 40

        // Cause step 5
        range.value(42)
        assert range.value() == 40
    }

    @Test
    public void search_field_should_have_expected_behaviours() {
        InputTypeSearch in SearchField
        InputTypeSearch in Input

        InputTypeSearch searchField = $('#search_field') as InputTypeSearch
        assert searchField.label() == 'Search'

        searchField.value() == ''
        searchField.value('my search')
        assert searchField.value() == 'my search'
    }

    @Test
    public void text_field_should_have_expected_behaviours() {
        InputTypeText in TextField
        InputTypeText in Input

        TextField text = $('#text_field') as InputTypeText
        assert text.label() == 'Text'
    }

    @Test
    public void time_field_should_have_expected_behaviours() {
        InputTypeTime in TimeField
        InputTypeTime in Input

        InputTypeTime time = $('#time_field') as InputTypeTime
        assert time.label() == 'Time'

        assert time.value() == ''
        time.value('14:45')
        assert time.value() == '14:45'
    }

    @Test
    public void url_field_should_have_expected_behaviours() {
        InputTypeURL in URLField
        InputTypeURL in Input

        InputTypeURL url = $('#url_field') as InputTypeURL
        assert url.label() == 'URL'

        assert url.value() == ''
        url.value('http://mysite.org')
        assert url.value() == 'http://mysite.org'
    }

    @Test
    public void week_field_should_have_expected_behaviours() {
        InputTypeWeek in WeekField
        InputTypeWeek in Input

        WeekField week = $('#week_field') as InputTypeWeek
        assert week.label() == 'Week'

        assert week.value() == ''
        week.value('2016-W32')
        assert week.value() == '2016-W32'
    }
}
