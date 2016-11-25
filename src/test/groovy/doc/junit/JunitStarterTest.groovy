package doc.junit

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.experimental.categories.Category
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.bundle.html5.input.InputTypeText
import org.testatoo.category.UserAgent
import org.testatoo.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.*

@RunWith(JUnit4)
@Category(UserAgent.All)
class JunitStarterTest {
    @BeforeClass
    static void setup() {
        config.evaluator = new WebDriverEvaluator(new ChromeDriver()) // <1>
        visit 'http://www.google.com' // <2>
    }

    @Test
    void google_search_field_should_be_visible() {
        // Write you test here
        InputTypeText search = $('#lst-ib') as InputTypeText    // <3>
        search.should { be visible }
    }

    @AfterClass
    static void tearDown() {
        config.evaluator.close() // <4>
    }
}