/**
 * Copyright (C) 2013 Ovea (dev@ovea.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.input

import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Testatoo
import org.testatoo.core.component.input.TextField
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Key.*
import static org.testatoo.core.input.Keyboard.type
import static org.testatoo.core.input.Mouse.clickOn
import static org.testatoo.core.property.Properties.text
import static org.testatoo.core.state.States.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@Ignore // Ignore until FF issue on new driver is fixed.
@RunWith(JUnit4)
class KeyboardTest {

    @BeforeClass
    public static void setup() {
        Testatoo.evaluator = new WebDriverEvaluator(new FirefoxDriver())
    }

    @AfterClass
    public static void tearDown() { evaluator.close() }

    @Before
    public void before() {
        open 'http://localhost:8080/keyboard.html'
    }

    @Test
    public void test_letters_on_keyboard_() {
        [
            '#span_a': 'a',
            '#span_b': 'b',
            '#span_c': 'c',
            '#span_d': 'd',
            '#span_e': 'e',
            '#span_f': 'f',
            '#span_g': 'g',
            '#span_h': 'h',
            '#span_i': 'i',
            '#span_j': 'j',
            '#span_k': 'k',
            '#span_l': 'l',
            '#span_m': 'm',
            '#span_n': 'n',
            '#span_o': 'o',
            '#span_p': 'p',
            '#span_q': 'q',
            '#span_r': 'r',
            '#span_s': 's',
            '#span_t': 't',
            '#span_u': 'u',
            '#span_v': 'v',
            '#span_w': 'w',
            '#span_x': 'x',
            '#span_y': 'y',
            '#span_z': 'z'
        ].each { k, v ->
            assertThat $(k) is missing
            type v
            assertThat $(k) is available
        }
    }

    @Test
    public void test_number() {
        [
            '#span_1': '1',
            '#span_2': '2',
            '#span_3': '3',
            '#span_4': '4',
            '#span_5': '5',
            '#span_6': '6',
            '#span_7': '7',
            '#span_8': '8',
            '#span_9': '9',
            '#span_0': '0'
        ].each { k, v ->
            assertThat $(k) is missing
            type v
            assertThat $(k) is available
        }
    }

    @Test
    public void test_special_key() {
        [
            '#span_esc': ESCAPE,
            '#span_f1': F1,
            '#span_f2': F2,
            '#span_f3': F3,
            '#span_f4': F4,
            '#span_f5': F5,
            '#span_f6': F6,
            '#span_f7': F7,
            '#span_f8': F8,
            '#span_f9': F9,
            '#span_f10': F10,
            '#span_f11': F11,
            '#span_f12': F12,
            '#span_insert': INSERT,
            '#span_del': DELETE,
            '#span_pageup': PAGE_UP,
            '#span_pagedown': PAGE_DOWN,
            '#span_home': HOME,
            '#span_end': END,
            '#span_backspace': BACK_SPACE,
            '#span_divide': DIVIDE,
            '#span_multiply': MULTIPLY,
            '#span_substract': SUBTRACT,
            '#span_add': ADD,
            '#span_equals': EQUALS,
            '#span_tab': TAB,
            '#span_return': RETURN,
            '#span_space': SPACE,
            '#span_left': LEFT,
            '#span_up': UP,
            '#span_right': RIGHT,
            '#span_down': DOWN
        ].each { k, v ->
            assertThat $(k) is missing
            type v
            assertThat $(k) is available
        }
    }

    @Test
    public void test_key_modifier() {
        assertThat $('#span_Ctrl_Alt_Shift_x') is missing
        type(CTRL + ALT + SHIFT + 'x')
        assertThat $('#span_Ctrl_Alt_Shift_x') is available

        TextField textField = $('#textfield') as TextField

        assertThat textField has text('')
        clickOn textField
        type(SHIFT + 'testatoo')
        assertThat textField has text('TESTATOO')

        reset textField
        assertThat textField has text('')
        type('~!@#$%^&*()_+')
        assertThat textField has text('~!@#$%^&*()_+')

        reset textField
        assertThat textField has text('')
        type(SHIFT + '`1234567890-=')
        assertThat textField has text('~!@#$%^&*()_+')
    }

}
