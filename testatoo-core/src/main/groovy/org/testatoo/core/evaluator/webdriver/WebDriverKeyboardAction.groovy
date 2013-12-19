package org.testatoo.core.evaluator.webdriver

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.testatoo.core.evaluator.KeyboardAction

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class WebDriverKeyboardAction implements KeyboardAction {

    private final WebDriver webDriver

    WebDriverKeyboardAction(WebDriver webDriver) {
        this.webDriver = webDriver
    }

    @Override
    void enter(String id, String data) {
        webDriver.findElement(By.id(id)).sendKeys(data)
    }
}
