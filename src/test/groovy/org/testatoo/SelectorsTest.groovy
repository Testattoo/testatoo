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
package org.testatoo

import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Components
import org.testatoo.core.Testatoo
import org.testatoo.core.component.Button
import org.testatoo.core.component.Component
import org.testatoo.core.component.ComponentException
import org.testatoo.core.component.input.TextField
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.junit.Assert.fail
import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.property.Properties.*
import static org.testatoo.core.state.States.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class SelectorsTest {

    @BeforeClass
    public static void setup() {
        Testatoo.evaluator = new WebDriverEvaluator(new FirefoxDriver())
    }

    @Before
    public void before() {
        // Due to script injection in some tests the page must be reloaded at each test
        open 'http://localhost:8080/selectors.html'
    }

    @AfterClass
    public static void tearDown() { evaluator.close() }

    @Test
    public void $_as_a_single_selector() {
        Button button = $('#button') as Button

        button.should { be enabled }
        button.should { be visible }
    }

    @Test
    public void $$_as_a_multi_selector() {
        Components<Button> buttons = $$('.btn') of Button
        assert buttons.size() == 4

        Components<TextField> textFields = $$('[type="text"]') of TextField
        assert textFields.size() == 3

        textFields.each {
            it.should {
                be enabled
                be visible
                be empty
            }
        }

        textFields.each { it ->
            on it enter 'TESTATOO!'
        }

        textFields.each {
            it.should {
                be filled
                have value('TESTATOO!')
            }
        }
    }

    @Test
    public void bad_component_type() {
        CustomComponent customComponent = $('#custom-component') as CustomComponent
        try {
            customComponent.should { be visible }
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Expected a Panel (id=custom-component) but was a CustomComponent (hierarchy: [CustomComponent, Component])'
        }

        evaluator.runScript(this.getClass().getResourceAsStream('/custom.js').text)
        customComponent.should { be visible }
    }

    @Test
    public void bad_component_type_for_custom_tag() {
        CustomTag customTag = $('custom-tag') as CustomTag
        try {
            customTag.should { be visible }
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Expected a CUSTOM-TAG (id=custom-tag) but was a CustomTag (hierarchy: [CustomTag, Component])'
        }

        evaluator.runScript(this.getClass().getResourceAsStream('/custom.js').text)
        customTag.should { be visible }
    }

    @Test
    public void bad_list_of_component_type() {
        try {
            Components<Button> buttons = $$('[type="text"]') of Button
            buttons.each {
                it.should {
                    be enabled
                }
            }
        } catch (ComponentException e) {
            assert e.message.contains('Expected a TextField')
            assert e.message.contains('but was a Button (hierarchy: [Button, Component])')
        }
    }

    @Test
    public void Components_implements_Collection_but_not_support_all_method() {
        assert Components.interfaces.contains(Collection.class)

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
            assert buttons.addAll(null)
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

    private class CustomComponent extends Component {
    }

    private class CustomTag extends Component {
    }

}
