/**
 * Copyright (C) 2014 Ovea (dev@ovea.com)
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
package org.testatoo.core.input

import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.bundle.html5.components.Button
import org.testatoo.bundle.html5.components.input.TextField
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Key.*
import static org.testatoo.core.input.Keyboard.*
import static org.testatoo.core.input.Mouse.*
import static org.testatoo.core.property.Properties.*
import static org.testatoo.core.state.States.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class KeyboardTest {

    @BeforeClass
    public static void setup() {
        evaluator = new WebDriverEvaluator(new FirefoxDriver())
    }

    @AfterClass
    public static void tearDown() { evaluator.close() }

    @Before
    public void before() {
        open 'http://localhost:8080/keyboard.html'
        // TODO remove when FF issue on new driver is fixed => https://code.google.com/p/selenium/issues/detail?id=7937
        click_on($('#button') as Button)
        Thread.sleep(500);
    }

    @Test
    public void should_type_letters_on_keyboard() {
        (0..25).each {
            char letter = (char)(('a' as char) + it)
            $("#span_$letter").should { be missing }
            type "$letter"
            $("#span_$letter").should { be available }
        }
    }

    @Test
    public void should_type_number_on_keyboard() {
        (0..9).each {
            $("#span_$it").should { be missing }
            type "$it"
            $("#span_$it").should { be available }
        }
    }

    @Test
    public void should_type_special_key_on_keyboard() {
        [
                '#span_esc'      : ESCAPE,
                '#span_f1'       : F1,
                '#span_f2'       : F2,
                '#span_f3'       : F3,
                '#span_f4'       : F4,
                '#span_f5'       : F5,
                '#span_f6'       : F6,
                '#span_f7'       : F7,
                '#span_f8'       : F8,
                '#span_f9'       : F9,
                '#span_f10'      : F10,
                '#span_f11'      : F11,
                '#span_f12'      : F12,
                '#span_insert'   : INSERT,
                '#span_del'      : DELETE,
                '#span_pageup'   : PAGE_UP,
                '#span_pagedown' : PAGE_DOWN,
                '#span_home'     : HOME,
                '#span_end'      : END,
                '#span_backspace': BACK_SPACE,
                '#span_divide'   : DIVIDE,
                '#span_multiply' : MULTIPLY,
                '#span_substract': SUBTRACT,
                '#span_add'      : ADD,
                '#span_equals'   : EQUALS,
                '#span_tab'      : TAB,
                '#span_return'   : RETURN,
                '#span_space'    : SPACE,
                '#span_left'     : LEFT,
                '#span_up'       : UP,
                '#span_right'    : RIGHT,
                '#span_down'     : DOWN
        ].each { k, v ->
            $(k).should { be missing }
            type v
            $(k).should { be available }
        }
    }

    @Test
    public void should_use_key_modifier_on_keyboard() {
        $('#span_Ctrl_Alt_Shift_x').should { be missing }
        type(CTRL + ALT + SHIFT + 'x')
        $('#span_Ctrl_Alt_Shift_x').should { be available }

        TextField textField = $('#textfield') as TextField

        textField.should { have value('') }
        click_on textField
        type(SHIFT + 'testatoo')
        textField.should { have value('TESTATOO') }

        textField.reset()
        textField.should { have value('') }
        type('~!@#$%^&*()_+')
        textField.should { have value('~!@#$%^&*()_+') }

        textField.reset()
        textField.should { have value('') }
        type(SHIFT + '`1234567890-=')
        textField.should { have value('~!@#$%^&*()_+') }
    }

}
