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

import org.junit.BeforeClass
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import static org.testatoo.core.Testatoo.open

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@Ignore
@RunWith(JUnit4)
class LoginTest {

    @BeforeClass
    public static void setup() {
        open '/login/index.html'
    }

    @Test
    public void can_login() {
//        assert user_is_not_logged()

//        email_field.type('test@email.org')
//        password_field.type('password666')

//        login_button.click()

//        assert user_is_logged()
    }

    @Test
    public void login_failure() {

    }

    /* expected */
//    @Test
//    public void can_login() {
//        assert user_is_not_logged()
//
//        on(emailField).type('test@email.org')
//        on(passwordField).type('password666')
//
//        clickOn loginView.loginButton
//
//        assert user_is_logged()
//    }
}
