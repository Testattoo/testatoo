package com.guestfull.dashboard.web;

import org.junit.Before;
import org.junit.Test;

import static com.guestfull.dashboard.web.SecurityFactory.*;
import static com.guestfull.dashboard.web.Views.*;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class SecurityTest extends WebTest {

    // =========== LOGIN / LOGOUT =======================

    @Before
    public void before() {
        open("/");
        waitUntil(login_view().is(visible()));
    }

    @Test
    public void login_page_contains_expected_elements()  {
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
