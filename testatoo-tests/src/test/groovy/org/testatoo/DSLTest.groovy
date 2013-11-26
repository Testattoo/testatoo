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
        open('/component.html')
    }

    @Test
    public void test_chaining_assert() {
        CheckBox checkBox = $('#checkbox') as CheckBox
        assertThat checkBox, { Component c ->
            c.is enabled
            c.is visible

            c.is unchecked
            c.has label('Check me out')
        }
    }

    @Test
    public void test_AND() {
        CheckBox checkBox = $('#checkbox') as CheckBox
        assertThat checkBox, { Component c ->
            c.is (enabled) and c.is(visible)
            c.is (enabled) & c.is(visible)
        }
    }

    @Test
    public void test_OR() {
        ListBox listBox = $('#cities') as ListBox

        assertThat listBox, { ListBox c ->
            c.has (8.items) or c.has(3.visibleItems)
            c.has (8.items) | c.has(3.visibleItems)
        }
    }

    @Test
    public void test_ARE() {
        DropDown dropDown = $('#elements') as DropDown

        assertThat dropDown, { DropDown c ->
            c.items.are enabled
        }
    }


//    @Test
//    public void test_wait() {
//        Button button = $('#add-message') as Button
//        Button message = $('#msg') as Button

//        assertThat button.is(enabled) & button.is(visible)
//        assertThat button.is(enabled).and(button.is(visible))

//        assertThat message is missing

//        clickOn button

//        assertThat button.is(enabled).or(button.is(visible))
//        assertThat button.is(enabled) | button.is(visible)

//        assertThat button.is(disabled) & button.is(visible)
//        assertThat button.is(disabled).and(button.is(visible))

        //waitUntil button.is(enabled).or(message.is(visible))
        //waitUntil button.is(enabled) | message.is(visible)

        //waitUntil button.is(enabled).and(message.is(visible)), 10.seconds
//        waitUntil button is enabled  & message.is(visible), 10.seconds

//    }

}
