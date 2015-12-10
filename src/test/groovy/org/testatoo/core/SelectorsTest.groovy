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
import org.testatoo.bundle.html5.components.Button
import org.testatoo.bundle.html5.components.input.InputTypeText
import org.testatoo.core.component.field.TextField
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.junit.Assert.fail
import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.dsl.Actions.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class SelectorsTest {

    @BeforeClass
    public static void setup() {
        config.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        config.scan 'org.testatoo'
        browser.open 'http://localhost:8080/selectors.html'
    }

    @AfterClass
    public static void tearDown() { config.evaluator.close() }

    @Test
    public void should_use_$_as_a_single_selector() {
        Button button = $('#button') as Button

        assert button.enabled
        assert button.visible
    }

    @Test
    public void should_use_$$_as_a_multi_selector() {
        Components<Button> buttons = $$('.btn') of Button
        assert buttons.size() == 4

        Components<TextField> textFields = $$('[type="text"]') of InputTypeText
        assert textFields.size() == 3

        textFields.each {
            assert it.enabled
            assert it.visible
            assert it.empty
        }

        textFields.each { it ->
            fill it with 'TESTATOO!'
        }

        textFields.each {
            assert it.filled
            assert it.value == 'TESTATOO!'
        }
    }

    @Test
    public void should_throw_an_error_on_bad_component_type() {
        try {
            Components<Button> buttons = $$('[type="text"]') of Button
            buttons.each {
                assert it.enabled
            }
        } catch (ComponentException e) {
            assert e.message.contains('Expected a Button')
            assert e.message.contains('but was: InputTypeText')
        }
    }

    @Test
    public void should_have_Components_class_implements_Collection_but_not_support_all_method() {
        assert Components in Collection

        Components<Button> buttons = $$('[type="text"]') of Button

        assert buttons.size() == 3
        assert !buttons.isEmpty()
        assert buttons.iterator() != null
        assert buttons.toArray().length == 3
        assert buttons.toArray(Button).length == 3

        try {
            assert buttons.containsAll(buttons)
            fail()
        } catch (UnsupportedOperationException e) {
        }

        try {
            assert buttons.add(null)
            fail()
        } catch (UnsupportedOperationException e) {
        }

        try {
            assert buttons.remove(null)
            fail()
        } catch (UnsupportedOperationException e) {
        }

        try {
            assert buttons.addAll()
            fail()
        } catch (UnsupportedOperationException e) {
        }

        try {
            assert buttons.removeAll(buttons)
            fail()
        } catch (UnsupportedOperationException e) {
        }

        try {
            assert buttons.retainAll(new ArrayList<Object>())
            fail()
        } catch (UnsupportedOperationException e) {
        }

        try {
            assert buttons.clear()
            fail()
        } catch (UnsupportedOperationException e) {
        }

    }
}
