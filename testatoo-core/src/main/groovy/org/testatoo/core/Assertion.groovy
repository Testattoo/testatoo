/**
 * Copyright (C) 2013 Ovea (dev@ovea.com)
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
import org.testatoo.core.property.matcher.PropertyMatcher
import org.testatoo.core.state.State

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Assertion {

    final Component component

    Assertion(Component c) {
        this.component = c;
    }

    void is(State matcher) {
        assert component.is(matcher)
    }

    void be(State matcher) {
        assert component.be(matcher)
    }

    void is(PropertyMatcher m) {
        throw new IllegalArgumentException("Cannot use property matcher (${m}) for state")
    }

    void has(PropertyMatcher matcher) {
        assert component.has(matcher)
    }

    void contains(Component... components) {
        assert component.contains(components)
    }

    void displays(Component... components) {
        assert component.displays(components)
    }

}
