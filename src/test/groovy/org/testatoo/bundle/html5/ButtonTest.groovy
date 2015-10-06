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
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.action.Actions.visit
import static org.testatoo.core.property.Properties.text
import static org.testatoo.core.state.States.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ButtonTest {

    @BeforeClass
    public static void setup() {
        evaluator = new WebDriverEvaluator(new FirefoxDriver())
        visit 'http://localhost:8080/components.html'
    }

    @AfterClass
    public static void tearDown() { evaluator.close() }

    @Test
    public void should_override_text_support() {
        Button button = $('#button') as Button
        assert button.text == 'Button'
    }

    @Test
    public void button_should_have_expected_behaviours() {
        // input type=button
        Button button = $('#button') as Button

        button.should { be enabled }
        button.should { be visible }

        button.should { have text('Button') }

        // input type=submit
        button = $('#submit') as Button
        button.should { have text('Submit') }

        // input type=reset
        button = $('#reset') as Button
        button.should { have text('Reset') }

        // button element
        button = $('#btn') as Button
        button.should { have text('My Button Text') }
        button.should { have text.containing('My') }
        button.should { have text.containing('Button') }
        button.should { have text.containing('Text') }



    }
}