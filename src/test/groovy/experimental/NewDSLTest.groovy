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
package experimental

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import static Term.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class NewDSLTest {

    @Test
    public void dsl() {
        def button = { new Term() }
        button should be enabled
        button should have label containing 'dsd'

    }

//
//    CheckBox checkBox = $('#checkbox') as CheckBox
//
//    checkBox.should {
//        be enabled
//        be visible
//
//        be unchecked
//        have label('Check me out')
//    }
//
//    checkBox.should {
//        be enabled and be(visible)
//    }
//
//    ListBox listBox = $('#cities') as ListBox
//    listBox.should {
//        have 8.items or have(3.visibleItems)
//    }

//        waitUntil 10.seconds, {
//            button.be(enabled)
//        }

//        on listBox unselect 'New York'
//        on listBox unselect 'Munich'
//
//        on listBox select 'Montreal'
//        on listBox select 'Montpellier'
//
//        reset form
//
//        clickOn reset_button

//        DropDown dropDown = $('#elements') as DropDown
//        CheckBox checkBox = $('#checkbox') as CheckBox

//        dropDown.items.are
//        assertThat {
//            dropDown.items.are enabled
//        }

//        assertThat {
//            [dropDown, checkBox]*.are visible
//        }
//        assertThat([login_email, login_password, login_button].are(visible))

//
//    @BeforeClass
//    public static void setup() {
//        Testatoo.evaluator = new WebDriverEvaluator(new FirefoxDriver())
//        open 'http://localhost:8080/components.html'
//    }
//
//    @AfterClass
//    public static void tearDown() { evaluator.close() }

}
