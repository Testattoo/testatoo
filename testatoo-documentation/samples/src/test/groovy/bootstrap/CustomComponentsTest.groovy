package bootstrap

import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Testatoo
import org.testatoo.core.component.Button
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Mouse.click
import static org.testatoo.core.property.Properties.*
import static org.testatoo.core.state.States.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class CustomComponentsTest {

    @BeforeClass
    public static void setup() {
        Testatoo.evaluator =  new WebDriverEvaluator(new FirefoxDriver())
        evaluator.registerScripts(this.getClass().getResourceAsStream('/bootstrap/custom.js').text)
        open 'http://localhost:8080/bootstrap/index.html'
    }

    @AfterClass public static void tearDown() { evaluator.close() }

    @Test
    public void test_progress_bar() {
        ProgressBar progress_bar = $('#progress_bar') as ProgressBar
        Button plus = $('#plus') as Button
        Button minus = $('#minus') as Button

        assertThat progress_bar has value('60%')

        click plus
        assertThat progress_bar has value('70%')

        click minus
        click minus

        assertThat progress_bar has value('50%')
    }

    @Test
    public void test_tab_panel() {
        TabPanel tab_panel = $('#myTab') as TabPanel

        assertThat tab_panel has 2.tabs
        assertThat tab_panel.tabs[0] has title('Home')
        assertThat tab_panel.tabs[1] has title('Profile')

        assertThat tab_panel.tabs[0].panel is visible
        assertThat tab_panel.tabs[1].panel is hidden

        click tab_panel.tabs[1]

        waitUntil { tab_panel.tabs[0].panel.is(hidden) }
        assertThat tab_panel.tabs[1].panel is visible

//        assertThat tab_panel.tabs[0] is unSelected
//        assertThat tab_panel.tabs[1] is selected
//
//        select tab_panel.tabs[0]
//
//        assertThat tab_panel.tabs[0] is selected
//        assertThat tab_panel.tabs[1] is unSelected

    }

    @Test
    public void test_accordion() {
        Accordion accordion = $('#accordion') as Accordion
        assertThat accordion has 3.items

        assertThat accordion.items[0] has title('Item 1')
        assertThat accordion.items[1] has title('Item 2')
        assertThat accordion.items[2] has title('Item 3')

        assertThat accordion.items[0].panel is visible
        assertThat accordion.items[1].panel is hidden
        assertThat accordion.items[2].panel is hidden

//        assertThat accordion.items[0] is selected
//        assertThat accordion.items[1] is unSelected
//        assertThat accordion.items[2] is unSelected
//
//        click accordion.items[1]
//        assertThat accordion.items[0] is unSelected
//        assertThat accordion.items[1] is selected
//        assertThat accordion.items[2] is unSelected
//
//        select accordion.items[2]
//        assertThat accordion.items[0] is unSelected
//        assertThat accordion.items[1] is unSelected
//        assertThat accordion.items[2] is selected











    }
}
