/**
 * Copyright © 2018 Ovea (d.avenante@gmail.com)
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


import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testatoo.Server
import org.testatoo.bundle.html5.Div
import org.testatoo.bundle.html5.InputTypeText
import org.testatoo.core.internal.Log
import reactor.netty.DisposableServer

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver
import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@DisplayName("Webdriver Evaluator")
class WebDriverEvaluatorTest {
    private static DisposableServer server
    private static final int PORT = 9090
    private static String BASE_URL = "http://localhost:${PORT}/"
    private static WebDriver driver

    @BeforeAll
    static void before() {
        server = Server.start(9090)

        chromedriver().setup()
        driver = new ChromeDriver()
        config.evaluator = new WebDriverEvaluator(driver)
    }

    @AfterAll
    static void tearDown() {
        config.debug = false
        server.dispose()
        driver.close()
    }

    @Test
    @DisplayName("Should obtain the underline implementation")
    void should_obtain_the_underline_implementation() {
        assert config.evaluator.driver instanceof WebDriver
    }

    @Test
    @DisplayName("Should register a script")
    void should_register_a_script() {
        try {
            // Page with jquery missing
            visit BASE_URL + 'popup.html'

            InputTypeText field = $('[id="first.name"]') as InputTypeText
            Div error = $('#firstname_blur') as Div

            assert field.empty()
            assert !error.visible()

            // Register scripts who
            // 1 - show the first name_blur message
            // 2 - set an email in email field
            config.evaluator.registerScripts("function A_test() { \$('#firstname_blur').show()  }; A_test()")
            config.evaluator.registerScripts("function B_test() { \$('[id=\"first.name\"]').val('Joe') }; B_test()")

            visit BASE_URL + 'popup.html'

            field = $('[id="first.name"]') as InputTypeText
            error = $('#firstname_blur') as Div

            assert !field.empty()
            assert error.visible()

            // Page with jquery already available
            visit BASE_URL + 'index.html'

            Div created = $('#created') as Div
            created.should { be missing }

            // Register scripts who
            // Create the missing TAG
            config.evaluator.registerScripts("function create() { var element = document.createElement('div'); " +
                "element.id = 'created'; document.body.appendChild(element);}; create()")

            visit BASE_URL + 'index.html'

            created = $('#created') as Div
            created.should { be available }

        } finally {
            config.evaluator.close()
        }
    }

    @Test
    @DisplayName("Should inject a jQuery in noConflict mode")
    void should_add_jquery_if_missing() {
        visit BASE_URL + 'popup.html'

        assert 'Joe' == config.evaluator.eval(null, "\$('#last_name').val('Joe').val()")
    }

    @Test
    @DisplayName("Should activate logging")
    void should_be_able_to_activate_logging() {
        assert !Log.debug
        config.debug = true
        assert Log.debug
    }
}
