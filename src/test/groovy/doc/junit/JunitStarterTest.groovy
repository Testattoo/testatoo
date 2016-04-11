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
package doc.junit

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.bundle.html5.input.InputTypeText
import org.testatoo.core.component.field.TextField
import org.testatoo.evaluator.webdriver.WebDriverEvaluator
import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.Actions.*

@RunWith(JUnit4)
class JunitStarterTest {

    @BeforeClass
    public static void setup() {
        config.evaluator = new WebDriverEvaluator(new FirefoxDriver()) // <1>
        visit 'http://www.google.com' // <2>
    }

    @Test
    public void google_search_field_should_be_visible() {
        // Write you test here
        TextField search = $('#lst-ib') as InputTypeText    // <3>
        search.should { be visible }
    }

    @AfterClass
    public static void tearDown() {
        config.evaluator.close() // <4>
    }
}
