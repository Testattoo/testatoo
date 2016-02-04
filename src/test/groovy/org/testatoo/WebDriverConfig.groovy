package org.testatoo

import org.junit.rules.ExternalResource
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.getConfig

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class WebDriverConfig extends ExternalResource {
    @Override
    protected void before() throws Throwable {
        config.evaluator = new WebDriverEvaluator(new FirefoxDriver())
    }

    @Override
    protected void after() {
        config.evaluator.close()
    }
}
