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
package login.unit

import login.Factory
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import static org.testatoo.core.Testatoo.open

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class LoginPageTest {

    @Delegate
    private static Factory factory = new Factory()

    @BeforeClass
    public static void setup() {
        open '/login/index.html'
    }

    @Test
    public void page_contains_expected_elements() {
        assert login_panel.is(visible)

//        assertThat login_button is visible

//        assert login_panel.has(title.equalsTo('Login Form'))

//        assert login_panel.contains(email_field, password_field, login_button)

//        assert email_field.has(label.equalsTo('Email'))
//        assert email_field.has(placeholder.equalsTo('joe@blow.org'))

//        assert password_field.has(label.equalsTo('Password'))
//
//        assert login_button.has(text.equalsTo('Login'))
    }

}
