/**
 * Copyright (C) 2011 Ovea <dev@ovea.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo

import com.thoughtworks.selenium.DefaultSelenium
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.server.RemoteControlConfiguration
import org.openqa.selenium.server.SeleniumServer
import org.testatoo.core.Testatoo
import org.testatoo.core.component.Button
import org.testatoo.core.config.Port
import org.testatoo.core.evaluator.DeferredEvaluator
import org.testatoo.core.evaluator.EvaluatorHolder
import org.testatoo.core.evaluator.SeleniumEvaluator

import java.util.logging.Level
import java.util.logging.Logger

import static org.testatoo.core.Testatoo.assertThat
import static org.testatoo.core.Testatoo.open
import static org.testatoo.core.Testatoo.waitUntil
import static org.testatoo.core.input.Mouse.clickOn
import static org.testatoo.core.state.States.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class WaitTest {

    @BeforeClass
    public static void openTestPage() {
//        Testatoo.configure([
//
//        ])

        Testatoo.evaluator = new DeferredEvaluator()
        int port = Port.findFreePort()

        RemoteControlConfiguration seleniumServerConfiguration = new RemoteControlConfiguration()
        seleniumServerConfiguration.port = port
        seleniumServerConfiguration.singleWindow = true
        seleniumServerConfiguration.avoidProxy = true
        seleniumServerConfiguration.honorSystemProxy = true

        if (!seleniumServerConfiguration.dontTouchLogging()) {
            Logger.getLogger("org.openqa.selenium.server.SeleniumDriverResourceHandler").setLevel(Level.OFF)
            Logger.getLogger("org.openqa.selenium.server.SeleniumServer").setLevel(Level.OFF)
            Logger.getLogger("org.openqa.jetty").setLevel(Level.OFF)
        }
        SeleniumServer seleniumServer = new SeleniumServer(seleniumServerConfiguration)
        seleniumServer.start()

        DefaultSelenium selenium = new DefaultSelenium('localhost', port, '*googlechrome', 'http://localhost:8080')
        selenium.start()

        EvaluatorHolder.register(new SeleniumEvaluator(selenium))
        open('/wait.html')
    }


    @Test
    public void test_wait() {
        Button button = $('#add-message') as Button
        Button message = $('#msg') as Button

        assertThat button, { Button c ->
            c.is(enabled) and c.is(visible)
        }

        assertThat message is missing

        clickOn button

        assertThat button is(disabled)




//        waitUntil button
//
//        } is(enabled).or(message.is(visible))
//
//
//        waitUntil button.is(enabled).and(message.is(visible)), 10.seconds


    }
}



//        assertThat button.is(enabled).or(button.is(visible))
//        assertThat button.is(enabled) | button.is(visible)

//        assertThat button.is(disabled) & button.is(visible)
//        assertThat button.is(disabled).and(button.is(visible))

    //waitUntil button.is(enabled).or(message.is(visible))
    //waitUntil button.is(enabled) | message.is(visible)

    //waitUntil button.is(enabled).and(message.is(visible)), 10.seconds
//        waitUntil button is enabled  & message.is(visible), 10.seconds

//}

