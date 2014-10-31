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
package polymerjs

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Testatoo
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Mouse.clickOn
import static org.testatoo.core.property.Properties.title
import static org.testatoo.core.state.States.getSelected
import static org.testatoo.core.state.States.getUnSelected

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@Ignore // TODO fix why it fail on CI
@RunWith(JUnit4)
class PolymerComponentsTest {

    @BeforeClass
    public static void setup() {
        Testatoo.evaluator =  new WebDriverEvaluator(new FirefoxDriver())
        evaluator.registerScripts(this.getClass().getResourceAsStream('/polymerjs/custom.js').text)
        open 'http://localhost:8080/polymerjs/index.html'
    }

    @AfterClass public static void tearDown() { evaluator.close() }

    @Test
    public void test_tab_panel() {
        TabPanel tab_panel = $('#myTab') as TabPanel

        tab_panel.should { have 3.tabs }
        tab_panel.tabs[0].should { have title('Home') }
        tab_panel.tabs[1].should { have title('Profile') }
        tab_panel.tabs[2].should { have title('Options') }

        clickOn tab_panel.tabs[1]

        tab_panel.tabs[0].should { be unSelected }
        tab_panel.tabs[1].should { be selected }
        tab_panel.tabs[2].should { be unSelected }

        clickOn tab_panel.tabs[2]

        tab_panel.tabs[0].should { be unSelected }
        tab_panel.tabs[1].should { be unSelected }
        tab_panel.tabs[2].should { be selected }
    }
}
