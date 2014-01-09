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

import org.junit.AfterClass
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
import static org.testatoo.core.input.Keys.*
import org.testatoo.core.property.Title

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Keyboard.pressKey
import static org.testatoo.core.input.Keyboard.type
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
    public static void before() {
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
    public void test_my_abc_on_keyboard_() {
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

        assertThat $('#_backspace') is missing
        assertThat $('#_tab') is missing
        assertThat $('#_enter') is missing
        assertThat $('#_esc') is missing
        assertThat $('#_space') is missing
        assertThat $('#_pageup') is missing
        assertThat $('#_pagedown') is missing
        assertThat $('#_end') is missing
        assertThat $('#_home') is missing
        assertThat $('#_left') is missing
        assertThat $('#_up') is missing
        assertThat $('#_right') is missing
        assertThat $('#_down') is missing
        assertThat $('#_insert') is missing
        assertThat $('#_del') is missing
        assertThat $('#_semicolon') is missing
        assertThat $('#_equals') is missing
        assertThat $('#_multiply') is missing
        assertThat $('#_add') is missing
        assertThat $('#_separator') is missing
        assertThat $('#_substract') is missing
        assertThat $('#_decimal') is missing
        assertThat $('#_divide') is missing
        assertThat $('#_scroll') is missing
        assertThat $('#_capslock') is missing
        assertThat $('#_numlock') is missing
        assertThat $('#_pause') is missing

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

        pressKey(BACK_SPACE)
        pressKey(TAB)
        pressKey(ENTER)
        pressKey(ESCAPE)
        pressKey(SPACE)
        pressKey(PAGE_UP)
        pressKey(PAGE_DOWN)
        pressKey(END)
        pressKey(HOME)
        pressKey(LEFT)
        pressKey(UP)
        pressKey(RIGHT)
        pressKey(DOWN)
        pressKey(INSERT)
        pressKey(DELETE)
        pressKey(SEMICOLON)

        pressKey(EQUALS)
        pressKey(MULTIPLY)
        pressKey(ADD)
        pressKey(SEPARATOR)
        pressKey(SUBTRACT)
        pressKey(DECIMAL)
        pressKey(DIVIDE)

        pressKey(F1)
        pressKey(F2)
        pressKey(F3)
        pressKey(F4)
        pressKey(F5)
        pressKey(F6)
        pressKey(F7)
        pressKey(F8)
        pressKey(F9)
        pressKey(F10)
        pressKey(F11)
        pressKey(F12)

        assertThat $('#_backspace') is available
        assertThat $('#_tab') is available
        assertThat $('#_enter') is available
        assertThat $('#_esc') is available
        assertThat $('#_space') is available
        assertThat $('#_pageup') is available
        assertThat $('#_pagedown') is available
        assertThat $('#_end') is available
        assertThat $('#_home') is available
        assertThat $('#_left') is available
        assertThat $('#_up') is available
        assertThat $('#_right') is available
        assertThat $('#_down') is available
        assertThat $('#_insert') is available
        assertThat $('#_del') is available
        assertThat $('#_semicolon') is available
        assertThat $('#_equals') is available
        assertThat $('#_multiply') is available
        assertThat $('#_add') is available
        assertThat $('#_separator') is available
        assertThat $('#_substract') is available
        assertThat $('#_decimal') is available
        assertThat $('#_divide') is available
        assertThat $('#_scroll') is available
        assertThat $('#_capslock') is available
        assertThat $('#_numlock') is available
        assertThat $('#_pause') is available

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
    }



    @Test
    public void test_key_modifier() {
        //        SHIFT,
//        LEFT_SHIFT,
//        CONTROL,
//        LEFT_CONTROL,
//        ALT,
//        LEFT_ALT,
        //        COMMAND
    }

    @Test
    public void test_mouse_with_key_modifier() {

    }


    class DropPanel extends Panel {
        DropPanel() {
            support Title, { Component c -> c.evaluator.getString("\$('#${id}').find('h1').text()") }
        }
    }
}
