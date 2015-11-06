package org.testatoo.core.dsl

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.config
import static org.testatoo.core.dsl.Actions.visit

/**
 * @author David Avenante (d.avenante@gmail.com)
 */

@RunWith(JUnit4)
class DSLTest {

    @BeforeClass
    public static void setup() {
        config.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        visit 'http://localhost:8080/dsl.html'
    }

    @AfterClass
    public static void tearDown() { config.evaluator.close() }



}
