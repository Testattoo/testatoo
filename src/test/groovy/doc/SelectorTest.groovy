package doc

import org.junit.BeforeClass
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.WebDriverConfig
import org.testatoo.bundle.html5.Button

import static org.testatoo.core.Testatoo.$
import static org.testatoo.core.Testatoo.getBrowser
import static org.testatoo.core.Testatoo.getConfig

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class SelectorTest {

    @ClassRule
    public static WebDriverConfig driver = new WebDriverConfig()

    @BeforeClass
    public static void before() {
        config.scan 'org.testatoo'
        browser.open 'http://localhost:8080/selectors.html'
    }

    @Test
    public void should_select_unique_component_by_css_selector() {
        // tag::componentSelection[]
        $('css selector');

        // Samples
        Button button = $('#button') as Button              // <1>
        Button reset = $('.btn-secondary') as Button        // <2>
        Button submit = $('input:eq(1)') as Button          // <3>
        Button myButton = $('[data-role=myRole]') as Button // <4>

        // end::componentSelection[]

        assert button.text() == 'Button'
        assert reset.text() == 'Reset'
        assert submit.text() == 'Submit'
        assert myButton.text() == 'My Button Text'
    }

    @Test
    public void should_select_many_components_by_css_selector() {
        // tag::componentsSelection[]

        // end::componentsSelection[]
    }

}
