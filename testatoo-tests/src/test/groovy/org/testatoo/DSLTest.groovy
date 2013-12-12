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
import org.testatoo.core.component.Component
import org.testatoo.core.component.input.CheckBox
import org.testatoo.core.component.list.DropDown
import org.testatoo.core.component.list.ListBox
import org.testatoo.core.config.Port
import org.testatoo.core.evaluator.DeferredEvaluator
import org.testatoo.core.evaluator.EvaluatorHolder
import org.testatoo.core.evaluator.SeleniumEvaluator

import java.util.logging.Level
import java.util.logging.Logger

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.property.Properties.label
import static org.testatoo.core.state.States.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class DSLTest {

    @BeforeClass
    public static void before() {
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
        open('/component.html')
    }

    @Test
    public void test_chaining_assert() {
        CheckBox checkBox = $('#checkbox') as CheckBox
        assertThat checkBox, { CheckBox c ->
            c.is enabled
            c.is visible

            c.is unchecked
            c.has label('Check me out')
        }

        assertThat {
            checkBox.is enabled
            checkBox.is visible

            checkBox.is unchecked
            checkBox.has label('Check me out')
        }
    }

    @Test
    public void test_AND() {
        CheckBox checkBox = $('#checkbox') as CheckBox
        assertThat checkBox, { CheckBox c ->
            c.is (enabled) and c.is(visible)
            c.is (enabled) & c.is(visible)
        }

        assertThat {
            checkBox.is(enabled) and checkBox.is(visible)
            checkBox.is(enabled) & checkBox.is(visible)
        }
    }

    @Test
    public void test_OR() {
        ListBox listBox = $('#cities') as ListBox

        assertThat listBox, { ListBox c ->
            c.has(8.items) or c.has(3.visibleItems)
            c.has(8.items) | c.has(3.visibleItems)
        }

        assertThat {
            listBox.has(8.items) or listBox.has(3.visibleItems)
            listBox.has(8.items) | listBox.has(3.visibleItems)
        }
    }

    @Test
    public void test_ARE() {
        DropDown dropDown = $('#elements') as DropDown

        assertThat dropDown, { DropDown c ->
            c.items.are enabled
        }

        assertThat {
            dropDown.items.are enabled
        }
    }
}
