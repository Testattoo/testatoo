package experimental.dsl

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.testatoo.config.annotation.TestatooModules
import org.testatoo.config.junit.TestatooJunitRunner
import org.testatoo.experimental.dsl.Testatoo

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-01
 */
@RunWith(TestatooJunitRunner)
@TestatooModules(MyModule)
class SampleDsl {

    @Delegate
    Testatoo testatoo = new Testatoo()

    @Before
    void before() {
        open("/");
        waitUntil(login_view().is(visible()));
    }

    @Test
    void login_page_contains_expected_elements() {
        assertThat(page().displays(
            email_field(),
            password_field(),
            login_button()
        ));

        assertThat(email_field().has(label()).equalsTo("Email"));
        assertThat(password_field().has(label()).equalsTo("Password"));
        assertThat(login_button().has(text()).equalsTo("Login"));
    }

    @Test
    public void can_login_logout() {
        enter("pastaga@guestful.com", on(email_field()));
        enter("password666", on(password_field()));

        clickOn(login_button());
        waitUntil(dashboard_view().is(visible()));

        clickOn(logout_button());
        waitUntil(login_view().is(visible()));
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
