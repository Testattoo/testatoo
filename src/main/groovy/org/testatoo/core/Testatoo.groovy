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
package org.testatoo.core

import org.testatoo.core.component.Component
import org.testatoo.core.input.Keyboard
import org.testatoo.core.input.Mouse
import org.testatoo.core.internal.CachedMetaData
import org.testatoo.core.internal.jQueryIdProvider
import org.testatoo.hamcrest.AvailableMatcher
import org.testatoo.hamcrest.CheckedMatcher
import org.testatoo.hamcrest.DisabledMatcher
import org.testatoo.hamcrest.EnabledMatcher
import org.testatoo.hamcrest.HiddenMatcher
import org.testatoo.hamcrest.MissingMatcher
import org.testatoo.hamcrest.UnCheckedMatcher
import org.testatoo.hamcrest.VisibleMatcher

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class Testatoo {

    /**
     * Access Testatoo config
     */
    static Config config = new Config()

    /**
     * Controls your browser
     */
    static Browser browser = new Browser()

    /**
     * Access the keyboard
     */
    static Keyboard keyboard = new Keyboard()

    /**
     * Access the mouse
     */
    static Mouse mouse = new Mouse()

    /**
     * Create a component
     */
    static Component $(String jQuery) {
        new Component(new CachedMetaData(idProvider: new jQueryIdProvider(jQuery, true)))
    }

    /**
     * Creates a list of component
     */
    static Components<? extends Component> $$(String jQuery) {
        new Components<>(Component, new CachedMetaData(idProvider: new jQueryIdProvider(jQuery, false)))
    }

    static {
        config.scan 'org.testatoo.bundle.html5'
    }

    // States
    public static enabled = EnabledMatcher
    public static disabled = DisabledMatcher
    public static visible = VisibleMatcher
    public static hidden = HiddenMatcher
    public static available = AvailableMatcher
    public static missing = MissingMatcher
    public static unchecked = UnCheckedMatcher
    public static checked = CheckedMatcher

    // Properties
}
