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
import org.testatoo.core.component.Component
import org.testatoo.core.component.Panel
import org.testatoo.core.component.input.CheckBox
import org.testatoo.core.component.input.Radio
import org.testatoo.core.config.Port
import org.testatoo.core.evaluator.DeferredEvaluator
import org.testatoo.core.evaluator.EvaluatorHolder
import org.testatoo.core.evaluator.SeleniumEvaluator
import org.testatoo.core.property.Title

import java.util.logging.Level
import java.util.logging.Logger

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Mouse.*
import static org.testatoo.core.property.Properties.text
import static org.testatoo.core.property.Properties.title
import static org.testatoo.core.state.States.getChecked
import static org.testatoo.core.state.States.getUnchecked

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class MouseTest {

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
        open('/mouse.html')
    }

    @Test
    public void click() {
        Button button = $('#button_1') as Button
        assertThat button has text('Button')
        assertThat button has text('Button')
        clickOn button
        assertThat button has text('Button Clicked!')

        CheckBox checkBox = $('#checkbox') as CheckBox
        assertThat checkBox is unchecked
        clickOn checkBox
        assertThat checkBox is checked

        Radio radio = $('#radio') as Radio
        assertThat radio is unchecked
        clickOn radio
        assertThat radio is checked

        // test click on dropdown items
    }

    @Test
    public void doubleClick() {
        Button button = $('#button_2') as Button
        assertThat button has text('Button')
        doubleClickOn button
        assertThat button has text('Button Double Clicked!')
    }

    @Test
    public void rightClick() {
        Button button = $('#button_5') as Button
        assertThat button has text('Button')
        rightClickOn button
        assertThat button has text('Button Right Clicked!')
    }

    @Test
    public void mouseOver() {
        Button button = $('#button_3') as Button
        assertThat button has text('Button')
        mouseOverOn button
        assertThat button has text('Button Mouse Over!')
    }

    @Test
    public void mouseOut() {
        Button button = $('#button_4') as Button
        assertThat button has text('Button')
        mouseOutOff button
        assertThat button has text('Button Mouse Out!')
    }

    @Test
    public void dragAndDrop() {
        DropPanel dropPanel = $('#droppable') as DropPanel
        assertThat dropPanel has title('Drop here')

        Panel dragPanel =  $('#draggable') as Panel

        drag dragPanel on dropPanel
        assertThat dropPanel has title('Dropped!')
    }

    class DropPanel extends Panel  {
        DropPanel() {
            support Title, { Component c -> c.evaluator.getString("testatoo.ext.getText('${c.id} h1')") }
        }
    }
}
