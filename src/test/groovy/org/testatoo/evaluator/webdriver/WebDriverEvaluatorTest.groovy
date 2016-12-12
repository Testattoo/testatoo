/**
 * Copyright © 2016 Ovea (d.avenante@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.evaluator.webdriver

import org.junit.AfterClass
import org.junit.ClassRule
import org.junit.Test
import org.junit.experimental.categories.Category
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.WebDriver
import org.testatoo.category.UserAgent
import org.testatoo.WebDriverConfig
import org.testatoo.bundle.html5.Div
import org.testatoo.bundle.html5.input.InputTypeText
import org.testatoo.core.component.field.TextField
import org.testatoo.core.internal.Log

import static org.testatoo.WebDriverConfig.BASE_URL
import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
@Category(UserAgent.All)
class WebDriverEvaluatorTest {
    @ClassRule
    public static WebDriverConfig driver = new WebDriverConfig()

    @AfterClass
    static void after() {
        config.debug = false
    }

    @Test
    void should_be_able_to_obtain_the_underline_implementation() {
        try {
            assert config.evaluator.getImplementation(WebDriver) instanceof WebDriver
        } finally {
            config.evaluator.close()
        }
    }

    @Test
    void should_be_able_to_register_a_script() {
        try {
            visit BASE_URL + 'dsl.html'

            TextField field = $('#firstname') as InputTypeText
            Div error = $('#firstname_blur') as Div

            assert field.empty()
            assert !error.visible()

            // Register scripts who
            // 1 - show the first name_blur message
            // 2 - set an email in email field
            config.evaluator.registerScripts("function A_test() { \$('#firstname_blur').show()  }; A_test()")
            config.evaluator.registerScripts("function B_test() { \$('#firstname').val('Joe') }; B_test()")

            visit BASE_URL + 'dsl.html'

            field = $('#firstname') as InputTypeText
            error = $('#firstname_blur') as Div

            assert !field.empty()
            assert error.visible()
        } finally {
            config.evaluator.close()
        }
    }

    @Test
    void should_be_able_to_activate_logging() {
        assert !Log.debug
        config.debug = true
        assert Log.debug
    }
}