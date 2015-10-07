package org.testatoo.bundle.html5.traits

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.bundle.html5.input.NumberField
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.$
import static org.testatoo.core.Testatoo.getConfig
import static org.testatoo.core.action.Actions.visit

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class RangeSupportTest {

    @BeforeClass
    public static void setup() {
        config.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        visit 'http://localhost:8080/traits.html'
    }

    @AfterClass
    public static void tearDown() { config.evaluator.close() }

    @Test
    public void should_have_expected_behaviours() {
        NumberField number = $('#number_field') as NumberField
        assert number

//        rangeField.should { have minimun(0) }
//        rangeField.should { have maximum(50) }
//        rangeField.should { have step(5) }

        // inRage, OUtOfRange

//        NumberField() {
//            support Minimum, Maximum, Step
//            support InRange, OutOfRange
//        }

    }


}