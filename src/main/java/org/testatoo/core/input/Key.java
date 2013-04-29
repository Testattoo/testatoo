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
package org.testatoo.core.input;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public enum Key {
    F1(112),
    F2(113),
    F3(114),
    F4(115),
    F5(116),
    F6(117),
    F7(118),
    F8(119),
    F9(120),
    F10(121),
    F11(122),
    F12(123),

    NUMPAD0(96),
    NUMPAD1(97),
    NUMPAD2(98),
    NUMPAD3(99),
    NUMPAD4(100),
    NUMPAD5(101),
    NUMPAD6(102),
    NUMPAD7(103),
    NUMPAD8(104),
    NUMPAD9(105),

    BACKSPACE(8),
    TAB(9),
    ENTER(13),
    PAUSE(19),
    CAPS_LOCK(20),
    ESCAPE(27),
    SPACE(32),
    PAGE_UP(33),
    PAGE_DOWN(34),
    END(35),
    HOME(36),
    LEFT_ARROW(37),
    UP_ARROW(38),
    RIGHT_ARROW(39),
    DOWN_ARROW(40),
    INSERT(45),
    DELETE(46),
    MULTIPLY(106),
    ADD(107),
    SUBTRACT(109),
    DIVIDE(111),
    NUM_LOCK(144),
    SCROLL_LOCK(145),
    SEMI_COLON(186),
    DECIMAL_POINT(110),
    EQUAL(187),
    COMMA(188),
    DASH(189),
    PERIOD(190),
    FORWARD_SLASH(191),
    GRAVE_ACCENT(192),
    OPEN_BRACKET(219),
    BACK_SLASH(220),
    CLOSE_BRACKET(221),
    SINGLE_QUOTE(222);

    private final int code;

    Key(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
