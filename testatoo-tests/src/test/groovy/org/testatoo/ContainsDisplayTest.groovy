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
import org.testatoo.core.component.Form
import org.testatoo.core.component.Panel
import org.testatoo.core.component.input.EmailField
import org.testatoo.core.component.input.PasswordField
import org.testatoo.core.config.Port
import org.testatoo.core.evaluator.DeferredEvaluator
import org.testatoo.core.evaluator.EvaluatorHolder
import org.testatoo.core.evaluator.SeleniumEvaluator

import java.util.logging.Level
import java.util.logging.Logger

import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ContainsDisplayTest {

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
        open('/container.html')
    }

    @Test
    public void test_contains() {
        Panel panel = $('#panel') as Panel
        Button visible_button = $('#visible_button') as Button
        Button invisible_button = $('#invisible_button') as Button

        assertThat panel contains(
                visible_button,
                invisible_button
        )

        Form form = $('#form') as Form
        EmailField email_field = $('[type=email]') as EmailField
        PasswordField password_field = $('[type=password]') as PasswordField
        Button submit_button = $('[type=submit]') as Button
        Button reset_button = $('[type=reset]') as Button

        assertThat form contains(
                email_field,
                password_field,
                submit_button,
                reset_button
        )

        try {
            assertThat panel contains(
                    submit_button,
                    reset_button
            )
        } catch (AssertionError e) {
            assert e.message == "Component Panel:panel does not contains expected component(s): [Button:$submit_button.id, Button:$reset_button.id]"
        }
    }

    @Test
    public void test_display() {
        Panel panel = $('#panel') as Panel
        Button visible_button = $('#visible_button') as Button
        Button invisible_button = $('#invisible_button') as Button

        assertThat panel displays(
                visible_button
        )

        try {
            assertThat panel displays(
                    visible_button,
                    invisible_button
            )
        } catch (AssertionError e) {
            assert e.message == "Component Button with id invisible_button expected visible but was hidden"
        }
    }

}
