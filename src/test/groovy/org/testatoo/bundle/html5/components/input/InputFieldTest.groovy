/**
 * Copyright © 2016 Ovea (d.avenante@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
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
import org.junit.experimental.categories.Category
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.WebDriverConfig
import org.testatoo.bundle.html5.input.*
import org.testatoo.category.UserAgent
import org.testatoo.core.Browser
import org.testatoo.core.ComponentException
import org.testatoo.core.component.field.*

import static org.junit.Assert.fail
import static org.testatoo.WebDriverConfig.BASE_URL
import static org.testatoo.core.Testatoo.$
import static org.testatoo.core.Testatoo.visit

/**
 * @author David Avenante (d.avenante@gmail.com)
 */

@RunWith(JUnit4)
@Category(UserAgent.All)
class InputFieldTest {
    @ClassRule
    public static WebDriverConfig driver = new WebDriverConfig()

    @BeforeClass
    static void before() {
        visit BASE_URL + 'components.html'
    }

    @Test
    void input_should_have_expected_behaviours() {
        Browser.refresh()

        InputTypeEmail in EmailField
        InputTypeEmail in Input

        InputTypeEmail email = $('#email') as InputTypeEmail
        assert email.empty()
        assert !email.required()
        assert !email.readOnly()
        assert email.valid()
        assert email.value() == ''
        assert email.focused()

        TextField text = $('#text_field') as InputTypeText
        assert text.placeholder() == 'Placeholder'
        assert !text.focused()

        text = $('#read_only_and_filled') as InputTypeText
        assert !text.empty()
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
        // Invalid cause required
        assert !password.valid()

        password.value('My Password')
        assert password.value() == 'My Password'
    }

    @Test
    void color_field_should_have_expected_behaviours() {
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
    void date_field_should_have_expected_behaviours() {
        InputTypeColor in DateField
        InputTypeColor in Input

        InputTypeDate date = $('#date_field') as InputTypeDate
        assert date.value() == ''
        assert date.step() == 0
        assert date.inRange()
        assert date.minimum() == '2011-08-13'
        assert date.maximum() == '2012-06-25'

        date.value('2010-06-25')
        assert date.value() == '2010-06-25'
    }

    @Test
    void dateTime_field_should_have_expected_behaviours() {
        InputTypeDateTime in DateTimeField
        InputTypeDateTime in Input

        DateTimeField dateTime = $('#datetime_field') as InputTypeDateTime

        assert dateTime.value() == ''
        dateTime.value('2010-06-25')
        assert dateTime.value() == '2010-06-25'
    }

    @Test
    void email_field_should_have_expected_behaviours() {
        InputTypeEmail in EmailField
        InputTypeEmail in Input

        InputTypeEmail email = $('#email_field') as InputTypeEmail
        assert email.label() == 'Email'
    }

    @Test
    void month_field_should_have_expected_behaviours() {
        InputTypeMonth in MonthField
        InputTypeMonth in Input

        InputTypeMonth month = $('#month_field') as InputTypeMonth
        assert month.label() == 'Month'
    }

    @Test
    void number_field_should_have_expected_behaviours() {
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
        assert !number.inRange()
    }

    @Test
    void password_field_should_have_expected_behaviours() {
        InputTypePassword in PasswordField
        InputTypePassword in Input

        InputTypePassword password = $('#password_field') as InputTypePassword
        assert password.label() == 'Password'
        assert password.length() == 20
    }

    @Test
    void phone_field_should_have_expected_behaviours() {
        InputTypeTel in PhoneField
        InputTypeTel in Input

        InputTypeTel phone = $('#phone_field') as InputTypeTel
        assert phone.pattern == '^((\\+\\d{1,3}(-| )?\\(?\\d\\)?(-| )?\\d{1,5})|(\\(?\\d{2,6}\\)?))(-| )?(\\d{3,4})(-| )?(\\d{4})(( x| ext)\\d{1,5}){0,1}$'
    }

    @Test
    void range_field_should_have_expected_behaviours() {
        InputTypeRange in RangeField
        InputTypeRange in Input

        InputTypeRange range = $('#range_field') as InputTypeRange
        assert range.maximum() == 50
        assert range.minimum() == 0
        assert range.step() == 5
        assert range.inRange()

        assert range.value() == 10
        range.value(40)
        assert range.value() == 40

        // Cause step 5
        range.value(42)
        assert range.value() == 40
    }

    @Test
    void search_field_should_have_expected_behaviours() {
        InputTypeSearch in SearchField
        InputTypeSearch in Input

        InputTypeSearch searchField = $('#search_field') as InputTypeSearch
        assert searchField.label() == 'Search'
        assert searchField.length() == 200

        searchField.value() == ''
        searchField.value('my search')
        assert searchField.value() == 'my search'
    }

    @Test
    void text_field_should_have_expected_behaviours() {
        InputTypeText in TextField
        InputTypeText in Input

        TextField text = $('#text_field') as InputTypeText
        assert text.label() == 'Text'
        assert text.length() == 20
    }

    @Test
    void area_field_should_have_expected_behaviours() {
        TextArea in TextField
        TextArea in Input

        TextArea text = $('#text_area_field') as TextArea
        assert text.label() == 'TextArea'
        assert text.length() == 140
    }

    @Test
    void time_field_should_have_expected_behaviours() {
        InputTypeTime in TimeField
        InputTypeTime in Input

        InputTypeTime time = $('#time_field') as InputTypeTime
        assert time.label() == 'Time'

        assert time.value() == ''
        time.value('14:45PM')
        // 14:45PM on FF but 14:45 on Chrome (Field vs Widget)
        assert (time.value() as String).startsWith('14:45')
    }

    @Test
    void url_field_should_have_expected_behaviours() {
        InputTypeURL in URLField
        InputTypeURL in Input

        InputTypeURL url = $('#url_field') as InputTypeURL
        assert url.label() == 'URL'

        assert url.value() == ''
        url.value('http://mysite.org')
        assert url.value() == 'http://mysite.org'
        assert url.length()  == 150
    }

    @Test
    void week_field_should_have_expected_behaviours() {
        InputTypeWeek in WeekField
        InputTypeWeek in Input

        WeekField week = $('#week_field') as InputTypeWeek
        assert week.label() == 'Week'

        assert week.value() == ''
        week.value('2016-W32')
        assert week.value() == '2016-W32'
    }
}