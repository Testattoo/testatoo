package experimental.dsl

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.testatoo.config.annotation.TestatooModules
import org.testatoo.config.junit.TestatooJunitRunner

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-01
 */
@RunWith(TestatooJunitRunner)
@TestatooModules(MyModule)
class SampleDsl {

    @Delegate
    private MyComponents myComponents = new MyComponents()

    @Before
    void before() {
        open "/"
        waitUntil login_view.is(visible)
    }

    @Test
    void login_page_contains_expected_elements() {
        assertThat(login_email.is(visible))
        assertThat([login_email, login_password, login_button]*.are(visible))
        assertThat {
            login_email.is visible
            [login_email, login_password, login_button]*.are visible
            login_email.has label.equalsTo("Email")
            [login_email, login_password, login_button]*.have label.equalsTo('Email', 'Password', 'Login')
        }
    }

    @Test
    public void can_login_logout() {
        on login_email.enter("pastaga@guestful.com")
        on login_password.enter("password666")
        on login_button.click()
        waitUntil dashboard_view.is(visible)
        on logout_button.click()
        waitUntil login_view.is(visible)
    }

    @Test
    public void on_login_failure_error_message_is_displayed() {
        assertThat(login_error_message().is(hidden()));

        enter("pastaga@guestful.com", on(email_field()));
        enter("badpassword", on(password_field()));

        clickOn(login_button());
        waitUntil(login_error_message().is(visible()));
        assertThat(login_error_message().has(text()).contains("Login failed: Please verify your credentials and try again"));

        assertThat(login_view().is(visible()));
    }

}
