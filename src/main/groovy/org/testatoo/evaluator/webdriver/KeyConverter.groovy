/**
 * Copyright (C) 2016 Ovea (dev@ovea.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.evaluator.webdriver

import org.openqa.selenium.Keys
import org.testatoo.core.input.Key

import static org.testatoo.core.input.Key.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class KeyConverter {
    static Keys convert(Key key) {
        switch (key) {
            case SHIFT:
                return Keys.SHIFT
            case CTRL:
                return Keys.CONTROL
            case ALT:
                return Keys.ALT
            case ESCAPE:
                return Keys.ESCAPE
            case F1:
                return Keys.F1
            case F2:
                return Keys.F2
            case F3:
                return Keys.F3
            case F4:
                return Keys.F4
            case F5:
                return Keys.F5
            case F6:
                return Keys.F6
            case F7:
                return Keys.F7
            case F8:
                return Keys.F8
            case F9:
                return Keys.F9
            case F10:
                return Keys.F10
            case F11:
                return Keys.F11
            case F12:
                return Keys.F12
            case INSERT:
                return Keys.INSERT
            case DELETE:
                return Keys.DELETE
            case PAGE_UP:
                return Keys.PAGE_UP
            case PAGE_DOWN:
                return Keys.PAGE_DOWN
            case HOME:
                return Keys.HOME
            case END:
                return Keys.END
            case BACK_SPACE:
                return Keys.BACK_SPACE
            case MULTIPLY:
                return Keys.MULTIPLY
            case DIVIDE:
                return Keys.DIVIDE
            case SUBTRACT:
                return Keys.SUBTRACT
            case ADD:
                return Keys.ADD
            case EQUALS:
                return Keys.EQUALS
            case TAB:
                return Keys.TAB
            case RETURN:
                return Keys.RETURN
            case SPACE:
                return Keys.SPACE
            case LEFT:
                return Keys.LEFT
            case UP:
                return Keys.UP
            case RIGHT:
                return Keys.RIGHT
            case DOWN:
                return Keys.DOWN
        }
    }
}
