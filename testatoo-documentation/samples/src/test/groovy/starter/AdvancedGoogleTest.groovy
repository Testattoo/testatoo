package starter

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Testatoo
import org.testatoo.core.component.Button
import org.testatoo.core.component.input.TextField
import org.testatoo.core.component.list.ListView
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Keyboard.*
import static org.testatoo.core.input.Mouse.clickOn
import static org.testatoo.core.property.Properties.*
import static org.testatoo.core.state.States.*

/**
 * @author davenante
 */
class AdvancedGoogleTest {

    @BeforeClass
    public static void setup() {
        Testatoo.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        open 'http://www.google.com'
    }

    @AfterClass
    public static void tearDown() { evaluator.close() }

    @Test
    public void simple_test() {
        TextField searchField = $('#gbqfq') as TextField
        Button searchButton = $('#gbqfb') as Button
        ListView resultList = $('#rso') as ListView

        assertThat resultList is missing
        assertThat searchField is visible

        clickOn searchField
        type('testatoo')
        clickOn searchButton

        waitUntil { resultList.is visible }

        assertThat resultList.items[0] has value.containing('Testatoo')
    }
}
