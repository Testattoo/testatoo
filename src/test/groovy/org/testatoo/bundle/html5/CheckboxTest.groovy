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
package org.testatoo.bundle.html5

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Testatoo
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator
import org.testatoo.core.state.States

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Mouse.*
import static org.testatoo.core.property.Properties.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class CheckboxTest {

    @BeforeClass
    public static void setup() {
        Testatoo.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        open 'http://localhost:8080/components.html'
    }

    @AfterClass
    public static void tearDown() { Testatoo.evaluator.close() }

    @Test
    public void should_have_expected_behaviours() {
        Checkbox checkbox = $('#checkbox') as Checkbox
        checkbox.should { be States.enabled }
        checkbox.should { be States.visible }

        checkbox.should { be States.unchecked }
        checkbox.should { have label('Check me out') }

        check checkbox
        checkbox.should { be States.checked }

        uncheck checkbox
        checkbox.should { be States.unchecked }

        click_on checkbox
        checkbox.should { be States.checked }

        click_on checkbox
        checkbox.should { be States.unchecked }
    }
}
