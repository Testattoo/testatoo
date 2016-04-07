package doc.dsl

import org.junit.BeforeClass
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.WebDriverConfig
import org.testatoo.bundle.html5.Button
import org.testatoo.bundle.html5.Div

import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ComponentsTest {

    @ClassRule
    public static WebDriverConfig driver = new WebDriverConfig()

    @BeforeClass
    public static void before() {
        browser.open 'http://localhost:8080/components.html'
    }

    @Test
    public void should_have_expected_properties_and_states_supported_by_component() {
        // tag::component[]
        Button button = $('#button') as Button

        button.should {
            be available
            be enabled
            be visible
        }

        button = $('#submit') as Button
        button.should { be disabled }

        Div panel = $('#hidden_panel') as Div
        panel.should { be hidden }

        panel = $('#non_existing_id') as Div
        panel.should { be missing }
        // end::component[]
    }
}
