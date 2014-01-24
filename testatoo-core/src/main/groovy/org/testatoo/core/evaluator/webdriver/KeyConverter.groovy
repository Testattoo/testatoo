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

import org.testatoo.core.input.Keys

import static org.testatoo.core.input.Keys.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class KeyConverter {


    static org.openqa.selenium.Keys convert(Keys key) {

        switch (key) {
            case ESCAPE:
                return org.openqa.selenium.Keys.ESCAPE
            case F1:
                return org.openqa.selenium.Keys.F1
            case F2:
                return org.openqa.selenium.Keys.F2
            case F3:
                return org.openqa.selenium.Keys.F3
            case F4:
                return org.openqa.selenium.Keys.F4
            case F5:
                return org.openqa.selenium.Keys.F5
            case F6:
                return org.openqa.selenium.Keys.F6
            case F7:
                return org.openqa.selenium.Keys.F7
            case F8:
                return org.openqa.selenium.Keys.F8
            case F9:
                return org.openqa.selenium.Keys.F9
            case F10:
                return org.openqa.selenium.Keys.F10
            case F11:
                return org.openqa.selenium.Keys.F11
            case F12:
                return org.openqa.selenium.Keys.F12
            case INSERT:
                return org.openqa.selenium.Keys.INSERT
            case DELETE:
                return org.openqa.selenium.Keys.DELETE
            case PAGE_UP:
                return org.openqa.selenium.Keys.PAGE_UP
            case PAGE_DOWN:
                return org.openqa.selenium.Keys.PAGE_DOWN
            case HOME:
                return org.openqa.selenium.Keys.HOME
            case END:
                return org.openqa.selenium.Keys.END
            case BACK_SPACE:
                return org.openqa.selenium.Keys.BACK_SPACE
            case MULTIPLY:
                return org.openqa.selenium.Keys.MULTIPLY
            case DIVIDE:
                return org.openqa.selenium.Keys.DIVIDE
            case SUBTRACT:
                return org.openqa.selenium.Keys.SUBTRACT
            case ADD:
                return org.openqa.selenium.Keys.ADD
            case EQUALS:
                return org.openqa.selenium.Keys.EQUALS
            case TAB:
                return org.openqa.selenium.Keys.TAB
            case RETURN:
                return org.openqa.selenium.Keys.RETURN
            case SPACE:
                return org.openqa.selenium.Keys.SPACE
            case LEFT:
                return org.openqa.selenium.Keys.LEFT
            case UP:
                return org.openqa.selenium.Keys.UP
            case RIGHT:
                return org.openqa.selenium.Keys.RIGHT
            case DOWN:
                return org.openqa.selenium.Keys.DOWN
        }
    }
}
