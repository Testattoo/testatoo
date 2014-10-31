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

import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Testatoo
import org.testatoo.core.component.*
import org.testatoo.core.component.input.*
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ContainsDisplayTest {

    @BeforeClass
    public static void setup() {
        Testatoo.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        open 'http://localhost:8080/container.html'
    }

    @AfterClass
    public static void tearDown() { evaluator.close() }

    @Test
    public void test_contains() {
        Panel panel = $('#panel') as Panel
        Button visible_button = $('#visible_button') as Button
        Button invisible_button = $('#invisible_button') as Button

        panel.should {
            contains(
                visible_button,
                invisible_button
            )
        }

        Form form = $('#form') as Form
        EmailField email_field = $('[type=email]') as EmailField
        PasswordField password_field = $('[type=password]') as PasswordField
        Button submit_button = $('[type=submit]') as Button
        Button reset_button = $('[type=reset]') as Button

        form.should {
            contains(
                email_field,
                password_field,
                submit_button,
                reset_button
            )
        }

        try {
            panel.should {
                contains(
                    submit_button,
                    reset_button
                )
            }
        } catch (AssertionError e) {
            assert e.message == "Component Panel:panel does not contains expected component(s): [Button:$submit_button.id, Button:$reset_button.id]"
        }
    }

    @Test
    public void test_display() {
        Panel panel = $('#panel') as Panel
        Button visible_button = $('#visible_button') as Button
        Button invisible_button = $('#invisible_button') as Button

        panel.should {
            displays(
                visible_button
            )
        }

        try {
            panel.should {
                displays(
                    visible_button,
                    invisible_button
                )
            }
        } catch (AssertionError e) {
            assert e.message == "Component Button with id invisible_button expected visible but was hidden"
        }

        EmailField email_field = $('[type=email]') as EmailField
        try {
            panel.should {
                displays(
                    email_field
                )
            }
        } catch (AssertionError e) {
            assert e.message == "Component Panel:panel does not contains expected component(s): [EmailField:$email_field.id]"
        }
    }

}
