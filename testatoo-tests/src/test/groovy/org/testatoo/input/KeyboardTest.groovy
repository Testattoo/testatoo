package org.testatoo.input

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Testatoo
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.$
import static org.testatoo.core.Testatoo.assertThat
import static org.testatoo.core.Testatoo.getEvaluator
import static org.testatoo.core.Testatoo.open
import static org.testatoo.core.input.Key.ADD
import static org.testatoo.core.input.Key.ALT
import static org.testatoo.core.input.Key.BACK_SPACE
import static org.testatoo.core.input.Key.CTRL
import static org.testatoo.core.input.Key.DELETE
import static org.testatoo.core.input.Key.DIVIDE
import static org.testatoo.core.input.Key.DOWN
import static org.testatoo.core.input.Key.END
import static org.testatoo.core.input.Key.EQUALS
import static org.testatoo.core.input.Key.ESCAPE
import static org.testatoo.core.input.Key.F1
import static org.testatoo.core.input.Key.F10
import static org.testatoo.core.input.Key.F11
import static org.testatoo.core.input.Key.F12
import static org.testatoo.core.input.Key.F2
import static org.testatoo.core.input.Key.F3
import static org.testatoo.core.input.Key.F4
import static org.testatoo.core.input.Key.F5
import static org.testatoo.core.input.Key.F6
import static org.testatoo.core.input.Key.F7
import static org.testatoo.core.input.Key.F8
import static org.testatoo.core.input.Key.F9
import static org.testatoo.core.input.Key.HOME
import static org.testatoo.core.input.Key.INSERT
import static org.testatoo.core.input.Key.LEFT
import static org.testatoo.core.input.Key.MULTIPLY
import static org.testatoo.core.input.Key.PAGE_DOWN
import static org.testatoo.core.input.Key.PAGE_UP
import static org.testatoo.core.input.Key.RETURN
import static org.testatoo.core.input.Key.RIGHT
import static org.testatoo.core.input.Key.SHIFT
import static org.testatoo.core.input.Key.SPACE
import static org.testatoo.core.input.Key.SUBTRACT
import static org.testatoo.core.input.Key.TAB
import static org.testatoo.core.input.Key.UP
import static org.testatoo.core.input.Keyboard.type
import static org.testatoo.core.state.States.available
import static org.testatoo.core.state.States.getMissing

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class KeyboardTest {

    @BeforeClass
    public static void setup() {
        Testatoo.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        open('http://localhost:8080/keyboard.html')
    }

    @AfterClass
    public static void tearDown() { evaluator.close() }

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
    public void test_key() {
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
        assertThat $('#span_Ctrl_c') is missing
        assertThat $('#span_Shift_c') is missing
        assertThat $('#span_Alt_c') is missing

        assertThat $('#span_Ctrl_1') is missing
        assertThat $('#span_Alt_1') is missing

        assertThat $('#span_Ctrl_esc') is missing
        assertThat $('#span_Shift_esc') is missing
        assertThat $('#span_Alt_esc') is missing

        type(CTRL + 'c')
        type(SHIFT + 'c')
        type(ALT + 'c')
        type(CTRL + '1')
        type(ALT + '1')
        type(CTRL + ESCAPE)
        type(SHIFT + ESCAPE)
        type(ALT + ESCAPE)

        assertThat $('#span_Ctrl_c') is available
        assertThat $('#span_Shift_c') is available
        assertThat $('#span_Alt_c') is available

        assertThat $('#span_Ctrl_1') is available
        assertThat $('#span_Alt_1') is available

        assertThat $('#span_Ctrl_esc') is available
        assertThat $('#span_Shift_esc') is available
        assertThat $('#span_Alt_esc') is available
    }

}
