/**
 * Copyright © 2016 Ovea (d.avenante@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.core.input

import org.junit.Before
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.WebDriverConfig
import org.testatoo.bundle.html5.Span
import org.testatoo.bundle.html5.input.InputTypeText
import org.testatoo.core.component.field.TextField

import static org.testatoo.WebDriverConfig.BASE_URL
import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Key.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class KeyboardTest {
    @ClassRule
    public static WebDriverConfig driver = new WebDriverConfig()

    @Before
    void before() {
        visit BASE_URL + 'keyboard.html'
    }

    @Test
    void should_type_letters_on_keyboard() {
        (0..25).each {
            char letter = (char) (('a' as char) + it)
            Span current_span = $("#span_$letter") as Span

            assert !current_span.available()
            type "$letter"
            assert current_span.available()
        }
    }

    @Test
    void should_type_number_on_keyboard() {
        (0..9).each {
            Span current_span = $("#span_$it") as Span
            assert !current_span.available()
            type "$it"
            assert current_span.available()
        }
    }

    /**
     * Most of the browsers of the market don't ever propagate all keyboard events for some keys.
     * So in this tests we just test keys that can be really handle by a web page
     * So we excluded :
     *  F1 to F12
     *  ESCAPE, INSERT, DELETE, PAGE_UP, PAGE_DOWN, HOME, END, BACK_SPACE, TAB, LEFT, UP, RIGHT, DOWN
     */
    @Test
    void should_type_special_key_on_keyboard() {
        [
                '#span_divide'   : DIVIDE,
                '#span_multiply' : MULTIPLY,
                '#span_substract': SUBTRACT,
                '#span_add'      : ADD,
                '#span_equals'   : EQUALS,
                '#span_return'   : RETURN,
                '#span_space'    : SPACE
        ].each { k, v ->
            Span current_span = $(k) as Span
            assert !current_span.available()
            type v
            println '-----' + v
            assert current_span.available()
        }

        Span span = $('#span_Ctrl_Alt_Shift_x') as Span
        assert !span.available()
        type(CTRL + ALT + SHIFT + 'x')
        assert span.available()
    }

    @Test
    void should_use_key_modifier_on_keyboard() {
        TextField textField = $('#textfield') as InputTypeText

        assert textField.value() == ''
        clickOn textField
        type(SHIFT + 'testatoo')
        textField.should { have value('TESTATOO') }

        textField.clear()
        textField.should { have value('') }
        type('~!@#$%^&*()_+')
        textField.should { have value('~!@#$%^&*()_+') }

        textField.clear()
        textField.should { have value('') }
        type(SHIFT + '`1234567890-=')
        textField.should { have value('~!@#$%^&*()_+') }
    }
}