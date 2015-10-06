package org.testatoo.core.traits

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.bundle.html5.Radio
import org.testatoo.bundle.html5.input.EmailField
import org.testatoo.bundle.html5.input.PasswordField
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.$
import static org.testatoo.core.Testatoo.getEvaluator
import static org.testatoo.core.Testatoo.setEvaluator
import static org.testatoo.core.action.Actions.visit

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class LabelSupportTest {

    @BeforeClass
    public static void setup() {
        evaluator = new WebDriverEvaluator(new FirefoxDriver())
        visit 'http://localhost:8080/traits.html'
    }

    @AfterClass
    public static void tearDown() { evaluator.close() }

    @Test
    public void should_have_expected_behaviours() {
        // Label with for reference
        EmailField email = $('#email') as EmailField
        assert email.label == 'Email'

        // Label as parent
        Radio male = $('#male') as Radio;
        assert male.label == 'Male'

        // Label as previous sibling
        PasswordField password = $('#password') as PasswordField
        assert password.label == 'Password'
    }
}
