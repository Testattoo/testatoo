/**
 * Copyright (C) 2013 Ovea (dev@ovea.com)
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
package bootstrap

import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Testatoo
import org.testatoo.core.component.Button
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Mouse.*
import static org.testatoo.core.property.Properties.*
import static org.testatoo.core.state.States.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class BootstrapComponentsTest {

    @BeforeClass
    public static void setup() {
        Testatoo.evaluator =  new WebDriverEvaluator(new FirefoxDriver())
        evaluator.registerScripts(this.getClass().getResourceAsStream('/bootstrap/custom.js').text)
        open 'http://localhost:8080/bootstrap/index.html'
    }

    @AfterClass public static void tearDown() { evaluator.close() }

    @Test
    public void test_progress_bar() {
        ProgressBar progress_bar = $('#progress_bar') as ProgressBar
        Button plus = $('#plus') as Button
        Button minus = $('#minus') as Button

        expect progress_bar has value('60%')

        clickOn plus
        clickOn plus
        expect progress_bar has value('80%')

        clickOn minus
        clickOn minus
        clickOn minus
        clickOn minus
        expect progress_bar has value('40%')
    }

    @Test
    public void test_tab_panel() {
        TabPanel tab_panel = $('#myTab') as TabPanel

        expect tab_panel has 2.tabs
        expect tab_panel.tabs[0] has title('Home')
        expect tab_panel.tabs[1] has title('Profile')

        expect tab_panel.tabs[0].panel is visible
        expect tab_panel.tabs[1].panel is hidden

        clickOn tab_panel.tabs[1]

        waitUntil { tab_panel.tabs[0].panel.is(hidden) }
        expect tab_panel.tabs[1].panel is visible

        expect tab_panel.tabs[0] is unSelected
        expect tab_panel.tabs[1] is selected

        clickOn tab_panel.tabs[0]

        expect tab_panel.tabs[0] is selected
        expect tab_panel.tabs[1] is unSelected
    }

    @Test
    public void test_accordion() {
        Accordion accordion = $('#accordion') as Accordion
        expect accordion has 3.items

        expect accordion.items[0] has title('Item 1')
        expect accordion.items[1] has title('Item 2')
        expect accordion.items[2] has title('Item 3')

        expect accordion.items[0].panel is visible
        expect accordion.items[1].panel is hidden
        expect accordion.items[2].panel is hidden

        expect accordion.items[0] is selected
        expect accordion.items[1] is unSelected
        expect accordion.items[2] is unSelected

        clickOn accordion.items[1]
        waitUntil { accordion.items[1].is(selected) }
        expect accordion.items[0] is unSelected
        expect accordion.items[2] is unSelected

        clickOn accordion.items[2]
        waitUntil { accordion.items[2].is(selected) }
        expect accordion.items[0] is unSelected
        expect accordion.items[1] is unSelected
    }
}
