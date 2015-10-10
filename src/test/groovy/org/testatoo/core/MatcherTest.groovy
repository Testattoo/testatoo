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
package org.testatoo.core

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.bundle.html5.components.list.Dropdown
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.junit.Assert.fail
import static org.testatoo.core.Testatoo.$
import static org.testatoo.core.Testatoo.getConfig
import static org.testatoo.core.dsl.Actions.visit

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class MatcherTest {

    @BeforeClass
    public static void setup() {
        config.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        visit 'http://localhost:8080/error.html'
    }

    @AfterClass
    public static void tearDown() { config.evaluator.close() }

    @Test
    public void equals_to_matcher_on_list_items() {
        Dropdown dropDown = $('#elements') as Dropdown
        try {
            dropDown.should { have items.equalsTo('Val1') }
            fail()
        } catch (ComponentException e) {
            assert e.message == "Expected Items '[Val1]' but was '[Helium, Boron, Polonium, Calcium, Radium]'"
        }

        try {
            dropDown.should { have items.equalsTo(['Val1', 'Val2']) }
            fail()
        } catch (ComponentException e) {
            assert e.message == "Expected Items '[Val1, Val2]' but was '[Helium, Boron, Polonium, Calcium, Radium]'"
        }
    }

    @Test
    public void equals_to_matcher() {
        Dropdown dropDown = $('#elements') as Dropdown
        try {
            dropDown.items[0].should { have value.equalsTo('Val_1') }
            fail()
        } catch (ComponentException e) {
            assert e.message == "Expected Value 'Val_1' but was 'Helium'"
        }

        try {
            dropDown.items[0].should { have value.equalsTo('Val_1', 'val_2') }
            fail()
        } catch (ComponentException e) {
            assert e.message == "Expected one of Value '[Val_1, val_2]' but was 'Helium'"
        }
    }

    @Test
    public void containing_matcher() {
        Dropdown dropDown = $('#elements') as Dropdown
        try {
            dropDown.should { have items.containing('Val_1') }
            fail()
        } catch (ComponentException e) {
            assert e.message == "Expected Items containing 'Val_1' but was '[Helium, Boron, Polonium, Calcium, Radium]'"
        }

        try {
            dropDown.should { have items.containing('Val_1', 'Val_2') }
            fail()
        } catch (ComponentException e) {
            assert e.message == "Expected one of Items containing '[Val_1, Val_2]' but was '[Helium, Boron, Polonium, Calcium, Radium]'"
        }
    }
}
