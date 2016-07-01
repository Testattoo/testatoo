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
package org.testatoo.bundle.html5

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.ClassRule
import org.junit.Test
import org.junit.experimental.categories.Category
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.category.UserAgent
import org.testatoo.WebDriverConfig
import org.testatoo.core.Browser

import static org.junit.Assert.fail
import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Mouse.clickOn

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
@Category(UserAgent.All)
class WaitTest {
    @ClassRule
    public static WebDriverConfig driver = new WebDriverConfig()

    @BeforeClass
    public static void before() {
        config.waitUntil = 10.seconds
        visit 'http://localhost:8080/wait.html'
    }

    @AfterClass
    public static void tearDown() {
        config.waitUntil = 2.seconds
    }

    @Test
    public void should_be_able_to_wait_on_condition() {
        Browser.refresh()

        Button button = $('#add-message') as Button
        Button message = $('#msg') as Button

        button.should {
            be enabled
            be visible
        }

        message.should { be missing }

        clickOn button
        button.should { be disabled }
        button.should { be enabled }

        clickOn button
        button.should { be enabled }
    }

    @Test
    public void should_throw_exception_when_condition_in_not_reach_in_expected_duration() {
        Browser.refresh()

        Button button = $('#add-message') as Button

        try {
            button.should { be disabled }
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('Unable to reach the condition after 10000 milliseconds')
                    .appendText('\nExpected: is disabled')
                    .appendText('\n     but: is enabled');

            assert e.message == description.toString()
        }
    }
}
