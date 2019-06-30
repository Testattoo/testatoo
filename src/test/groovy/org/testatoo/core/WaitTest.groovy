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
package org.testatoo.core

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testatoo.Server
import org.testatoo.bundle.html5.Button
import org.testatoo.evaluator.webdriver.WebDriverEvaluator
import reactor.netty.DisposableServer

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver
import static org.junit.jupiter.api.Assertions.fail
import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@DisplayName("Wait until")
class WaitTest {
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

        config.waitUntil = 10.seconds
        visit BASE_URL + 'wait.html'
    }

    @AfterAll
    static void tearDown() {
        config.waitUntil = 2.seconds
        driver.close()
        server.dispose()
    }

    @Test
    @DisplayName("Should wait on condition")
    void should_be_able_to_wait_on_condition() {
        Browser.refresh()

        Button button = $('#add-message') as Button
        Button message = $('#msg') as Button

        button.should {
            be enabled
            be visible
        }

        message.should { be missing }

        clickOn button
        button.should { be disabled }
        button.should { be enabled }

        clickOn button
        button.should { be enabled }
    }

    @Test
    @DisplayName("Should throw an exception when condition not reach in expected duration")
    void should_throw_exception_when_condition_in_not_reach_in_expected_duration() {
        Browser.refresh()

        Button button = $('#add-message') as Button

        try {
            button.should { be disabled }
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription()
            description.appendText('Unable to reach the condition after 10000 milliseconds')
                .appendText('\nExpected: is disabled')
                .appendText('\n     but: is enabled')

            assert e.message == description.toString()
        }
    }
}
