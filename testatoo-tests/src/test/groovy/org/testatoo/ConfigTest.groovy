package org.testatoo

import org.junit.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Testatoo
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class ConfigTest {

    @Test
    public void can_obtain_the_underline_implementation() {
        WebDriver driver = new FirefoxDriver();
        Testatoo.evaluator = new WebDriverEvaluator(driver)

        assert Testatoo.evaluator.implementation instanceof WebDriver
        assert Testatoo.evaluator.implementation == driver

        Testatoo.evaluator.close()
    }
}
