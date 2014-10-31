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
import static org.testatoo.core.property.Properties.items
import static org.testatoo.core.property.Properties.title
import static org.testatoo.core.state.States.*
import static starter.property.Properties.*

/**
 * @author davenante
 */
@Ignore // TODO Ignore until FF issue on new driver is fixed.
@RunWith(JUnit4)
class AdvancedGoogleTest {

    @Delegate
    private static Factory factory

    @BeforeClass
    public static void setup() {
        Testatoo.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        evaluator.registerScripts(this.getClass().getResourceAsStream('/starter/custom.js').text)
        open 'http://www.google.com'
        factory = new Factory()
    }

    @AfterClass
    public static void tearDown() { evaluator.close() }

    @Test
    public void simple_test() {
        resultList.should { be missing }
        searchField.should { be visible }

        on searchField enter 'Testatoo'
        clickOn searchButton

        waitUntil { googleResultList.is visible }

        GoogleItem item = googleResultList.items[0];
        item.should {
            have title('Testatoo documentation')
            have url.containing('www.testatoo.org')
            have description.containing('Testatoo is the result of numerous real-world observations of developers')
        }
    }
}
