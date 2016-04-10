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
package org.testatoo.core

import org.junit.BeforeClass
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.WebDriverConfig
import org.testatoo.bundle.html5.Button
import org.testatoo.bundle.html5.input.InputTypeText
import org.testatoo.core.component.field.TextField
import static org.testatoo.core.Testatoo.*
import static Actions.fill

/**
 * @author David Avenante (d.avenante@gmail.com)
 */

@RunWith(JUnit4)
class SelectorTest {

    @ClassRule
    public static WebDriverConfig driver = new WebDriverConfig()

    @BeforeClass
    public static void before() {
        config.scan 'org.testatoo'
        browser.open 'http://localhost:8080/selectors.html'
    }

    @Test
    public void should_use_$_as_a_single_selector() {
        Button button = $('#button') as Button

        assert button.enabled()
        assert button.visible()
    }

    @Test
    public void should_use_$$_as_a_multi_selector() {
        List<Button> buttons = $$('.btn', Button)
        assert buttons.size() == 4

        List<InputTypeText> textFields = $$('[type="text"]', InputTypeText)
        assert textFields.size() == 3

        textFields.each {
            assert it.enabled()
            assert it.visible()
            assert it.empty()
        }

        textFields.each { it ->
            fill it with 'TESTATOO!'
        }

        textFields.each {
            assert it.filled()
            assert it.value() == 'TESTATOO!'
        }
    }

    @Test
    public void should_throw_an_error_on_bad_component_type() {
        try {
            List<Button> buttons = $$('[type="text"]')
            buttons.each {
                assert it.enabled()
            }
        } catch (ComponentException e) {
            assert e.message.contains('Expected a Button')
            assert e.message.contains('but was: InputTypeText')
        }
    }
}
