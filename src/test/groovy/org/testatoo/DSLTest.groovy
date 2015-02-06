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
import org.testatoo.core.component.Checkbox
import org.testatoo.core.component.input.TextField
import org.testatoo.core.component.list.ListBox
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.property.Properties.label
import static org.testatoo.core.property.Properties.text
import static org.testatoo.core.property.Properties.text
import static org.testatoo.core.property.Properties.text
import static org.testatoo.core.property.Properties.text
import static org.testatoo.core.property.Properties.title
import static org.testatoo.core.property.Properties.value
import static org.testatoo.core.state.States.*
import static org.testatoo.core.input.Keyboard.*
import static org.testatoo.core.input.Mouse.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class DSLTest {

    @BeforeClass
    public static void setup() {
        Testatoo.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        open 'http://localhost:8080/components.html'
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

//    @Test
//    public void given_

    // reset
    // select

    // set a form easly
    // By the DSL
//    clickOn email_field
//    on email_field enter 'my@email.org'
//    on password_field enter 'password'
//
//    email_field.should { have text('my@email.org') }
//    password_field.should { have text('password') }
//
//    reset form
//
//    email_field.should { have text('') }
//    password_field.should { have text('') }
//    submit form
//    message.should { have title('The form was submitted 2 time(s)') }

    @Test
    public void test_AND() {
        Checkbox checkBox = $('#checkbox') as Checkbox
        checkBox.should {
            be enabled and be(visible)
        }
    }

    @Test
    public void test_OR() {
        ListBox listBox = $('#cities') as ListBox
        listBox.should {
            have 8.items or have(3.visibleItems)
        }
    }
}