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
package org.testatoo.core

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.bundle.html5.Button
import org.testatoo.bundle.html5.Checkbox
import org.testatoo.bundle.html5.Form
import org.testatoo.bundle.html5.Radio
import org.testatoo.bundle.html5.input.EmailField
import org.testatoo.bundle.html5.list.Dropdown
import org.testatoo.bundle.html5.list.Item
import org.testatoo.core.action.MouseModifiers
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.junit.Assert.fail
import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Key.*
import static org.testatoo.core.property.Properties.*
import static org.testatoo.core.state.States.*
import static org.testatoo.core.action.Actions.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ErrorTest {

    @BeforeClass
    public static void setup() {
        evaluator = new WebDriverEvaluator(new FirefoxDriver())
        visit 'http://localhost:8080/error.html'
    }

    @AfterClass
    public static void tearDown() { evaluator.close() }

    @Test
    public void should_throw_an_error_on_not_supported_state() {
        EmailField email = $('#email') as EmailField
        try {
            email.should { be checked }
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Component EmailField:email does not support state Checked'
        }
    }

    @Test
    public void should_throw_an_error_on_not_supported_property() {
        EmailField email = $('#email') as EmailField
        try {
            email.should { have reference('reference') }
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Component EmailField:email does not support property Reference'
        }
    }

    @Test
    @Ignore
    public void should_throw_an_error_on_not_supported_action() {
        fail()
    }

    @Test
    public void should_not_be_able_to_submit_form_if_no_submit_button_available() {
        Form form = $('#form') as Form
        try {
            submit form
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Cannot submit form without submit button'
        }
    }

    @Test
    public void should_not_be_able_to_reset_form_if_no_reset_button_available() {
        Form form = $('#form') as Form
        try {
            reset form
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Cannot reset form without reset button'
        }
    }

    @Test
    public void should_throw_an_error_when_wait_until_condition_is_not_reached() {
        Button button = $('#inexisting_button') as Button;
        try {
            waitUntil 2.seconds, { button.is(available) }
            fail()
        } catch (RuntimeException e) {
            assert e.message == "Unable to reach the condition within 2 seconds (Component defined by expression \$('#inexisting_button') not found.)"
        }
    }

    @Test
    public void should_throw_an_error_on_invalid_click_sequence() {
        Form form = $('#form') as Form
        try {
            [CTRL, 'test', ALT].click form
            fail()
        } catch (IllegalArgumentException e) {
            assert e.message == 'Cannot type a modifier after some text'
        }

        try {
            evaluator.click('form', [MouseModifiers.RIGHT, MouseModifiers.DOUBLE])
            fail()
        } catch (IllegalArgumentException e) {
            assert e.message == 'Invalid click sequence'
        }
    }

    @Test
    public void should_throw_an_error_when_test_hidden_state_on_visible_component() {
        Dropdown dropDown = $('#elements') as Dropdown
        try {
            dropDown.should { be hidden }
            fail()
        } catch (AssertionError e) {
            assert e.message == 'Component Dropdown with id elements expected hidden but was visible'
        }
    }

    @Test
    public void should_throw_an_error_when_trying_to_unselect_a_disabled_option() {
        Dropdown dropDown = $('#elements') as Dropdown
        dropDown.items[0].should { be disabled }
        try {
            on dropDown unselect 'Helium'
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Item Helium is disabled and cannot be unselected'
        }
    }

    @Test
    public void should_throw_an_error_when_trying_to_check_checked_element() {
        Checkbox checkbox = $('#checkbox_1') as Checkbox
        checkbox.should { be checked }

        try {
            check checkbox
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Checkbox Checkbox:checkbox_1 is already checked and cannot be checked'
        }
    }

    @Test
    public void should_throw_an_error_when_trying_to_uncheck_unchecked_element() {
        Checkbox checkbox = $('#checkbox_2') as Checkbox
        checkbox.should { be unchecked }

        try {
            uncheck checkbox
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Checkbox Checkbox:checkbox_2 is already unchecked and cannot be unchecked'
        }
    }

    @Test
    public void should_throw_an_error_when_trying_to_unchek_a_radio() {
        Radio radio = $('#radio') as Radio
        radio.should { be checked }

        try {
            uncheck radio
            fail()
        } catch (ComponentException e) {
            assert e.message == "Unsupported action 'Uncheck' on component Radio:radio"
        }
    }

    @Test
    @Ignore
    public void should_throw_an_error_on_select_already_selected_item() {
        fail()
    }

    @Test
    public void should_throw_an_error_on_missing_component() {
        Dropdown dropDown = $('#elements') as Dropdown
        try {
            dropDown.should { be missing }
            fail()
        } catch (AssertionError e) {
            assert e.message == 'Component Dropdown with id elements expected missing but was available'
        }
    }

    @Test
    public void should_throw_an_error_when_$_selector_match_multiple_elements() {
        try {
            Item item = $('#elements option') as Item;
            item.should { be visible }
            fail()
        } catch (ComponentException e) {
            e.message == "Component defined by jQuery expression \$('#elements option') is not unique: got 5"
        }
    }

}
