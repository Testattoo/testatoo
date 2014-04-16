package chosen

import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Testatoo
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ChosenComponentsTest {

    @BeforeClass
    public static void setup() {
        Testatoo.evaluator =  new WebDriverEvaluator(new FirefoxDriver())
        evaluator.registerScripts(this.getClass().getResourceAsStream('/chosen/custom.js').text)
        open 'http://localhost:8080/chosen/index.html'
    }

    @AfterClass public static void tearDown() { evaluator.close() }

    @Test
    public void standard_select_test() {

    }

    @Test
    public void multi_select_test() {

    }

    @Test
    public void option_group_support() {

    }

    @Test
    public void disabled_support_test() {

    }



}
