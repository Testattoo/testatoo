/**
 * Copyright (C) 2014 Ovea (dev@ovea.com)
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
package org.testatoo.core

import org.testatoo.core.dsl.Browser
import org.testatoo.core.input.Keyboard
import org.testatoo.core.input.Mouse

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class Testatoo {

    /**
     * Access Testatoo config
     */
    static final Config config = new Config()

    /**
     * Controls your browser
     */
    static final Browser browser = new Browser()

    /**
     * Access the keyboard
     */
    static final Keyboard keyboard = new Keyboard()

    /**
     * Access the mouse
     */
    static final Mouse mouse = new Mouse()

    /**
     * Create a component
     */
    static Component $(String jQuery, long timeout = 2000) { Component.$(jQuery, timeout) }

    /**
     * Creates a list of components
     */
    static Components<? extends Component> $$(String jQuery, long timeout = 2000) { Components.$$(jQuery, timeout) }

    static {
        config.scan 'org.testatoo.bundle.html5'
    }

}
