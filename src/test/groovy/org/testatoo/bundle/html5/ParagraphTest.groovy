package org.testatoo.bundle.html5

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.bundle.html5.traits.TextSupport
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.dsl.Actions.visit

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ParagraphTest {

    @BeforeClass
    public static void setup() {
        config.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        visit 'http://localhost:8080/components.html'
    }

    @AfterClass
    public static void tearDown() { config.evaluator.close() }

    @Test
    public void should_have_expected_behaviours() {
        assert Paragraph in TextSupport

        Paragraph paragraph = $('#p_1') as Paragraph

        assert paragraph.text == 'Paragraph 1'
    }
}
