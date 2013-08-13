package org.testatoo

import experimental.dsl.TestModule
import org.junit.Test
import org.junit.runner.RunWith
import org.testatoo.config.TestatooJunitRunner
import org.testatoo.config.TestatooModules
import org.testatoo.core.Testatoo
import org.testatoo.core.component.Button
import org.testatoo.core.component.CheckBox
import org.testatoo.core.component.Link
import org.testatoo.core.component.Panel
import org.testatoo.core.component.PasswordField
import org.testatoo.core.component.TextField

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(TestatooJunitRunner.class)
@TestatooModules(TestModule)
class ComponentTest extends Testatoo {

    @Test
    public void test_button() {
        open('/index.html')

        // input type=button
        Button button = $('#button') as Button
        assert button.is(enabled)
        assert button.is(visible)

        assertThat { button.has text.equalsTo('Button') }

        // input type=submit
        button = $('#submit') as Button
        assert button.is(disabled)
        assert button.is(visible)

        assert button.has(text.equalsTo('Submit'))

        // input type=reset
        button = $('#reset') as Button
        assert button.is(enabled)
        assert button.is(visible)

        assert button.has(text.equalsTo('Reset'))

        // button element
        button = $('#btn') as Button
        assert button.is(enabled)
        assert button.is(visible)

        assert button.has(text.equalsTo('My Button Text'))
    }

    @Test
    public void test_textField() {
        open('/index.html');

        TextField textField = $('#text_field') as TextField

        assert textField.is(disabled)
        assert textField.is(visible)

        assert textField.has(label.equalsTo('Email'))
//        assert textField.has(value.equalsTo(''))
    }

    @Test
    public void test_passwordField() {
        open('/index.html')

        PasswordField passwordField = $('#password_field') as PasswordField

        assert passwordField.is(enabled)
        assert passwordField.is(visible)

        assert passwordField.has(label.equalsTo('Password'))
//        assert passwordField.has(value.equalsTo('?art'))
    }


    @Test
    public void test_checkbox() {
        open('/index.html')

        CheckBox checkBox = $('#checkbox') as CheckBox
        assert checkBox.is(enabled)
        assert checkBox.is(visible)

        assert checkBox.is(unchecked)
        assert checkBox.has(label.containing('Check me out'))
    }

    @Test
    public void test_link() {
        open('/index.html')

        Link link = $('#link') as Link
        assert link.is(enabled)
        assert link.is(visible)

        assert link.has(text.equalsTo('Link to index'))
        assert link.has(reference.containing('/index.html'))
    }

    @Test
    public void test_panel() {
        open('/index.html')

        Panel panel = $('#panel') as Panel
        assert panel.is(enabled)
        assert panel.is(visible)

        assert panel.has(title.equalsTo(''))
    }


//
//    @Test
//    public void test_radio() {
//        open('/index.html');
//
//        Radio radio = new Radio('radio');
//        assertThat(radio.is(enabled()));
//        assertThat(radio.is(visible()));
//        assertThat(radio.is(checked()));
//
//        assertThat(radio.has(label()).contains('Radio label'));
//    }
//
//
//
//    @Test
//    public void test_page() {
//        open('/index.html');
//
//        assertThat(page().has(title()).equalsTo('Testatoo Rocks'));
//    }
//
//    @Test
//    public void test_contains() {
//        open('/index.html');
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
//        open('/index.html');
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

//    @Test
//    public void componentType() {
//
//    }

}
