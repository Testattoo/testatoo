package org.testatoo

import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.testatoo.config.TestatooJunitRunner
import org.testatoo.config.TestatooModules
import org.testatoo.core.component.Button
import org.testatoo.core.component.Component
import org.testatoo.core.component.input.CheckBox
import org.testatoo.core.component.list.DropDown
import org.testatoo.core.component.list.ListBox

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.Testatoo.assertThat
import static org.testatoo.core.Testatoo.assertThat
import static org.testatoo.core.input.Mouse.clickOn
import static org.testatoo.core.property.Properties.label
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

    @Test
    public void test_chaining_assert() {
        CheckBox checkBox = $('#checkbox') as CheckBox
        assertThat checkBox, { Component c ->
            c.is enabled
            c.is visible

            c.is unchecked
            c.has label('Check me out')
        }
    }

    @Test
    public void test_AND() {
        CheckBox checkBox = $('#checkbox') as CheckBox
        assertThat checkBox, { Component c ->
            c.is (enabled) and c.is(visible)
            c.is (enabled) & c.is(visible)
        }
    }

    @Test
    public void test_OR() {
        ListBox listBox = $('#cities') as ListBox

        assertThat listBox, { ListBox c ->
            c.has (8.items) or c.has(3.visibleItems)
            c.has (8.items) | c.has(3.visibleItems)
        }
    }

    @Test
    public void test_ARE() {
        DropDown dropDown = $('#elements') as DropDown

        assertThat dropDown, { DropDown c ->
            c.items.are enabled
        }
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

}
