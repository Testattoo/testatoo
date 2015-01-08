package org.testatoo

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Testatoo
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.browser
import static org.testatoo.core.Testatoo.getEvaluator
import static org.testatoo.core.Testatoo.open

/**
 * @author David Avenante (d.avenante@gmail.com)
 */

@RunWith(JUnit4)
class BrowserTest {

    @BeforeClass
    public static void setup() {
        Testatoo.evaluator = new WebDriverEvaluator(new FirefoxDriver())
    }

    @AfterClass
    public static void tearDown() { evaluator.close() }

    @Test
    public void test_browser() {
        open 'http://localhost:8080/components.html'

        assert browser.title == 'Testatoo Rocks'
        assert browser.pageSource.contains('<title>Testatoo Rocks</title>')
        assert browser.url == 'http://localhost:8080/components.html'

        browser.open('http://localhost:8080/keyboard.html')
        assert browser.url == 'http://localhost:8080/keyboard.html'
    }

    @Test
    public void test_navigate() {
        open 'http://localhost:8080/components.html'

        assert browser.url == 'http://localhost:8080/components.html'

        browser.navigate.to('http://localhost:8080/keyboard.html')
        assert browser.url == 'http://localhost:8080/keyboard.html'

        browser.navigate.back()
        assert browser.url == 'http://localhost:8080/components.html'

        browser.navigate.forward()
        assert browser.url == 'http://localhost:8080/keyboard.html'

        browser.navigate.refresh()
        assert browser.url == 'http://localhost:8080/keyboard.html'
    }

}
