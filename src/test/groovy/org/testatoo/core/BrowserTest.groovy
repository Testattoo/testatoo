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

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testatoo.Server
import org.testatoo.core.component.Component
import org.testatoo.evaluator.webdriver.WebDriverEvaluator
import reactor.netty.DisposableServer

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver
import static org.testatoo.core.Browser.*
import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@DisplayName("Browser behaviours")
class BrowserTest {
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
    @DisplayName("Should access to browser properties")
    void should_access_to_browser_properties() {
        open BASE_URL + 'index.html'

        assert title == 'Testattoo Rocks'
        assert pageSource.contains('<title>Testattoo Rocks</title>')
        assert url == BASE_URL + 'index.html'

        open(BASE_URL + 'keyboard.html')
        assert url == BASE_URL + 'keyboard.html'
    }

    @Test
    @DisplayName("Should navigate")
    void should_navigate() {
        open BASE_URL + 'index.html'

        assert url == BASE_URL + 'index.html'

        navigateTo(BASE_URL + 'keyboard.html')
        assert url == BASE_URL + 'keyboard.html'

        back()
        assert url == BASE_URL + 'index.html'

        forward()
        assert url == BASE_URL + 'keyboard.html'

        refresh()
        assert url == BASE_URL + 'keyboard.html'
    }

    @Test
    @DisplayName("Should manage Windows")
    void should_manage_windows() {
        open BASE_URL + 'index.html'
        Component link = $('#link') as Component
        Component form = $('#dsl-form') as Component

        assert windows.size() == 1
        assert link.available()
        assert !form.available()

        String main_window_id = windows[0].id

        clickOn link

        waitUntil({ windows.size() == 2 })
        switchTo(windows[1])
        assert form.available()

        windows[1].close()
        waitUntil({ windows.size() == 1 })
        assert windows[0].id == main_window_id
        assert windows[0].toString() == main_window_id

    }
}
