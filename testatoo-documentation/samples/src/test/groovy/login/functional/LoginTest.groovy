/**
 * Copyright (C) 2013 Ovea (dev@ovea.com)
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
package login.functional

import login.Factory
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Testatoo
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Keyboard.type
import static org.testatoo.core.input.Mouse.clickOn
import static org.testatoo.core.state.States.getHidden
import static org.testatoo.core.state.States.getVisible

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class LoginTest {

    @Delegate
    private static Factory factory

    @Before
    public void setup() {
        Testatoo.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        evaluator.registerScripts(this.getClass().getResourceAsStream('/login/jquery.mockjax-1.5.3.min.js').text)
        evaluator.registerScripts(this.getClass().getResourceAsStream('/login/mocked-data.js').text)

        open 'http://localhost:8080/login/index.html'
        factory = new Factory()
    }

    @After
    public void tearDown() { evaluator.close() }

    @Test
    public void can_login() {
        user_is_not_logged()

        clickOn email_field
        type('test@email.org')

        clickOn password_field
        type('password666')

        clickOn login_button

        user_is_logged()
    }

    @Test
    public void login_failure() {
        user_is_not_logged()

        clickOn email_field
        type('test@email.org')

        clickOn password_field
        type('bad_credential')

        clickOn login_button

        waitUntil { error_message.be(visible) }
        user_is_not_logged()
    }

    private void user_is_logged() {
        waitUntil { login_succes.be(visible) }
    }

    private void user_is_not_logged() {
        login_succes.should { be hidden }
    }

}
