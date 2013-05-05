/**
 * Copyright (C) 2008 Ovea <dev@ovea.com>
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
package com.guestfull.dashboard.web;

import com.guestfull.dashboard.web.component.ErrorMessage;
import org.testatoo.core.component.Button;
import org.testatoo.core.component.Panel;
import org.testatoo.core.component.PasswordField;
import org.testatoo.core.component.TextField;

import static org.testatoo.core.By.$;
import static org.testatoo.core.ComponentFactory.component;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class SecurityFactory {

    public static TextField email_field() {
        return component(TextField.class, $("#login_form :input[name=\"email\"]"));
    }

    public static PasswordField password_field() {
        return component(PasswordField.class, $("#login_form :input[name=\"password\"]"));
    }

    public static Button login_button() {
        return component(Button.class, $("#login_form button:first"));
    }

    public  static ErrorMessage login_error_message() {
        return component(ErrorMessage.class, $("#login_form .alert-error"));
    }

    public static Button logout_button() {
        return DashboardFactory.logout_button();
    }

}
