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
package login

import org.testatoo.core.Testatoo
import org.testatoo.core.component.Button
import org.testatoo.core.component.Component
import org.testatoo.core.component.input.EmailField
import org.testatoo.core.component.Panel
import org.testatoo.core.component.input.PasswordField
import org.testatoo.core.property.Title

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Factory extends Testatoo {

    def email_field = $('[name="email"]') as EmailField
    def password_field = $('[name="password"]') as PasswordField
    def login_button = $('input[type=submit]') as Button
    def login_panel =  $('#login-form') as LoginPanel

    class LoginPanel extends Panel {
        LoginPanel() {
            support Title, {Component c -> c.evaluator.getString("\$('#${c.id} h1').text()") }
        }

    }

}
