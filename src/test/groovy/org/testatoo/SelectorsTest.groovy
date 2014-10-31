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
import org.junit.BeforeClass
import org.junit.Ignore
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
import static org.testatoo.core.state.States.*
import static org.testatoo.core.property.Properties.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class SelectorsTest {

    @BeforeClass
    public static void setup() {
        Testatoo.evaluator = new WebDriverEvaluator(new FirefoxDriver())
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
    @Ignore
    public void $$_as_a_multi_selector() {
        Components<Button> buttons = $$('.btn') of Button
        assert buttons.size() == 4

        Components<TextField> textFields = $$('[type="text"]') of TextField
        assert textFields.size() == 3

//        textFields.should {
//            be enabled
//            be visible
//            be empty
//        }
//        assertThat textFields are enabled
//        assertThat textFields are visible
//        assertThat textFields are empty

        on textFields enter 'TESTATOO!'

//        textFields.should { be filled }
//        assertThat textFields are filled
//
         // TODO Math best syntax
//        all textFields have text('TESTATOO')
        textFields.each {
            it.should { have text('TESTATOO!') }
        }
    }

    @Test
    public void bad_component_type() {
        CustomComponent customComponent = $('#custom_component') as CustomComponent
        try {
            customComponent.should { be visible }
            fail()
        } catch (ComponentException e) {
            assert e.message == "The Component hierarchy [CustomComponent, Component] doesn\'t contain the type Panel for component with id custom_component"
        }

        evaluator.runScript(this.getClass().getResourceAsStream('/custom.js').text)
        customComponent.should { be visible }
    }

    @Test
    @Ignore
    public void bad_list_of_component_type() {
//        try {
//            Components<Button> buttons = $$('[type="text"]') of Button
//            assertThat buttons are enabled
//        } catch (ComponentException e) {
//            assert  e.message.startsWith("The Component hierarchy [Button, Component] doesn't contain the type TextField for component with id")
//        }
    }

    private class CustomComponent extends Component {
    }

}
