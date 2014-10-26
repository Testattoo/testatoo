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
package angularjs

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Testatoo
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.state.States.*
import static org.testatoo.core.property.Properties.*
import static org.testatoo.core.input.Mouse.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class AngularJSTest {

    @BeforeClass
    public static void setup() {
        Testatoo.evaluator =  new WebDriverEvaluator(new FirefoxDriver())
        open 'http://localhost:8080/angularjs/index.html'
    }

    @AfterClass public static void tearDown() { evaluator.close() }

    @Test
    public void test_navigation_menu() {
        NavigationMenu navigationMenu = $('#menu') as NavigationMenu

        expect {
            navigationMenu.is(visible)
            navigationMenu.has(4.items)
        }

        expect navigationMenu.items[0] has title('Home')
        expect navigationMenu.items[1] has title('Projects')
        expect navigationMenu.items[2] has title('Services')
        expect navigationMenu.items[3] has title('Contact')

        expect navigationMenu.items[0] is unSelected
        expect navigationMenu.items[1] is unSelected
        expect navigationMenu.items[2] is unSelected
        expect navigationMenu.items[3] is unSelected

        clickOn(navigationMenu.items[0])
        expect navigationMenu.items[0] is selected
        expect navigationMenu.items[1] is unSelected
        expect navigationMenu.items[2] is unSelected
        expect navigationMenu.items[3] is unSelected

        clickOn(navigationMenu.items[1])
        expect navigationMenu.items[0] is unSelected
        expect navigationMenu.items[1] is selected
        expect navigationMenu.items[2] is unSelected
        expect navigationMenu.items[3] is unSelected
    }
}
