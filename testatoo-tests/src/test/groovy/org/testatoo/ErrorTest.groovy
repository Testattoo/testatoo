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
import org.testatoo.core.component.ComponentException
import org.testatoo.core.component.Panel
import org.testatoo.core.component.input.CheckBox
import org.testatoo.core.component.list.DropDown
import org.testatoo.core.component.list.ListBox
import org.testatoo.core.evaluator.DeferredEvaluator
import org.testatoo.core.evaluator.EvaluatorHolder
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.junit.Assert.fail
import static org.testatoo.core.Testatoo.$
import static org.testatoo.core.Testatoo.assertThat
import static org.testatoo.core.Testatoo.open
import static org.testatoo.core.property.Properties.text
import static org.testatoo.core.state.States.getEnabled
import static org.testatoo.core.state.States.getEnabled
import static org.testatoo.core.state.States.getEnabled
import static org.testatoo.core.state.States.getVisible
import static org.testatoo.core.state.States.getVisible

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ErrorTest {

    static WebDriver driver

    @BeforeClass
    public static void before() {
        Testatoo.evaluator = new DeferredEvaluator()

        driver = new FirefoxDriver();
        EvaluatorHolder.register(new WebDriverEvaluator(driver))
        open('http://localhost:8080/component.html')
    }

    @AfterClass
    public static void after() {
        driver.quit()
    }

    @Test
    public void bad_component_type() {

    }

    @Test
    public void not_supported_support() {

    }


    // TODO errors on ...

//    @Test
//    public void test_AND() {
//        CheckBox checkBox = $('#checkbox') as CheckBox
//
//        assertThat {
//            checkBox.is(enabled) and checkBox.is(visible)
//            checkBox.is(enabled) & checkBox.is(visible)
//        }
//    }
//
//    @Test
//    public void test_OR() {
//        ListBox listBox = $('#cities') as ListBox
//
//        assertThat {
//            listBox.has(8.items) or listBox.has(3.visibleItems)
//            listBox.has(8.items) | listBox.has(3.visibleItems)
//        }
//    }
//
//    @Test
//    public void test_ARE() {
//        DropDown dropDown = $('#elements') as DropDown
//
//        assertThat {
//            dropDown.items.are enabled
//        }
//    }
    // try to check component that not support checked state
    // try to submit a form without submit button
    // try to reset a form without reset button

    // cannot select already selected item
    // cannot unselect already unselected item

    // USe evaluator.runScript to add new component type
//    @Test
//    public void custom_component_type() {
////        Custom_1 custom_1 = $('#button') as Custom_1
////        assertThat custom_1 has text('Button')
//
//        Custom_2 custom_2 = $('#button') as Custom_2
//        try {
//            assertThat custom_2 has text('Button')
//            fail()
//        } catch (ComponentException e) {
//            assert e.message == "The Component hierarchy [Custom_2, Panel, Component] doesn't contain the evaluated type Button for component with id button"
//        }
//    }

//    class Custom_1 extends Button {
//        Custom_1() {
//        }
//    }
//
//    class Custom_2 extends Panel {
//        Custom_2() {
//        }
//    }



}
