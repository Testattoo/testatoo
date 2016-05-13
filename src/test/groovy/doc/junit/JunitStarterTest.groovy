package doc.junit

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.bundle.html5.input.InputTypeText
import org.testatoo.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.*

@RunWith(JUnit4)
class JunitStarterTest {
    @BeforeClass
    public static void setup() {
        config.evaluator = new WebDriverEvaluator(new FirefoxDriver()) // <1>
        visit 'http://www.google.com' // <2>
    }

    @Test
    public void google_search_field_should_be_visible() {
        // Write you test here
        InputTypeText search = $('#lst-ib') as InputTypeText    // <3>
        search.should { be visible }
    }

    @AfterClass
    public static void tearDown() {
        config.evaluator.close() // <4>
    }
}
