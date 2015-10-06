package org.testatoo.core.traits

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.bundle.html5.Radio
import org.testatoo.bundle.html5.input.EmailField
import org.testatoo.bundle.html5.input.TextField
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator
import org.testatoo.core.property.Text

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.action.Actions.visit

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class GenericSupportTest {
    @BeforeClass
    public static void setup() {
        evaluator = new WebDriverEvaluator(new FirefoxDriver())
        visit 'http://localhost:8080/traits.html'
    }

    @AfterClass
    public static void tearDown() { evaluator.close() }

    @Test
    public void should_have_expected_behaviours() {
        Radio male = $('#male') as Radio;
        assert male.enabled
        assert male.visible

        EmailField email = $('#email') as EmailField
        assert email.disabled
        assert email.available

        TextField text = $('#non_existing_id') as TextField
        assert text.missing

        // display:none
        TextField field = $('#hidden_1') as TextField
        assert  field.hidden
    }

}