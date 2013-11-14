package org.testatoo

import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.testatoo.config.TestatooJunitRunner
import org.testatoo.config.TestatooModules
import org.testatoo.core.component.Button

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Mouse.clickOn
import static org.testatoo.core.state.States.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(TestatooJunitRunner.class)
@TestatooModules(TestModule)
class DSLTest {

    @BeforeClass
    public static void openTestPage() {
        open('/component.html')
    }

    // TODO do the with

    @Test
    public void test_AND() {
        Button button = $('#button') as Button
//        assertThat button is enabled and button is visible
//        assertThat button.is(enabled) & button.is(visible)
    }

    @Test
    public void test_OR() {
        Button button = $('#button') as Button
//        assertThat button.is(disabled).or(button.is(visible))
//        assertThat button.is(disabled) | button.is(visible)
    }

    @Test
    public void test_wait() {
        Button button = $('#add-message') as Button
        Button message = $('#msg') as Button

//        assertThat button.is(enabled) & button.is(visible)
//        assertThat button.is(enabled).and(button.is(visible))

        assertThat message is missing

        clickOn button

//        assertThat button.is(enabled).or(button.is(visible))
//        assertThat button.is(enabled) | button.is(visible)

//        assertThat button.is(disabled) & button.is(visible)
//        assertThat button.is(disabled).and(button.is(visible))

        //waitUntil button.is(enabled).or(message.is(visible))
        //waitUntil button.is(enabled) | message.is(visible)

        //waitUntil button.is(enabled).and(message.is(visible)), 10.seconds
//        waitUntil button is enabled  & message.is(visible), 10.seconds

    }

    //
//    @Test
//    public void test_contains() {
//
//    }
}
