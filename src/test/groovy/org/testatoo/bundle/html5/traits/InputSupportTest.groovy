package org.testatoo.bundle.html5.traits

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.bundle.html5.components.fields.EmailField
import org.testatoo.bundle.html5.components.fields.PasswordField
import org.testatoo.bundle.html5.components.fields.TextField
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.$
import static org.testatoo.core.Testatoo.getConfig
import static org.testatoo.core.dsl.Actions.visit

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class InputSupportTest {

    @BeforeClass
    public static void setup() {
        config.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        visit 'http://localhost:8080/traits.html'
    }

    @AfterClass
    public static void tearDown() { config.evaluator.close() }

    @Test
    public void should_have_expected_behaviours() {
        EmailField email = $('#email') as EmailField
        assert email.empty
        assert email.optional
        assert !email.filled
        assert !email.readOnly
        assert !email.required

        assert email.value == ''

        TextField text = $('#read_only_and_filled') as TextField
        assert text.filled
        assert text.readOnly
        assert text.value == 'Filled'

        PasswordField password = $('#password') as PasswordField
        assert password.required
        assert !password.optional
    }
}
