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
import org.testatoo.bundle.html5.traits.InputSupport
import org.testatoo.bundle.html5.traits.LabelSupport
import org.testatoo.bundle.html5.traits.RangeSupport
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.$
import static org.testatoo.core.Testatoo.getConfig
import static org.testatoo.core.dsl.Actions.visit

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
    public void color_field_should_have_expected_behaviours() {
        assert ColorField in TextField

        ColorField colorField = $('#color_field') as ColorField

        assert colorField.visible
    }

    @Test
    public void date_field_should_have_expected_behaviours() {
        DateField in TextField
        DateField in RangeSupport

        DateField date = $('#date_field') as DateField

        assert date.minimun == '2011-08-13'
        assert date.maximum == '2012-06-25'
    }

    @Test
    public void dateTime_field_should_have_expected_behaviours() {
        DateTimeField in TextField
        DateTimeField in RangeSupport

        DateTimeField dateTime = $('#datetime_field') as DateTimeField

        assert dateTime.visible
    }

    @Test
    public void email_field_should_have_expected_behaviours() {
        assert EmailField in TextField

        EmailField email = $('#email_field') as EmailField
        assert email.visible
    }

    @Test
    public void month_field_should_have_expected_behaviours() {
        assert MonthField in TextField

        MonthField month = $('#month_field') as MonthField
        assert month.visible
    }

    @Test
    public void number_field_should_have_expected_behaviours() {
        assert NumberField in TextField
        assert NumberField in RangeSupport

        NumberField number = $('#number_field') as NumberField

        assert number.visible
    }

    @Test
    public void password_field_should_have_expected_behaviours() {
        assert PasswordField in LabelSupport
        assert PasswordField in InputSupport

        PasswordField password = $('#password_field') as PasswordField
        assert password.visible
    }

    @Test
    public void phone_field_should_have_expected_behaviours() {
        assert PhoneField in TextField

        PhoneField phone = $('#phone_field') as PhoneField

        assert phone.pattern == '^((\\+\\d{1,3}(-| )?\\(?\\d\\)?(-| )?\\d{1,5})|(\\(?\\d{2,6}\\)?))(-| )?(\\d{3,4})(-| )?(\\d{4})(( x| ext)\\d{1,5}){0,1}$'
    }

    @Test
    public void range_field_should_have_expected_behaviours() {
        RangeField in TextField
        RangeField in RangeSupport

        RangeField range = $('#range_field') as RangeField

        assert range.visible
    }

    @Test
    public void search_field_should_have_expected_behaviours() {
        assert SearchField in TextField

        SearchField searchField = $('#search_field') as SearchField
        assert searchField.visible
    }

    @Test
    public void text_field_should_have_expected_behaviours() {
        assert TextField in LabelSupport
        assert TextField in InputSupport

        TextField text = $('#text_field') as TextField
        assert text.visible
    }

    @Test
    public void time_field_should_have_expected_behaviours() {
        assert TimeField in TextField

        TimeField time = $('#time_field') as TimeField
        assert time.visible
    }

    @Test
    public void url_field_should_have_expected_behaviours() {
        assert URLField in TextField

        URLField url = $('#url_field') as URLField
        assert url.visible
    }

    @Test
    public void week_field_should_have_expected_behaviours() {
        assert WeekField in TextField

        WeekField week = $('#week_field') as WeekField
        assert week.visible
    }

}
