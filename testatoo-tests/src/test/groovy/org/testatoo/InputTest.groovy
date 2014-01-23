/**
 * Copyright (C) 2011 Ovea <dev@ovea.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo

import org.junit.After
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Testatoo
import org.testatoo.core.component.Button
import org.testatoo.core.component.Component
import org.testatoo.core.component.Panel
import org.testatoo.core.component.input.CheckBox
import org.testatoo.core.component.input.Radio
import org.testatoo.core.component.list.DropDown
import org.testatoo.core.evaluator.DeferredEvaluator
import org.testatoo.core.evaluator.EvaluatorHolder
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.assertThat
import static org.testatoo.core.input.Keys.*
import static org.testatoo.core.input.KeysModifier.*
import org.testatoo.core.property.Title

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Keyboard.*
import static org.testatoo.core.input.Mouse.*
import static org.testatoo.core.property.Properties.*
import static org.testatoo.core.state.States.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class InputTest {

    static WebDriver driver

    @BeforeClass
    public static  void before() {
        Testatoo.evaluator = new DeferredEvaluator()

        driver = new FirefoxDriver();
        EvaluatorHolder.register(new WebDriverEvaluator(driver))
        open('http://localhost:8080/input.html')
    }

    @AfterClass
    public static void after() {
        driver.quit()
    }

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
    public void test_letters_on_keyboard_() {
        assertThat $('#span_a') is missing
        assertThat $('#span_b') is missing
        assertThat $('#span_c') is missing
        assertThat $('#span_d') is missing
        assertThat $('#span_e') is missing
        assertThat $('#span_f') is missing
        assertThat $('#span_g') is missing
        assertThat $('#span_h') is missing
        assertThat $('#span_i') is missing
        assertThat $('#span_j') is missing
        assertThat $('#span_k') is missing
        assertThat $('#span_l') is missing
        assertThat $('#span_m') is missing
        assertThat $('#span_n') is missing
        assertThat $('#span_o') is missing
        assertThat $('#span_p') is missing
        assertThat $('#span_q') is missing
        assertThat $('#span_r') is missing
        assertThat $('#span_s') is missing
        assertThat $('#span_t') is missing
        assertThat $('#span_u') is missing
        assertThat $('#span_v') is missing
        assertThat $('#span_w') is missing
        assertThat $('#span_x') is missing
        assertThat $('#span_y') is missing
        assertThat $('#span_z') is missing

        type('a')
        type('b')
        type('c')
        type('d')
        type('e')
        type('f')
        type('g')
        type('h')
        type('i')
        type('j')
        type('k')
        type('l')
        type('m')
        type('n')
        type('o')
        type('p')
        type('q')
        type('r')
        type('s')
        type('t')
        type('u')
        type('v')
        type('w')
        type('x')
        type('y')
        type('z')

        assertThat $('#span_a') is available
        assertThat $('#span_b') is available
        assertThat $('#span_c') is available
        assertThat $('#span_d') is available
        assertThat $('#span_e') is available
        assertThat $('#span_f') is available
        assertThat $('#span_g') is available
        assertThat $('#span_h') is available
        assertThat $('#span_i') is available
        assertThat $('#span_j') is available
        assertThat $('#span_k') is available
        assertThat $('#span_l') is available
        assertThat $('#span_m') is available
        assertThat $('#span_n') is available
        assertThat $('#span_o') is available
        assertThat $('#span_p') is available
        assertThat $('#span_q') is available
        assertThat $('#span_r') is available
        assertThat $('#span_s') is available
        assertThat $('#span_t') is available
        assertThat $('#span_u') is available
        assertThat $('#span_v') is available
        assertThat $('#span_w') is available
        assertThat $('#span_x') is available
        assertThat $('#span_y') is available
        assertThat $('#span_z') is available
    }

    @Test
    public void test_number() {
        assertThat $('#span_1') is missing
        assertThat $('#span_2') is missing
        assertThat $('#span_3') is missing
        assertThat $('#span_4') is missing
        assertThat $('#span_5') is missing
        assertThat $('#span_6') is missing
        assertThat $('#span_7') is missing
        assertThat $('#span_8') is missing
        assertThat $('#span_9') is missing
        assertThat $('#span_0') is missing

        type('1')
        type('2')
        type('3')
        type('4')
        type('5')
        type('6')
        type('7')
        type('8')
        type('9')
        type('0')

        assertThat $('#span_1') is available
        assertThat $('#span_2') is available
        assertThat $('#span_3') is available
        assertThat $('#span_4') is available
        assertThat $('#span_5') is available
        assertThat $('#span_6') is available
        assertThat $('#span_7') is available
        assertThat $('#span_8') is available
        assertThat $('#span_9') is available
        assertThat $('#span_0') is available
    }

    @Test
    public void test_type_on_focused_input() {
    }

    @Test
    public void test_key() {
        assertThat $('#span_esc') is missing

        assertThat $('#span_f1') is missing
        assertThat $('#span_f2') is missing
        assertThat $('#span_f3') is missing
        assertThat $('#span_f4') is missing
        assertThat $('#span_f5') is missing
        assertThat $('#span_f6') is missing
        assertThat $('#span_f7') is missing
        assertThat $('#span_f8') is missing
        assertThat $('#span_f9') is missing
        assertThat $('#span_f10') is missing
        assertThat $('#span_f11') is missing
        assertThat $('#span_f12') is missing

        assertThat $('#span_insert') is missing
        assertThat $('#span_del') is missing
        assertThat $('#span_pageup') is missing
        assertThat $('#span_pagedown') is missing

        assertThat $('#span_home') is missing
        assertThat $('#span_end') is missing
        assertThat $('#span_backspace') is missing

        assertThat $('#span_divide') is missing
        assertThat $('#span_multiply') is missing
        assertThat $('#span_substract') is missing
        assertThat $('#span_add') is missing
        assertThat $('#span_equals') is missing

        assertThat $('#span_tab') is missing
        assertThat $('#span_return') is missing
        assertThat $('#span_space') is missing

        assertThat $('#span_left') is missing
        assertThat $('#span_up') is missing
        assertThat $('#span_right') is missing
        assertThat $('#span_down') is missing

        type(ESCAPE)

        type(F1)
        type(F2)
        type(F3)
        type(F4)
        type(F5)
        type(F6)
        type(F7)
        type(F8)
        type(F9)
        type(F10)
        type(F11)
        type(F12)

        type(INSERT)
        type(DELETE)
        type(PAGE_UP)
        type(PAGE_DOWN)

        type(HOME)
        type(END)
        type(BACK_SPACE)

        type(MULTIPLY)
        type(DIVIDE)
        type(SUBTRACT)
        type(ADD)
        type(EQUALS)

        type(TAB)
        type(RETURN)
        type(SPACE)

        type(LEFT)
        type(UP)
        type(RIGHT)
        type(DOWN)

        assertThat $('#span_esc') is available

        assertThat $('#span_f1') is available
        assertThat $('#span_f2') is available
        assertThat $('#span_f3') is available
        assertThat $('#span_f4') is available
        assertThat $('#span_f5') is available
        assertThat $('#span_f6') is available
        assertThat $('#span_f7') is available
        assertThat $('#span_f8') is available
        assertThat $('#span_f9') is available
        assertThat $('#span_f10') is available
        assertThat $('#span_f11') is available
        assertThat $('#span_f12') is available

        assertThat $('#span_insert') is available
        assertThat $('#span_del') is available
        assertThat $('#span_pageup') is available
        assertThat $('#span_pagedown') is available

        assertThat $('#span_home') is available
        assertThat $('#span_end') is available
        assertThat $('#span_backspace') is available

        assertThat $('#span_divide') is available
        assertThat $('#span_multiply') is available
        assertThat $('#span_substract') is available
        assertThat $('#span_add') is available
        assertThat $('#span_equals') is available

        assertThat $('#span_tab') is available
        assertThat $('#span_return') is available
        assertThat $('#span_space') is available

        assertThat $('#span_left') is available
        assertThat $('#span_up') is available
        assertThat $('#span_right') is available
        assertThat $('#span_down') is available
    }

    @Test
    public void test_key_modifier() {
        assertThat $('#span_Ctrl_c') is missing
        assertThat $('#span_Shift_c') is missing
        assertThat $('#span_Alt_c') is missing

        assertThat $('#span_Ctrl_1') is missing
        assertThat $('#span_Alt_1') is missing

        assertThat $('#span_Ctrl_esc') is missing
        assertThat $('#span_Shift_esc') is missing
        assertThat $('#span_Alt_esc') is missing

        press(CONTROL)
        type('c')
        release(CONTROL)

        press(SHIFT)
        type('c')
        release(SHIFT)

        press(ALT)
        type('c')
        release(ALT)

        press(CONTROL)
        type('1')
        release(CONTROL)

        press(ALT)
        type('1')
        release(ALT)

        press(CONTROL)
        type(ESCAPE)
        release(CONTROL)

        press(SHIFT)
        type(ESCAPE)
        release(SHIFT)

        press(ALT)
        type(ESCAPE)
        release(ALT)

        assertThat $('#span_Ctrl_c') is available
        assertThat $('#span_Shift_c') is available
        assertThat $('#span_Alt_c') is available

        assertThat $('#span_Ctrl_1') is available
        assertThat $('#span_Alt_1') is available

        assertThat $('#span_Ctrl_esc') is available
        assertThat $('#span_Shift_esc') is available
        assertThat $('#span_Alt_esc') is available
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
