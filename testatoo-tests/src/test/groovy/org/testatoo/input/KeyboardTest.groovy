package org.testatoo.input

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Testatoo
import org.testatoo.core.evaluator.KeyboardAction
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Keyboard.*
import static org.testatoo.core.input.Keys.*
import static org.testatoo.core.input.KeysModifier.*
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
    public void release_all_keys_pressed() {
        KeyboardAction keyboard = Testatoo.evaluator.keyboard()

        assert keyboard.keysPressed().size().equals(0)

        press(CONTROL)
        press(ALT)

        assert keyboard.keysPressed().size().equals(2)
        assert keyboard.keysPressed().contains(CONTROL)
        assert keyboard.keysPressed().contains(ALT)

        keyboard.releaseAll()
        assert keyboard.keysPressed().size().equals(0)
    }
}
