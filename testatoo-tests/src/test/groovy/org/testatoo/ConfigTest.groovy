package org.testatoo

import org.junit.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.evaluator.EvaluatorHolder
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.junit.Assert.fail

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class ConfigTest {

    @Test
    public void cannot_register_null_evaluator() {
        try {
            EvaluatorHolder.register(null)
            fail()
        } catch (IllegalArgumentException e) {
            assert e.message.equals('Evaluator cannot be null')
        }

    }

    @Test
    public void can_obtain_the_underline_implementation() {
        WebDriver driver = new FirefoxDriver();
        EvaluatorHolder.register(new WebDriverEvaluator(driver))

        assert EvaluatorHolder.get().getImplementation() instanceof WebDriver
        assert EvaluatorHolder.get().getImplementation().equals(driver)

        driver.quit()
    }
}
