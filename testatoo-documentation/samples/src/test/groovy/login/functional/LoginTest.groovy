package login.functional

import login.Factory
import login.TestModule
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.testatoo.config.TestatooJunitRunner
import org.testatoo.config.TestatooModules

import static org.testatoo.core.Testatoo.open

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(TestatooJunitRunner.class)
@TestatooModules(TestModule)
class LoginTest {

    @Delegate
    private static Factory component = new Factory()

    @BeforeClass
    public static void open() {
        open('/login/index.html')
    }

    @Test
    public void can_login() {
//        assert user_is_not_logged()

        email_field.type('test@email.org')
        password_field.type('password666')

        login_button.click()

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
