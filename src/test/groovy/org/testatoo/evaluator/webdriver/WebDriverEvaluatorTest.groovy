/**
 * Copyright (C) 2016 Ovea (dev@ovea.com)
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
package org.testatoo.evaluator.webdriver

import org.junit.Before
import org.junit.BeforeClass
import org.junit.ClassRule
import org.testatoo.WebDriverConfig

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class WebDriverEvaluatorTest {

//        @ClassRule
//    public static WebDriverConfig driver = new WebDriverConfig()
//
//    @BeforeClass
//    public static void before() {
//        browser.open 'http://localhost:8080/mouse.html'
//    }
//
//    @Before
//    public void setup() {
//        browser.navigate.refresh()
//    }
//
//    @Test
//    public void should_be_able_to_click() {
//        Button button = $('#button_1') as Button
//        assert  button.text == 'Button'
//        clickOn button
//        assert button.text == 'Button Clicked!'
//
//        browser.navigate.refresh()
//
//        button = $('#button_1') as Button
//        assert  button.text == 'Button'
//        button.click()
//        assert button.text == 'Button Clicked!'
//
//        CheckBox checkBox = $('#checkbox') as CheckBox
//        assert checkBox.unchecked
//        clickOn checkBox
//        assert checkBox.checked
//
//        Radio radio = $('#radio') as Radio
//        assert radio.unchecked
//        clickOn radio
//        assert radio.checked
//
//        Select dropDown = $('#elements') as Select
//        dropDown.selectedItem.value == 'H'
//
//        clickOn dropDown.items[2]
//        dropDown.selectedItem.value == 'Pol'
//    }
//
//    @Test
//    public void should_be_able_to_doubleClick() {
//        Button button = $('#button_2') as Button
//
//        assert button.text == 'Button'
//        doubleClickOn button
//        assert button.text == 'Button Double Clicked!'
//
//        browser.navigate.refresh()
//
//        button = $('#button_2') as Button
//
//        assert button.text == 'Button'
//        button.doubleClick()
//        assert button.text == 'Button Double Clicked!'
//    }
//
//    @Test
//    public void should_be_able_to_rightClick() {
//        Button button = $('#button_5') as Button
//
//        assert button.text == 'Button'
//        rightClickOn button
//        assert button.text == 'Button Right Clicked!'
//
//        browser.navigate.refresh()
//
//        button = $('#button_5') as Button
//
//        assert button.text == 'Button'
//        button.rightClick()
//        assert button.text == 'Button Right Clicked!'
//    }new HasMathcer
//
//    @Test
//    public void should_be_able_to_mouseOver() {
//        Button button = $('#button_3') as Button
//        assert button.text == 'Button'
//        hoveringMouseOn button
//        assert button.text == 'Button Mouse Over!'
//    }
//
//    @Test
//    public void should_be_able_to_mouseOut() {
//        Button button = $('#button_4') as Button
//        assert button.text == 'Button'
//
//        // To simulate mouse out
//        // 1 - mouse over the component
//        hoveringMouseOn button
//        // 2 - mouse over an another component
//        hoveringMouseOn $('#button_5') as Button
//        // The mouse out is triggered
//        assert button.text == 'Button Mouse Out!'
//    }
//
//    @Test
//    public void should_be_able_to_dragAndDrop() {
//        DropPanel dropPanel = $('#droppable') as DropPanel
//        assert dropPanel.title == 'Drop here'
//
//        Div dragPanel = $('#draggable') as Div
//        drag dragPanel on dropPanel
//        assert dropPanel.title == 'Dropped!'
//
//        browser.navigate.refresh()
//
//        dropPanel = $('#droppable') as DropPanel
//        assert dropPanel.title == 'Drop here'
//
//        dragPanel = $('#draggable') as Div
//        dragPanel.drag().on(dropPanel)
//        assert dropPanel.title == 'Dropped!'
//    }
//
//    @Test
//    public void should_be_able_to_use_mouse_with_key_modifier() {
//        Span span_Ctrl_mouseleft = $('#span_Ctrl_mouseleft') as Span
//        Span span_Shift_mouseleft = $('#span_Shift_mouseleft') as Span
//
//        assert span_Ctrl_mouseleft.missing
//        assert span_Shift_mouseleft.missing
//
//        CTRL.click $('#_Ctrl_mouseleft') as Div
//        SHIFT.click $('#_Shift_mouseleft') as Div
//
//        assert span_Ctrl_mouseleft.available
//        assert span_Shift_mouseleft.available
//
//        // Not testable cause Rightclick Handled by the browser
//        CTRL.rightClick $('#_Ctrl_mouseright') as Div
//        [CTRL, ALT].rightClick $('#_Ctrl_mouseright') as Div
//
//        Span span_Alt_Shift_mouseleft = $('#span_Alt_Shift_mouseleft') as Span
//        assert span_Alt_Shift_mouseleft.missing
//        (ALT + SHIFT).click $('#_Alt_Shift_mouseleft') as Div
//        assert span_Alt_Shift_mouseleft.available
//
//        Span span_Crtl_Shift_mouseleft = $('#span_Crtl_Shift_mouseleft') as Span
//        assert span_Crtl_Shift_mouseleft.missing
//        [CTRL, SHIFT].click $('#_Ctrl_Shift_mouseleft') as Div
//        assert span_Crtl_Shift_mouseleft.missing
//
//        // For code coverage
//        [SPACE].click $('#_Ctrl_Shift_mouseleft') as Div
//        ['data'].click $('#_Ctrl_Shift_mouseleft') as Div
//    }
//
//    class DropPanel extends Div {
//        String getTitle() {
//            config.evaluator.eval(id, "it.find('h1').text()")
//        }
//    }
////
////
//    @ClassRule
//    public static WebDriverConfig driver = new WebDriverConfig()
//
//    @Before
//    public void before() {
//        browser.open 'http://localhost:8080/keyboard.html'
//        // TODO remove when FF issue on new driver is fixed => https://code.google.com/p/selenium/issues/detail?id=7937
//        mouse.clickOn($('#button') as Button)
//        Thread.sleep(500);
//    }
//
//    @Test
//    public void should_type_letters_on_keyboard() {
//        (0..25).each {
//            char letter = (char) (('a' as char) + it)
//            Span current_span = $("#span_$letter") as Span
//
//            assert current_span.missing
//            keyboard.type "$letter"
//            assert current_span.available
//        }
//    }

//    @Test
//    public void should_type_number_on_keyboard() {
//        (0..9).each {
//            Span current_span = $("#span_$it") as Span
//            assert  current_span.missing
//            keyboard.type "$it"
//            assert current_span.available
//        }
//    }
//
//    @Test
//    public void should_type_special_key_on_keyboard() {
//        [
//                '#span_esc'      : ESCAPE,
//                '#span_f1'       : F1,
//                '#span_f2'       : F2,
//                '#span_f3'       : F3,
//                '#span_f4'       : F4,
//                '#span_f5'       : F5,
//                '#span_f6'       : F6,
//                '#span_f7'       : F7,
//                '#span_f8'       : F8,
//                '#span_f9'       : F9,
//                '#span_f10'      : F10,
//                '#span_f11'      : F11,
//                '#span_f12'      : F12,
//                '#span_insert'   : INSERT,
//                '#span_del'      : DELETE,
//                '#span_pageup'   : PAGE_UP,
//                '#span_pagedown' : PAGE_DOWN,
//                '#span_home'     : HOME,
//                '#span_end'      : END,
//                '#span_backspace': BACK_SPACE,
//                '#span_divide'   : DIVIDE,
//                '#span_multiply' : MULTIPLY,
//                '#span_substract': SUBTRACT,
//                '#span_add'      : ADD,
//                '#span_equals'   : EQUALS,
//                '#span_tab'      : TAB,
//                '#span_return'   : RETURN,
//                '#span_space'    : SPACE,
//                '#span_left'     : LEFT,
//                '#span_up'       : UP,
//                '#span_right'    : RIGHT,
//                '#span_down'     : DOWN
//        ].each { k, v ->
//            Span current_span = $(k) as Span
//            assert current_span.missing
//            keyboard.type v
//            assert current_span.available
//        }
//    }
//
//    @Test
//    public void should_use_key_modifier_on_keyboard() {
//        Span span = $('#span_Ctrl_Alt_Shift_x') as Span
//        assert span.missing
//        keyboard.type(CTRL + ALT + SHIFT + 'x')
//        assert span.available
//
//        TextField textField = $('#textfield') as InputTypeText
//
//        assert textField.value == ''
//        mouse.clickOn textField
//        keyboard.type(SHIFT + 'testatoo')
//        assert textField.value == 'TESTATOO'
//
//        textField.clear()
//        assert textField.value == ''
//        keyboard.type('~!@#$%^&*()_+')
//        assert textField.value == '~!@#$%^&*()_+'
//
//        textField.clear()
//        assert textField.value == ''
//        keyboard.type(SHIFT + '`1234567890-=')
//        assert textField.value == '~!@#$%^&*()_+'
//    }

//    @ClassRule
//    public static WebDriverConfig driver = new WebDriverConfig()
//
//    @Test
//    public void should_be_able_to_have_browser_properties_access() {
//        browser.open 'http://localhost:8080/components.html'
//
//        assert browser.title == 'Testatoo Rocks'
//        assert browser.pageSource.contains('<title>Testatoo Rocks</title>')
//        assert browser.url == 'http://localhost:8080/components.html'
//
//        browser.open('http://localhost:8080/keyboard.html')
//        assert browser.url == 'http://localhost:8080/keyboard.html'
//    }
//
//    @Test
//    public void should_be_able_to_navigate() {
//        browser.open 'http://localhost:8080/components.html'
//
//        assert browser.url == 'http://localhost:8080/components.html'
//
//        browser.navigate.to('http://localhost:8080/keyboard.html')
//        assert browser.url == 'http://localhost:8080/keyboard.html'
//
//        browser.navigate.back()
//        assert browser.url == 'http://localhost:8080/components.html'
//
//        browser.navigate.forward()
//        assert browser.url == 'http://localhost:8080/keyboard.html'
//
//        browser.navigate.refresh()
//        assert browser.url == 'http://localhost:8080/keyboard.html'
//    }
//
//    @Test
//    public void should_manage_windows() {
//        browser.open 'http://localhost:8080/components.html'
//
//        assert browser.windows.size() == 1
//        String main_window_id = browser.windows[0].id
//
//        A link = $('#link') as A
//        Form form = $('#dsl-form') as Form
//
//        assert link.available
//        assert form.missing
//
//        clickOn link
//
//        assert browser.windows.size() == 2
//        assert link.available
//        assert form.missing
//
//        browser.switchTo(browser.windows[1])
//        assert link.missing
//        assert form.available
//
//        browser.windows[1].close()
//        assert browser.windows.size() == 1
//        assert browser.windows[0].id == main_window_id
//        assert browser.windows[0].toString() == main_window_id
//    }

//    @ClassRule
//    public static WebDriverConfig driver = new WebDriverConfig()
//
//    @AfterClass
//    public static void after() {
//        config.debug = false
//    }
//
//    @Test
//    public void should_be_able_to_obtain_the_underline_implementation() {
//        try {
//            assert config.evaluator.getImplementation(WebDriver) instanceof WebDriver
//        } finally {
//            config.evaluator.close()
//        }
//    }
//
//    @Test
//    public void should_be_able_to_register_a_script() {
//        try {
//            browser.open 'http://localhost:8080/dsl.html'
//
//            TextField field = $('#firstname') as InputTypeText
//            Div error = $('#firstname_blur') as Div
//
//            field.empty
//            error.hidden
//
//            // Register scripts who
//            // 1 - show the first name_blur message
//            // 2 - set an email in email field
//            config.evaluator.registerScripts("function A_test() { \$('#firstname_blur').show()  }; A_test()")
//            config.evaluator.registerScripts("function B_test() { \$('#firstname').val('Joe') }; B_test()")
//
//            browser.open 'http://localhost:8080/dsl.html'
//
//            field = $('#firstname') as InputTypeText
//            error = $('#firstname_blur') as Div
//
//            assert field.filled
//            assert error.visible
//        } finally {
//            config.evaluator.close()
//        }
//    }
//
//    @Test
//    public void should_be_able_to_activate_logging() {
//        assert !Log.debug
//        config.debug = true
//        assert Log.debug
//    }

    //    @ClassRule
//    public static WebDriverConfig driver = new WebDriverConfig()
//
//    @BeforeClass
//    public static void setup() {
//        config.scan 'org.testatoo.component'
//        browser.open 'http://localhost:8080/components.html'
//    }
//
//    @AfterClass
//    public static void tearDown() { config.evaluator.close() }
//
//    @Test
//    public void should_fail_on_component_without_identifier() {
//        try {
//            assert ($('#button') as UnidentifiedComponent).enabled
//            fail()
//        } catch (e) {
//            assert e instanceof ComponentException
//            assert e.message == "Missing @Identifier annotation on type $UnidentifiedComponent.name" as String
//        }
//    }
//
//    @Test
//    public void should_use_top_level_component_identifier() {
//        assert ($('#button') as CustomButton).enabled
//    }
//
//    @Test
//    public void should_throw_an_error_on_bad_component_definition() {
//        try {
//            assert ($('#radio') as Button).enabled
//            fail()
//        } catch (e) {
//            assert e instanceof ComponentException
//            assert e.message == "Expected a $Button.simpleName for component with id 'radio', but was: $Radio.simpleName" as String
//        }
//    }
//
//    @Test
//    public void should_evaluate_component_equality_on_id() {
//        Radio radio_1 = $('#radio') as Radio
//
//        // The selector select the same component as radio_1
//        Radio radio_2 = $('[type=radio]:checked') as Radio
//        Radio radio_3 = $('#other_radio') as Radio
//
//        assert radio_1 == radio_2
//        assert radio_1 != radio_3
//    }
//
//    @Test
//    public void should_be_able_to_find_nested_component() {
//        Section section = $('#section') as Section
//        Paragraph paragraph = section.find('p:first')[0] as Paragraph
//
//        assert paragraph.text == 'Paragraph 1'
//    }
//
//    @Test
//    public void should_have_the_hashCode_of_the_component_based_on_its_id() {
//        Radio radio_1 = $('#radio') as Radio
//        assert radio_1.hashCode() == radio_1.id.hashCode()
//    }
//
//    @Test
//    public void should_be_able_to_evaluate_state_and_property() {
//        Radio checked_radio = $('[type=radio]:checked') as Radio
//
//        assert checked_radio.checked
//        assert checked_radio.label == 'Radio label checked'
//    }
//
//    @Test
//    public void should_be_able_to_override_property() {
//        Button button = $('#button') as CustomButton
//        assert button.text == 'Override Text'
//    }
//
//    @Test
//    public void should_be_able_to_override_state() {
//        Button button = $('#button') as CustomButton
//        assert button.hidden
//    }
//
//    static class CustomButton extends Button {
//
//        @Override
//        String text() {
//            return "Override Text"
//        }
//
//        @Override
//        boolean isHidden() {
//            return true
//        }
//    }
//
//    static class UnidentifiedComponent extends Component implements WebElement {}

}
