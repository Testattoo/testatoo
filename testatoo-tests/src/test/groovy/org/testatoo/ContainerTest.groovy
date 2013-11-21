package org.testatoo

import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.testatoo.config.TestatooJunitRunner
import org.testatoo.config.TestatooModules
import org.testatoo.core.component.Panel

import static org.testatoo.core.Testatoo.assertThat
import static org.testatoo.core.Testatoo.open

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(TestatooJunitRunner.class)
@TestatooModules(TestModule)
class ContainerTest {

    @BeforeClass
    public static void openTestPage() {
        open('/container.html')
    }


    @Test
    public void test_contains() {
        Panel panel = $('#panel') as Panel

//        assertThat panel has contains(
//
//        )



//        assertThat(panel.contains(
//                new Button('button_in_visible_panel'),
//                new Button('invisible_button_in_visible_panel')
//        ));

    }

    //
//
//    @Test
//    public void test_contains() {
//        open('/component.html');
//
//        assertThat(page().contains(
//                new Button('button'),
//                new TextField('text_field'),
//                new Radio('radio')
//        ));
//
//        Panel panel = new Panel('panel');
//        assertThat(panel.contains(
//                new Button('button_in_visible_panel'),
//                new Button('invisible_button_in_visible_panel')
//        ));
//
//        Panel invisible_panel = new Panel('invisible_panel');
//        try {
//            assertThat(invisible_panel.contains(
//                    new Button('button_in_visible_panel')
//            ));
//        } catch (AssertionError e) {
//            assertEquals('Component Panel with id: \'invisible_panel\' does no contains component Button with id: \'button_in_visible_panel\'', e.getMessage());
//        }
//    }

    //
//    @Test
//    public void test_displays() {
//        open('/component.html');
//
//        assertThat(page().displays(
//                new Button('button'),
//                new TextField('text_field'),
//                new Radio('radio')
//        ));
//
//        Panel panel = new Panel('panel');
//        assertThat(panel.displays(
//                new Button('button_in_visible_panel')
//        ));
//
//        try {
//            assertThat(panel.displays(
//                    new Button('button_in_visible_panel'),
//                    new Button('invisible_button_in_visible_panel')
//            ));
//        } catch (AssertionError e) {
//            assertEquals('Component Panel with id: \'panel\' does no displays component Button with id: \'invisible_button_in_visible_panel\'', e.getMessage());
//        }
//    }
//}

}
