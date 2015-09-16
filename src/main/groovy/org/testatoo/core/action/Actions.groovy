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
package org.testatoo.core.action

import groovy.time.TimeDuration
import org.testatoo.core.Blocks
import org.testatoo.core.Component
import org.testatoo.core.Log
import org.testatoo.core.Testatoo

import java.util.concurrent.TimeoutException

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Actions {

    static void visit(String uri) { Testatoo.browser.open(uri) }

    static void open(String uri) { visit(uri) }

    static final Component check(Component c) {
        c.execute(new Check())
        return c
    }

    static final Component uncheck(Component c) {
        c.execute(new Uncheck())
        return c
    }

    static final Component select(Component c) {
        c.execute(new Select())
        return c
    }

    static final Component unselect(Component c) {
        c.execute(new Unselect())
        return c
    }

    static final Component on(Component c) {
        return c
    }

    static final Component fill(Component c) {
        return c
    }

    static final Component clear(Component c) {
        c.execute(new Clear())
        return c
    }

    static final Component reset(Component c) {
        c.execute(new Reset())
        return c
    }

    static final Component submit(Component c) {
        c.execute(new Submit())
        return c
    }
}
