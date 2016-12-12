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
package org.testatoo.bundle.html5

import org.junit.BeforeClass
import org.junit.ClassRule
import org.junit.Test
import org.junit.experimental.categories.Category
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.WebDriverConfig
import org.testatoo.bundle.html5.input.InputTypeNumber
import org.testatoo.bundle.html5.input.InputTypePassword
import org.testatoo.category.UserAgent
import org.testatoo.core.ComponentException
import org.testatoo.core.component.field.NumberField
import org.testatoo.core.component.field.PasswordField
import org.testatoo.core.input.MouseModifiers

import static org.junit.Assert.fail
import static org.testatoo.WebDriverConfig.BASE_URL
import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Key.ALT
import static org.testatoo.core.input.Key.CTRL

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
@Category(UserAgent.All)
class ErrorsTest {
    @ClassRule
    public static WebDriverConfig driver = new WebDriverConfig()

    @BeforeClass
    static void before() {
        visit BASE_URL + 'error.html'
    }

    @Test
    void should_not_check_already_checked_element() {
        CheckBox checkbox = $('#checkbox_1') as CheckBox
        checkbox.should { be checked }

        try {
            check checkbox
        } catch (ComponentException e) {
            assert e.message == 'CheckBox CheckBox:checkbox_1 is already checked and cannot be checked'
        }
    }

    @Test
    void should_not_be_able_to_submit_form_if_no_submit_button_available() {
        Form form = $('#form') as Form
        try {
            submit form
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Cannot submit form without submit button'
        }
    }

    @Test
    void should_not_be_able_to_reset_form_if_no_reset_button_available() {
        Form form = $('#form') as Form
        try {
            reset form
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Cannot reset form without reset button'
        }
    }

    @Test
    void should_throw_an_error_on_invalid_click_sequence() {
        Form form = $('#form') as Form
        try {
            [CTRL, 'test', ALT].click form
            fail()
        } catch (IllegalArgumentException e) {
            assert e.message == 'Cannot type a modifier after some text'
        }

        try {
            config.evaluator.click('form', [MouseModifiers.RIGHT, MouseModifiers.DOUBLE])
            fail()
        } catch (IllegalArgumentException e) {
            assert e.message == 'Invalid click sequence'
        }
    }

    @Test
    void should_throw_an_error_when_asking_length_on_input_whiteout_length() {
        PasswordField password = $('#password') as InputTypePassword
        try {
            password.length()
        } catch (ComponentException e) {
            assert e.message == 'Not length defined for component InputTypePassword InputTypePassword:password'
        }
    }

    @Test
    void should_throw_an_error_when_asking_value_on_number_field_whiteout_value() {
        NumberField number = $('#number') as InputTypeNumber
        try {
            number.value()
        } catch (ComponentException e) {
            assert e.message == 'InputTypeNumber InputTypeNumber:number is empty and has no value'
        }
    }
}
