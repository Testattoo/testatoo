package doc.junit

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.experimental.categories.Category
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.chrome.ChromeDriver
import org.testatoo.bundle.html5.input.InputTypeText
import org.testatoo.category.UserAgent
import org.testatoo.core.component.field.TextField
import org.testatoo.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.*

@RunWith(JUnit4)
@Category(UserAgent.Chrome)
class JunitStarterTest {

    @BeforeClass
    static void setup() {
        System.setProperty('webdriver.chrome.driver', '/usr/bin/chromedriver')
        config.evaluator = new WebDriverEvaluator(new ChromeDriver()) // <1>

        visit 'http://www.google.ca' // <2>
    }

    @Test
    void google_search_field_should_be_visible() {
        // Write you test here
        TextField search = $('#lst-ib') as InputTypeText    // <3>
        search.should {
            have focus
            be visible
        }
    }

    @AfterClass
    static void tearDown() {
        config.evaluator.close() // <4>
    }
}