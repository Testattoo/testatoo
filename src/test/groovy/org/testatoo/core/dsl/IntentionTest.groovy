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
package org.testatoo.core.dsl

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.bundle.html5.components.Checkbox
import org.testatoo.bundle.html5.components.Form
import org.testatoo.bundle.html5.components.Panel
import org.testatoo.bundle.html5.components.Radio
import org.testatoo.bundle.html5.components.fields.EmailField
import org.testatoo.bundle.html5.components.fields.PasswordField
import org.testatoo.bundle.html5.components.fields.TextField
import org.testatoo.bundle.html5.components.list.Dropdown
import org.testatoo.bundle.html5.components.list.ListBox
import org.testatoo.core.ComponentException
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator
import org.testatoo.core.input.Keyboard

import static org.junit.Assert.fail
import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.dsl.Actions.*
import static org.testatoo.core.dsl.Actions.visit
import static org.testatoo.core.input.Mouse.clickOn

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class IntentionTest {

    @BeforeClass
    public static void setup() {
        config.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        visit 'http://localhost:8080/dsl.html'
    }

    @AfterClass
    public static void tearDown() { config.evaluator.close() }

    @Test
    public void should_be_able_to_check_and_uncheck_a_checkbox() {
        Checkbox checkbox = $('#checkbox') as Checkbox

        assert checkbox.unchecked
        check checkbox
        assert checkbox.checked
        uncheck checkbox
        assert checkbox.unchecked

        try {
            uncheck checkbox
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Checkbox Checkbox:checkbox is already unchecked and cannot be unchecked'
        }

        check checkbox
        assert checkbox.checked

        try {
            check checkbox
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Checkbox Checkbox:checkbox is already checked and cannot be checked'
        }
    }

    @Test
    public void should_be_able_to_only_check_a_radio() {
        Radio radio = $('#radio') as Radio
        radio.unchecked

        check radio
        radio.checked

        // Try to check again fail
        try {
            check radio
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Radio Radio:radio is already checked and cannot be checked'
        }

        // Try to uncheck fail
        try {
            uncheck radio
            fail()
        } catch (ComponentException e) {
            assert e.message == "Unsupported action 'Uncheck' on component Radio:radio"
        }
    }

    @Test
    public void given_input_with_value_when_fill_value_the_field_is_reset_before() {
        TextField textField = $('#text_field') as TextField

        clickOn textField
        Keyboard.type 'Some fields'

        assert textField.value == 'Some fields'
        fill textField with 'Other fields'
        assert textField.value == 'Other fields'
    }

    @Test
    public void given_input_with_value_when_fill_value_we_trigger_a_blur_event() {
        TextField field = $('#firstname') as TextField
        Panel panel = $('#firstname_blur') as Panel

        assert panel.hidden
        fill field with 'invalid value'
        assert panel.visible
    }

    @Test
    public void given_input_with_value_when_clear_value_we_trigger_a_change_and_blur_event() {
        TextField field = $('#lastname') as TextField
        Panel panel = $('#lastname_reset') as Panel

        assert panel.hidden
        clear field
        assert panel.visible
    }

    @Test
    public void should_be_able_to_clear_input() {
        TextField textField = $('#text_field') as TextField
        fill textField with 'Some fields'

        assert textField.filled
        assert textField.value == 'Some fields'

        clear textField

        assert textField.empty
        assert textField.value == ''
    }

    @Test
    public void should_be_able_to_set_form_easily() {
        Form form = $('#form') as Form
        EmailField email = $('#form [type=email]') as EmailField
        PasswordField password = $('#form [type=password]') as PasswordField
        Message message = $('#form .alert') as Message

        assert message.title == 'The form was submitted 0 time(s)'

        fill email with 'my@email.org'
        fill password with 'password'

        assert email.value == 'my@email.org'
        assert password.value == 'password'

        reset form

        assert email.value == ''
        assert password.value == ''

        submit form

        assert message.title == 'The form was submitted 1 time(s)'
    }

    @Test
    public void should_be_able_to_select_element_in_dropdown_an_listbox() {
        Dropdown dropdown = $('#elements') as Dropdown
        assert dropdown.selectedItem.value == 'H'

        on dropdown select 'Pol'
        assert dropdown.selectedItem.value == 'Pol'

        ListBox listBox = $('#cities') as ListBox

        listBox.items.containsAll('Montreal', 'Quebec', 'Montpellier', 'New York', 'Casablanca', 'Munich')
        listBox.selectedItems.containsAll('New York', 'Munich')

        on listBox unselect 'New York', 'Munich'
        assert listBox.item('New York').unselected
        assert listBox.item('Munich').unselected

        on listBox select 'Montreal'
        assert listBox.items[0].selected

        on listBox select 'Montpellier'
        listBox.items[2].selected

        on listBox select listBox.items[4]
        assert listBox.items[4].selected

        on listBox unselect listBox.items[4]
        assert listBox.items[4].unselected

        select listBox.items[4]
        assert listBox.items[4].selected

        unselect listBox.items[4]
        assert listBox.items[4].unselected

        try {
            on listBox select 'Quebec'
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Item Quebec is disabled and cannot be selected'
        }
    }


    class Message extends Panel {
        @Override
        String getTitle() {
            config.evaluator.eval(id, "it.text()")
        }
    }
}
