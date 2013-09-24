package login

import org.testatoo.core.Testatoo
import org.testatoo.core.component.Button
import org.testatoo.core.component.Component
import org.testatoo.core.component.EmailField
import org.testatoo.core.component.Panel
import org.testatoo.core.component.PasswordField
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
