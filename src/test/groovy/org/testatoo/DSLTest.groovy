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
package org.testatoo

import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.bundle.html5.components.Checkbox
import org.testatoo.core.ComponentException
import org.testatoo.bundle.html5.components.Form
import org.testatoo.bundle.html5.components.Panel
import org.testatoo.bundle.html5.components.input.EmailField
import org.testatoo.bundle.html5.components.input.PasswordField
import org.testatoo.bundle.html5.components.input.TextField
import org.testatoo.bundle.html5.components.list.Dropdown
import org.testatoo.bundle.html5.components.list.ListBox
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator
import org.testatoo.core.property.Title

import static org.junit.Assert.fail
import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Keyboard.*
import static org.testatoo.core.input.Mouse.*
import static org.testatoo.core.property.Properties.*
import static org.testatoo.core.state.States.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class DSLTest {

    @BeforeClass
    public static void setup() {
        evaluator = new WebDriverEvaluator(new FirefoxDriver())
        scan DSLTest.package.name
    }

    @Before
    public void before() {
        open 'http://localhost:8080/dsl.html'
    }

    @AfterClass
    public static void tearDown() { evaluator.close() }

    @Test
    public void test_chaining_assert() {
        Checkbox checkBox = $('#checkbox') as Checkbox
        checkBox.should {
            be enabled
            be visible

            be unchecked
            have label('Check me out')
        }
    }

    @Test
    public void given_input_with_value_when_enter_value_the_field_is_reset_before() {
        TextField textField = $('#text_field') as TextField

        click_on textField
        type 'Some input'

        textField.should { have value('Some input') }
        on textField enter 'Other input'
        textField.should { have value('Other input') }
    }

    @Test
    public void given_input_with_value_when_enter_value_we_trigger_a_blur_event() {
        TextField field = $('#firstname') as TextField
        Panel panel = $('#firstname_blur') as Panel

        panel.should { be hidden }
        on field enter 'invalid value'
        waitUntil { panel.is(visible) }
    }

    @Test
    public void given_input_with_value_when_reset_value_we_trigger_a_change_and_blur_event() {
        TextField field = $('#lastname') as TextField
        Panel panel = $('#lastname_reset') as Panel

        panel.should { be hidden }
        reset field
        waitUntil { panel.becomes(visible) }
    }

    @Test
    public void should_be_able_to_reset_input() {
        TextField textField = $('#text_field') as TextField
        on textField enter 'Some input'

        textField.should {
            be filled
            have value('Some input')
        }

        assert textField.has(value('Some input'))

        reset textField

        textField.should {
            be empty
            have value('')
        }
    }

    @Test
    public void should_be_able_to_select_element_in_dropdown_an_listbox() {
        Dropdown dropdown = $('#elements') as Dropdown
        dropdown.should { have selectedItems('Helium') }

        on dropdown select 'Polonium'
        dropdown.should { have selectedItems('Polonium') }

        ListBox listBox = $('#cities') as ListBox

        listBox.should { have items('Montreal', 'Quebec', 'Montpellier', 'New York', 'Casablanca', 'Munich') }
        listBox.should { have selectedItems('New York', 'Munich') }

        on listBox unselect 'New York'
        on listBox unselect 'Munich'

        on listBox select 'Montreal'
        on listBox select 'Montpellier'

        try {
            on listBox select 'Quebec'
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Item Quebec is disabled and cannot be selected'
        }
    }

    @Test
    public void should_be_able_to_set_form_easily() {
        Form form = $('#form') as Form
        EmailField email_field = $('#form [type=email]') as EmailField
        PasswordField password_field = $('#form [type=password]') as PasswordField

        Message message = $('#form .alert') as Message

        on email_field enter 'my@email.org'
        on password_field enter 'password'

        email_field.should { have value('my@email.org') }
        password_field.should { have value('password') }

        reset form

        email_field.should { have value('') }
        password_field.should { have value('') }

        submit form

        message.should { have title('The form was submitted 1 time(s)') }
    }

    @Test
    public void test_AND() {
        Checkbox checkBox = $('#checkbox') as Checkbox
        checkBox.should { be enabled and be(visible) }
    }

    @Test
    public void test_OR() {
        ListBox listBox = $('#cities') as ListBox
        listBox.should { have 8.items or have(3.visibleItems) }
    }

    // TODO OPTIONAL
//    @IdentifiedByCss('div')
    class Message extends Panel {
        Message() {
            support Title, "it.text()"
        }
    }

}