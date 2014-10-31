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

        navigationMenu.should {
            be visible
            have 4.items
        }

        navigationMenu.items[0].should { have title('Home') }
        navigationMenu.items[1].should { have title('Projects') }
        navigationMenu.items[2].should { have title('Services') }
        navigationMenu.items[3].should { have title('Contact') }

        navigationMenu.items[0].should { be unSelected }
        navigationMenu.items[1].should { be unSelected }
        navigationMenu.items[2].should { be unSelected }
        navigationMenu.items[3].should { be unSelected }

        clickOn(navigationMenu.items[0])
        navigationMenu.items[0].should { be selected }
        navigationMenu.items[1].should { be unSelected }
        navigationMenu.items[2].should { be unSelected }
        navigationMenu.items[3].should { be unSelected }

        clickOn(navigationMenu.items[1])
        navigationMenu.items[0].should { be unSelected }
        navigationMenu.items[1].should { be selected }
        navigationMenu.items[2].should { be unSelected }
        navigationMenu.items[3].should { be unSelected }
    }
}
