/**
 * Copyright (C) 2011 Ovea <dev@ovea.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package experimental.dsl

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.Testatoo

import static org.testatoo.core.Testatoo.*

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-01
 */
@RunWith(JUnit4)
class SampleDsl {

    @Delegate Testatoo testatoo = new Testatoo()
    @Delegate MyComponents myComponents = new MyComponents()

    @Before
    void before() {
        open '/'
        waitUntil login_view.is(visible)
    }

    @Test
    void login_page_contains_expected_elements() {
        assertThat login_email.is(visible)
        assertThat([login_email, login_password, login_button].are(visible))
        assertThat {
            login_email.is visible
            [login_email, login_password, login_button]*.are visible
            login_email.has placeholder.equalsTo('Email')
            [login_email, login_password]*.have placeholder.equalsTo('Email', 'Password')
            login_button.has text.equalsTo('Login')
        }
    }

    @Test
    public void can_login_logout() {
        login_email.enter('pastaga@guestful.com')
        login_password.enter('password666')
        login_button.click()
        waitUntil dashboard_view.is(visible)
        logout_button.click()
        waitUntil login_view.is(visible)
    }

    @Test
    public void on_login_failure_error_message_is_displayed() {
        assertThat login_error_message.is(hidden)
        login_email.enter("pastaga@guestful.com")
        login_password.enter("badpassword")
        login_button.click()
        waitUntil login_error_message.is(visible)
        assertThat {
            login_error_message.has text.containing("Login failed: Please verify your credentials and try again")
            login_view.is visible
        }
    }

}
