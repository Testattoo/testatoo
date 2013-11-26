package org.testatoo

import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.testatoo.config.TestatooJunitRunner
import org.testatoo.config.TestatooModules
import org.testatoo.core.component.Button
import org.testatoo.core.component.ComponentException
import org.testatoo.core.component.Form
import org.testatoo.core.component.Panel
import org.testatoo.core.component.input.EmailField
import org.testatoo.core.component.input.PasswordField

import static org.testatoo.core.Testatoo.$
import static org.testatoo.core.Testatoo.assertThat
import static org.testatoo.core.Testatoo.open
import static org.testatoo.core.state.States.getEnabled

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(TestatooJunitRunner.class)
@TestatooModules(TestModule)
class ContainsDisplayTest {

    @BeforeClass
    public static void openTestPage() {
        open('/container.html')
    }

    @Test
    public void test_contains() {
        Panel panel = $('#panel') as Panel
        Button visible_button = $('#visible_button') as Button
        Button invisible_button = $('#invisible_button') as Button

        assertThat panel contains(
                visible_button,
                invisible_button
        )

        Form form = $('#form') as Form
        EmailField email_field = $('[type=email]') as EmailField
        PasswordField password_field = $('[type=password]') as PasswordField
        Button submit_button = $('[type=submit]') as Button
        Button reset_button = $('[type=reset]') as Button

        assertThat form contains(
                email_field,
                password_field,
                submit_button,
                reset_button
        )

        try {
            assertThat panel contains(
                submit_button,
                reset_button
            )
        } catch (AssertionError e) {
            assert e.message == "Component Panel:panel does not contains expected component(s): [Button:$submit_button.id, Button:$reset_button.id]"
        }
    }

    @Test
    public void test_display() {
        Panel panel = $('#panel') as Panel
        Button visible_button = $('#visible_button') as Button
        Button invisible_button = $('#invisible_button') as Button

        assertThat panel displays(
                visible_button
        )

        try {
            assertThat panel displays(
                    visible_button,
                    invisible_button
            )
        } catch (AssertionError e) {
            assert e.message == "Component Button with id invisible_button expected visible but was hidden"
        }
    }

}
