package org.testatoo.core.traits

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.bundle.html5.Radio
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.$
import static org.testatoo.core.Testatoo.getEvaluator
import static org.testatoo.core.Testatoo.setEvaluator
import static org.testatoo.core.action.Actions.visit

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class CheckableTest {

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
        Radio female = $('#female') as Radio;

        assert male.checked
        assert !male.unchecked

        assert !female.checked
        assert female.unchecked

        female.check()
        assert male.unchecked
        assert female.checked
    }

}
