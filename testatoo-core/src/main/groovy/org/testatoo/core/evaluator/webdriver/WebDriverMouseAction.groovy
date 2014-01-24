/**
 * Copyright (C) 2008 Ovea <dev@ovea.com>
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
    private final WebDriverKeyboardAction keyboard

    WebDriverMouseAction(WebDriver webDriver, WebDriverKeyboardAction keyboard) {
        this.webDriver = webDriver
        this.keyboard = keyboard
    }

    @Override
    void click(String id) {
        Actions action = new Actions(webDriver)
        keyboard.addModifierKeys(action)
        action.click(webDriver.findElement(By.id(id))).build().perform()
        keyboard.releaseAction()
    }

    @Override
    void doubleClick(String id) {
        new Actions(webDriver).doubleClick(webDriver.findElement(By.id(id))).build().perform()
    }

    @Override
    void rightClick(String id) {
        new Actions(webDriver).contextClick(webDriver.findElement(By.id(id))).build().perform()
    }

    @Override
    void mouseOver(String id) {
        new Actions(webDriver).moveToElement(webDriver.findElement(By.id(id))).build().perform()
    }

    @Override
    void dragAndDrop(String originId, String targetId) {
        new Actions(webDriver).dragAndDrop(webDriver.findElement(By.id(originId)), webDriver.findElement(By.id(targetId))).build().perform()
    }

}
