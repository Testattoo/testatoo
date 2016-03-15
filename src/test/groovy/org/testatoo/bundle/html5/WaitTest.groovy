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

import org.hamcrest.Matcher
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.WebDriverConfig
import org.testatoo.bundle.html5.Button
import org.testatoo.core.ComponentException
import org.testatoo.core.Testatoo
import org.testatoo.hamcrest.DisabledMatcher
import org.testatoo.hamcrest.EnabledMatcher
import org.testatoo.hamcrest.MissingMatcher
import org.testatoo.hamcrest.VisibleMatcher

import static org.junit.Assert.fail
import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Mouse.clickOn

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class WaitTest {

    @ClassRule
    public static WebDriverConfig driver = new WebDriverConfig()

    @BeforeClass
    public static void before() {
        config.waitUntil = 10.seconds
        browser.open 'http://localhost:8080/wait.html'
    }

    @AfterClass
    public static void tearDown() {
        config.waitUntil = 2.seconds
    }

    @Test
    public void should_be_able_to_wait_on_condition() {
        browser.navigate.refresh()

        Button button = $('#add-message') as Button
        Button message = $('#msg') as Button

        button.should {
            be Enabled
            be Visible
        }

        message.should { be Missing }

        clickOn button
        button.should { be Disabled }
        button.should { be Enabled }

        clickOn button
        button.should { be Enabled }
    }

    @Test
    public void should_throw_exception_when_condition_in_not_reach_in_expected_duration() {
        browser.navigate.refresh()

        Button button = $('#add-message') as Button

        try {
            button.should { be Disabled }
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Unable to reach <Button:add-message> is disabled in 10000 milliseconds'
        }
    }
}

