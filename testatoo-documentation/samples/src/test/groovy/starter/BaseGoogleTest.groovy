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
package starter

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
import static org.testatoo.core.input.Mouse.*
import static org.testatoo.core.property.Properties.*
import static org.testatoo.core.state.States.*
/**
 * @author davenante
 */
@Ignore // TODO Ignore until FF issue on new driver is fixed.
@RunWith(JUnit4)
class BaseGoogleTest {

    @Delegate
    private static Factory factory

    @BeforeClass
    public static void setup() {
        Testatoo.evaluator =  new WebDriverEvaluator(new FirefoxDriver())
        open 'http://www.google.com'
        factory = new Factory()
    }
    @AfterClass public static void tearDown() { evaluator.close() }

    @Test
    public void simple_test() {
        resultList.should { be missing }
        searchField.should { be visible }

        on searchField enter 'Testatoo'
        clickOn searchButton

        waitUntil {resultList.be visible}

        resultList.items[0].should { have value.containing('Testatoo') }
    }

}