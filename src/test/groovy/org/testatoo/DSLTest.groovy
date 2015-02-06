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
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Testatoo
import org.testatoo.core.component.*
import org.testatoo.core.component.input.EmailField
import org.testatoo.core.component.input.PasswordField
import org.testatoo.core.component.input.TextField
import org.testatoo.core.component.list.Dropdown
import org.testatoo.core.component.list.ListBox
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
        Testatoo.evaluator = new WebDriverEvaluator(new FirefoxDriver())
    }

    @AfterClass
    public static void tearDown() { evaluator.close() }

    @Test
    public void test_chaining_assert() {
        open 'http://localhost:8080/components.html'

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
        open 'http://localhost:8080/components.html'

        TextField textField = $('#text_field') as TextField

        clickOn textField
        type 'Some input'

        textField.should {
            have value('Some input')
        }

        on textField enter 'Other input'

        textField.should {
            have value('Other input')
        }
    }

    @Test
    public void should_be_able_to_reset_input() {
        open 'http://localhost:8080/components.html'

        TextField textField = $('#text_field') as TextField
        on textField enter 'Some input'

        textField.should {
            be filled
            have value('Some input')
        }

        reset textField

        textField.should {
            be empty
            have value('')
        }
    }

    @Test
    public void should_be_able_to_select_element_in_dropdown_an_listbox() {
        open 'http://localhost:8080/components.html'

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
        open 'http://localhost:8080/form.html'

        Form form = $('#form') as Form
        EmailField email_field = $('#form [type=email]') as EmailField
        PasswordField password_field = $('#form [type=password]') as PasswordField

        Message message = $('#form .alert') as Message

        on email_field enter 'my@email.org'
        on password_field enter 'password'

        email_field.should { have text('my@email.org') }
        password_field.should { have text('password') }

        reset form

        email_field.should { have text('') }
        password_field.should { have text('') }

        submit form

        message.should { have title('The form was submitted 1 time(s)') }
    }

    @Test
    public void test_AND() {
        open 'http://localhost:8080/components.html'

        Checkbox checkBox = $('#checkbox') as Checkbox
        checkBox.should {
            be enabled and be(visible)
        }
    }

    @Test
    public void test_OR() {
        open 'http://localhost:8080/components.html'

        ListBox listBox = $('#cities') as ListBox
        listBox.should {
            have 8.items or have(3.visibleItems)
        }
    }

    class Message extends Panel {
        Message() {
            support Title, { Component c -> c.evaluator.getString("\$('#${id}').text()") }
        }
    }

}