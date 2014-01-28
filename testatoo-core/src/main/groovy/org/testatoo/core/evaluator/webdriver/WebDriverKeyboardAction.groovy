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

import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions
import org.testatoo.core.evaluator.KeyboardAction
import org.testatoo.core.input.Keys
import org.testatoo.core.input.KeysModifier

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class WebDriverKeyboardAction implements KeyboardAction {

    private final WebDriver webDriver
    private final Set<KeysModifier> keysDown = new HashSet<>();

    WebDriverKeyboardAction(WebDriver webDriver) {
        this.webDriver = webDriver
    }

    @Override
    void enter(String id, String data) {
        Actions action = new Actions(webDriver)
        addModifierKeys(action)
        action.sendKeys(data).build().perform()
        releaseAction()
    }

    @Override
    void enter(Keys key) {
        Actions action = new Actions(webDriver)
        addModifierKeys(action)
        action.sendKeys(KeyConverter.convert(key)).perform()
        releaseAction()
    }

    @Override
    void press(KeysModifier key) {
        keysDown.add(key)
    }

    @Override
    void release(KeysModifier key) {
        keysDown.remove(key)
    }

    @Override
    Set<KeysModifier> keysPressed() {
        return Collections.unmodifiableSet(keysDown)
    }

    @Override
    void releaseAll() {
        keysDown.clear()
    }

    protected addModifierKeys(Actions action) {
        keysDown.each {
            action.keyDown(KeyModifierConverter.convert(it))
        }
    }

    protected releaseAction() {
        Actions action = new Actions(webDriver)
        action = new Actions(webDriver)
        keysDown.each {
            action.keyUp(KeyModifierConverter.convert(it))
        }
        action.build().perform()
    }

}
