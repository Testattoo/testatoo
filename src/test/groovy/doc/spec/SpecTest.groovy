package doc.spec

import org.junit.Before
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.WebDriverConfig
import org.testatoo.core.component.Radio

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.Actions.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class SpecTest {

    @ClassRule
    public static WebDriverConfig driver = new WebDriverConfig()

    Radio male_radio
    Radio female_radio

    @Before
    public void before() {
        browser.open 'http://localhost:8080/spec.html'

        male_radio = $('[name=gender]:first') as org.testatoo.bundle.html5.Radio
        female_radio = $('[value=female]:last') as org.testatoo.bundle.html5.Radio
    }

    @Test
    public void should_select_gender() {
        // tag::specMethod[]
        male_radio.should {
            be unchecked
            have label('Male')
        }

        female_radio.should {
            be unchecked
            have label('Female')
        }

        check male_radio
        male_radio.should { be checked }
        female_radio.should { be unchecked }

        check female_radio
        male_radio.should { be unchecked }
        female_radio.should { be checked }
        // end::specMethod[]
    }
}
