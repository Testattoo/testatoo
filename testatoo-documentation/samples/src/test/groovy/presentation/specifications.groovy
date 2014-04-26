package presentation

import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import static org.testatoo.core.Testatoo.assertThat
import static org.testatoo.core.Testatoo.open

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class specifications {

    @BeforeClass
    public static void open() {
        open('/presentation/index.html')
    }


    @Test
    public void except_to_not_annoy_people() {
//        assertThat presentation has 10.sections
    }

    @Test
    public void try_to_capture_the_audience_with_a_punching_title() {
        assertThat presentation has title('Functional Test With - Testatoo')
    }



}
