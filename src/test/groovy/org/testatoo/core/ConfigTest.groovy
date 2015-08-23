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

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.bundle.html5.Panel
import org.testatoo.bundle.html5.input.TextField
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.state.States.*
import static org.testatoo.core.action.Actions.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ConfigTest {

    @Test
    public void can_obtain_the_underline_implementation() {
        try {
            WebDriver driver = new FirefoxDriver();
            Testatoo.evaluator = new WebDriverEvaluator(driver)

            assert evaluator.implementation instanceof WebDriver
            assert evaluator.implementation == driver
        } finally {
            evaluator.close()
        }
    }

    @Test
    public void can_register_a_script() {
        try {
            WebDriver driver = new FirefoxDriver();
            Testatoo.evaluator = new WebDriverEvaluator(driver)

            visit 'http://localhost:8080/dsl.html'

            TextField field = $('#firstname') as TextField
            Panel error = $('#firstname_blur') as Panel

            field.should { be empty }
            error.should { be hidden }

            // Register scripts who
            // 1 - show the firstname_blur message
            // 2 - set an email in email field
            evaluator.registerScripts("function A_test() { \$('#firstname_blur').show()  }; A_test()")
            evaluator.registerScripts("function B_test() { \$('#firstname').val('Joe') }; B_test()")

            visit 'http://localhost:8080/dsl.html'

            field = $('#firstname') as TextField
            error = $('#firstname_blur') as Panel

            field.is(filled)
            error.should { be visible }
        } finally {
            evaluator.close()
        }
    }
}
