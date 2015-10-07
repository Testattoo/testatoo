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
package org.testatoo.bundle.html5

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.bundle.html5.input.EmailField
import org.testatoo.bundle.html5.input.PasswordField
import org.testatoo.core.ByCss
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator
import org.testatoo.core.property.Title

import static org.testatoo.core.Testatoo.$
import static org.testatoo.core.Testatoo.getConfig
import static org.testatoo.core.dsl.Actions.clear
import static org.testatoo.core.dsl.Actions.visit
import static org.testatoo.core.dsl.Mouse.clickOn
import static org.testatoo.core.property.Properties.*
import static org.testatoo.core.state.States.getInvalid
import static org.testatoo.core.state.States.getValid

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class FormTest {

    @BeforeClass
    public static void setup() {
        config.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        visit 'http://localhost:8080/form.html'
    }

    @AfterClass
    public static void tearDown() { config.evaluator.close() }

    @Test
    public void fom_should_have_expected_behaviours() {
        Form form = $('#form') as Form
        EmailField email_field = $('#form [type=email]') as EmailField
        PasswordField password_field = $('#form [type=password]') as PasswordField

        Button submit_button = $('#form [type=submit]') as Button
        Button reset_button = $('#form [type=reset]') as Button

        Message message = $('#form .alert') as Message

        // Can submit a form
        message.should { have title('The form was submitted 0 time(s)') }
        message.should { have title.containing('The form was submitted') }

        clickOn submit_button
        message.should { have title('The form was submitted 1 time(s)') }

        clickOn submit_button
        message.should { have title('The form was submitted 2 time(s)') }

        // Can reset a form
        clickOn email_field
        type 'my@email.org'
        clickOn password_field
        type 'password'

        // By clicking on the button
        email_field.should { have value('my@email.org') }
        password_field.should { have value('password') }

        clickOn reset_button

        email_field.should { have value('') }
        password_field.should { have value('') }

        form.should { be valid }
        // Field in error
        clickOn email_field
        type 'bad email'
        clickOn submit_button

        email_field.should { be invalid }
        form.should { be invalid }

        clear email_field

        clickOn email_field
        type 'y@email.org'
        clickOn submit_button
        email_field.should { be valid }
        form.should { be valid }
    }

    @ByCss('div')
    class Message extends Panel {
        Message() {
            support Title, "it.text()"
        }
    }

}
