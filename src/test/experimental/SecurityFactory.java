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
