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
import org.testatoo.bundle.html5.components.Button
import org.testatoo.bundle.html5.components.Form
import org.testatoo.bundle.html5.components.Panel
import org.testatoo.bundle.html5.components.input.EmailField
import org.testatoo.bundle.html5.components.input.PasswordField
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ContainAndDisplayTest {

    @BeforeClass
    public static void setup() {
        Testatoo.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        open 'http://localhost:8080/container.html'
    }

    @AfterClass
    public static void tearDown() { evaluator.close() }

    @Test
    public void should_be_able_to_test_if_component_contains_other_component() {
        Panel panel = $('#panel') as Panel
        Button visible_button = $('#visible_button') as Button
        Button invisible_button = $('#invisible_button') as Button

        panel.should {
            contain(
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
            contain(
                    email_field,
                    password_field,
                    submit_button,
                    reset_button
            )
        }

        try {
            panel.should {
                contain(
                        submit_button,
                        reset_button
                )
            }
        } catch (AssertionError e) {
            assert e.message == "Component Panel:panel does not contain expected component(s): [Button:$submit_button.id, Button:$reset_button.id]"
        }
    }

    @Test
    public void should_be_able_to_test_if_component_display_other_component() {
        Panel panel = $('#panel') as Panel
        Button visible_button = $('#visible_button') as Button
        Button invisible_button = $('#invisible_button') as Button

        panel.should {
            display(
                    visible_button
            )
        }

        try {
            panel.should {
                display(
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
                display(
                        email_field
                )
            }
        } catch (AssertionError e) {
            assert e.message == "Component Panel:panel does not display expected component(s): [EmailField:$email_field.id]"
        }
    }

}
