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
class HeadingTest {

    @BeforeClass
    public static void setup() {
        config.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        visit 'http://localhost:8080/components.html'
    }

    @AfterClass
    public static void tearDown() { config.evaluator.close() }

    @Test
    public void should_have_expected_behaviours() {
        assert Heading in TextSupport

        Heading h1 = $('#h1') as Heading
        assert h1.text == 'heading 1'

        Heading h2 = $('#h2') as Heading
        assert h2.text == 'heading 2'

        Heading h3 = $('#h3') as Heading
        assert h3.text == 'heading 3'

        Heading h4 = $('#h4') as Heading
        assert h4.text == 'heading 4'

        Heading h5 = $('#h5') as Heading
        assert h5.text == 'heading 5'

        Heading h6 = $('#h6') as Heading
        assert h6.text == 'heading 6'
    }
}
