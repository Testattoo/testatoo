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
        Testatoo.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        evaluator.registerScripts(this.getClass().getResourceAsStream('/bootstrap/custom.js').text)
        open 'http://localhost:8080/bootstrap/index.html'
    }

    @AfterClass
    public static void tearDown() { evaluator.close() }

    @Test
    public void test_progress_bar() {
        ProgressBar progress_bar = $('#progress_bar') as ProgressBar
        Button plus = $('#plus') as Button
        Button minus = $('#minus') as Button

        progress_bar.should { have value('60%') }

        clickOn plus
        clickOn plus
        progress_bar.should { have value('80%') }

        clickOn minus
        clickOn minus
        clickOn minus
        clickOn minus
        progress_bar.should { have value('40%') }
    }

    @Test
    public void test_tab_panel() {
        TabPanel tab_panel = $('#myTab') as TabPanel

        tab_panel.should { have 2.tabs }
        tab_panel.tabs[0].should { have title('Home') }
        tab_panel.tabs[1].should { have title('Profile') }

        tab_panel.tabs[0].panel.should { be visible }
        tab_panel.tabs[1].panel.should { be hidden }

        clickOn tab_panel.tabs[1]

        waitUntil { tab_panel.tabs[0].panel.be(hidden) }
        tab_panel.tabs[1].panel.should { be visible }

        tab_panel.tabs[0].should { be unSelected }
        tab_panel.tabs[1].should { be selected }

        clickOn tab_panel.tabs[0]

        tab_panel.tabs[0].should { be selected }
        tab_panel.tabs[1].should { be unSelected }
    }

    @Test
    public void test_accordion() {
        Accordion accordion = $('#accordion') as Accordion
        accordion.should { have 3.items }

        accordion.items[0].should { have title('Item 1') }
        accordion.items[1].should { have title('Item 2') }
        accordion.items[2].should { have title('Item 3') }

        accordion.items[0].panel.should { be visible }
        accordion.items[1].panel.should { be hidden }
        accordion.items[2].panel.should { be hidden }

        accordion.items[0].should { be selected }
        accordion.items[1].should { be unSelected }
        accordion.items[2].should { be unSelected }

        clickOn accordion.items[1]
        waitUntil { accordion.items[1].be(selected) }
        accordion.items[0].should { be unSelected }
        accordion.items[2].should { be unSelected }

        clickOn accordion.items[2]
        waitUntil { accordion.items[2].is(selected) }
        accordion.items[0].should { be unSelected }
        accordion.items[1].should { be unSelected }
    }
}
