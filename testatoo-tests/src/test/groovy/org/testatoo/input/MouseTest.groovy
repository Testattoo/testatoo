package org.testatoo.input

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Testatoo
import org.testatoo.core.component.Button
import org.testatoo.core.component.Component
import org.testatoo.core.component.Panel
import org.testatoo.core.component.input.CheckBox
import org.testatoo.core.component.input.Radio
import org.testatoo.core.component.list.DropDown
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator
import org.testatoo.core.property.Title

import static org.testatoo.core.Testatoo.$
import static org.testatoo.core.Testatoo.assertThat
import static org.testatoo.core.Testatoo.getEvaluator
import static org.testatoo.core.Testatoo.open
import static org.testatoo.core.input.Keyboard.press
import static org.testatoo.core.input.Keyboard.release
import static org.testatoo.core.input.KeysModifier.ALT
import static org.testatoo.core.input.KeysModifier.CONTROL
import static org.testatoo.core.input.KeysModifier.SHIFT
import static org.testatoo.core.input.Mouse.clickOn
import static org.testatoo.core.input.Mouse.doubleClickOn
import static org.testatoo.core.input.Mouse.drag
import static org.testatoo.core.input.Mouse.mouseOver
import static org.testatoo.core.input.Mouse.rightClickOn
import static org.testatoo.core.property.Properties.selectedItems
import static org.testatoo.core.property.Properties.text
import static org.testatoo.core.property.Properties.title
import static org.testatoo.core.state.States.getAvailable
import static org.testatoo.core.state.States.getChecked
import static org.testatoo.core.state.States.getMissing
import static org.testatoo.core.state.States.getUnchecked

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class MouseTest {

    @BeforeClass
    public static void setup() {
        Testatoo.evaluator =  new WebDriverEvaluator(new FirefoxDriver())
        open('http://localhost:8080/mouse.html')
    }
    @AfterClass public static void tearDown() { evaluator.close() }

    @Test
    public void click() {
        Button button = $('#button_1') as Button
        assertThat button has text('Button')
        assertThat button has text('Button')
        clickOn button
        assertThat button has text('Button Clicked!')

        CheckBox checkBox = $('#checkbox') as CheckBox
        assertThat checkBox is unchecked
        clickOn checkBox
        assertThat checkBox is checked

        Radio radio = $('#radio') as Radio
        assertThat radio is unchecked
        clickOn radio
        assertThat radio is checked

        DropDown dropDown = $('#elements') as DropDown
        assertThat dropDown has selectedItems('Helium')

        clickOn dropDown.items[2]
        assertThat dropDown has selectedItems('Polonium')
    }

    @Test
    public void doubleClick() {
        Button button = $('#button_2') as Button
        assertThat button has text('Button')
        doubleClickOn button
        assertThat button has text('Button Double Clicked!')
    }

    @Test
    public void rightClick() {
        Button button = $('#button_5') as Button
        assertThat button has text('Button')
        rightClickOn button
        assertThat button has text('Button Right Clicked!')
    }

    @Test
    public void mouseOver() {
        Button button = $('#button_3') as Button
        assertThat button has text('Button')
        mouseOver button
        assertThat button has text('Button Mouse Over!')
    }

    @Test
    public void mouseOut() {
        Button button = $('#button_4') as Button
        assertThat button has text('Button')

        // To simulate mouse out

        // 1 - mouse over the component
        mouseOver button
        // 2 - mouse over an another component
        mouseOver $('#button_5') as Button
        // The mouse out is triggered
        assertThat button has text('Button Mouse Out!')
    }

    @Test
    public void dragAndDrop() {
        DropPanel dropPanel = $('#droppable') as DropPanel
        assertThat dropPanel has title('Drop here')

        Panel dragPanel = $('#draggable') as Panel

        drag dragPanel on dropPanel
        assertThat dropPanel has title('Dropped!')
    }

    @Test
    public void test_mouse_with_key_modifier() {

        assertThat $('#span_Ctrl_mouseleft') is missing
        assertThat $('#span_Shift_mouseleft') is missing
        assertThat $('#span_Alt_mouseleft') is missing

        assertThat $('#span_Ctrl_mouseright') is missing
        assertThat $('#span_Shift_mouseright') is missing
        assertThat $('#span_Alt_mouseright') is missing

        press(CONTROL)
        clickOn $('#_Ctrl_mouseleft') as Panel
        release(CONTROL)

        press(SHIFT)
        clickOn $('#_Shift_mouseleft') as Panel
        release(SHIFT)

        press(ALT)
        clickOn $('#_Shift_mouseleft') as Panel
        release(ALT)

        assertThat $('#span_Ctrl_mouseleft') is available
        assertThat $('#span_Shift_mouseleft') is available
        assertThat $('#span_Alt_mouseleft') is available

        // Not testable cause Handled by the browser
        press(CONTROL)
        rightClickOn $('#_Ctrl_mouseright') as Panel
        release(CONTROL)

        press(SHIFT)
        rightClickOn $('#_Shift_mouseright') as Panel
        release(SHIFT)

        press(ALT)
        rightClickOn $('#_Shift_mouseright') as Panel
        release(ALT)

        assertThat $('#span_Ctrl_mouseright') is missing
        assertThat $('#span_Shift_mouseright') is missing
        assertThat $('#span_Alt_mouseright') is missing
    }

    class DropPanel extends Panel {
        DropPanel() {
            support Title, { Component c -> c.evaluator.getString("\$('#${id}').find('h1').text()") }
        }
    }


}
