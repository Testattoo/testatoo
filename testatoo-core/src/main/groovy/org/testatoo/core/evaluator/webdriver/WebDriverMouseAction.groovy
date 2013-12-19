package org.testatoo.core.evaluator.webdriver

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions
import org.testatoo.core.evaluator.MouseAction

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class WebDriverMouseAction implements MouseAction {

    private final WebDriver webDriver

    WebDriverMouseAction(WebDriver webDriver) {
        this.webDriver = webDriver
    }

    @Override
    void click(String id) {
        new Actions(webDriver).click(webDriver.findElement(By.id(id))).build().perform();
    }

    @Override
    void doubleClick(String id) {
        new Actions(webDriver).doubleClick(webDriver.findElement(By.id(id))).build().perform();
    }

    @Override
    void rightClick(String id) {
        new Actions(webDriver).contextClick(webDriver.findElement(By.id(id))).build().perform();
    }

    @Override
    void mouseOver(String id) {
        new Actions(webDriver).moveToElement(webDriver.findElement(By.id(id))).build().perform();
    }

    @Override
    void mouseOut(String id) {

    }

    @Override
    void dragAndDrop(String originId, String targetId) {
        new Actions(webDriver).dragAndDrop(webDriver.findElement(By.id(originId)), webDriver.findElement(By.id(targetId))).build().perform();
    }
}
