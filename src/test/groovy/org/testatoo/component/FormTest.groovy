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
package org.testatoo.component

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Testatoo
import org.testatoo.core.component.Button
import org.testatoo.core.component.Component
import org.testatoo.core.component.Form
import org.testatoo.core.component.Panel
import org.testatoo.core.component.input.EmailField
import org.testatoo.core.component.input.PasswordField
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator
import org.testatoo.core.property.Title

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Mouse.*
import static org.testatoo.core.input.Keyboard.*
import static org.testatoo.core.property.Properties.*
import static org.testatoo.core.state.States.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class FormTest {

    @BeforeClass
    public static void setup() {
        Testatoo.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        open 'http://localhost:8080/form.html'
    }

    @AfterClass
    public static void tearDown() { evaluator.close() }

    @Test
    public void should_have_expected_behaviours() {
        Form form = $('#form') as Form
        EmailField email_field = $('#form [type=email]') as EmailField
        PasswordField password_field = $('#form [type=password]') as PasswordField

        Button submit_button = $('#form [type=submit]') as Button
        Button reset_button = $('#form [type=reset]') as Button

        Message message = $('#form .alert') as Message

        // Can reset a form
        clickOn email_field
        type 'my@email.org'
        clickOn password_field
        type 'password'

        // By clicking on the button
        email_field.should { have text('my@email.org') }
        password_field.should { have text('password') }

        clickOn reset_button

        email_field.should { have text('') }
        password_field.should { have text('') }

        form.should { be valid }
        // Field in error
        clickOn email_field
        type 'bad email'
        email_field.should {
            be invalid
        }
        form.should { be invalid }

        email_field.reset()

        clickOn email_field
        type 'y@email.org'
        email_field.should {
            be valid
        }
        form.should { be valid }

        // Can submit a form
        message.should { have title('The form was submitted 0 time(s)') }
        message.should { have title.containing('The form was submitted') }

        clickOn submit_button
        message.should { have title('The form was submitted 1 time(s)') }

        clickOn submit_button
        message.should { have title('The form was submitted 2 time(s)') }
    }

    class Message extends Panel {
        Message() {
            support Title, { Component c -> c.evaluator.getString("\$('#${id}').text()") }
        }
    }

}
