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
package org.testatoo.core

import groovy.time.TimeDuration
import org.testatoo.core.component.Component
import org.testatoo.core.evaluator.Evaluator
import org.testatoo.core.evaluator.PerThreadEvaluator
import org.testatoo.core.property.*
import org.testatoo.core.state.*

import java.util.concurrent.TimeoutException

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class Testatoo {

    // Settings
    static Evaluator evaluator = new PerThreadEvaluator()

    // DSL
    static Component $(String jQuery, long timeout = 2000) { new Component(evaluator, new jQueryIdProvider(jQuery, timeout)) }

    static void open(String uri) { evaluator.open(uri) }

    static void assertThat(Block m) { Blocks.run m }

    static void assertThat(Collection<Block> blocks) { Blocks.run Blocks.and(blocks) }

    static void assertThat(Closure<?> c) {
        c()
        Blocks.run Blocks.and(Blocks.pending())
    }

    static void waitUntil(Block m, TimeDuration duration = 5.seconds) {
        try {
            Util.waitUntil duration.toMilliseconds(), 500, {
                Log.testatoo "waitUntil: ${m}"
                m.run()
            }
        } catch (TimeoutException e) {
            throw new RuntimeException("${e.message} : ${m}")
        }
    }

    // Properties
    final Placeholder placeholder = new Placeholder()
    final Label label = new Label()
    final Text text = new Text()
    final Value value = new Value()
    final Reference reference = new Reference()
    final Title title = new Title()
    final Size size = new Size()

    // States
    final Enabled enabled = new Enabled()
    final Disabled disabled = new Disabled()
    final Visible visible = new Visible()
    final Hidden hidden = new Hidden()
    final Available available = new Available()
    final Missing missing = new Missing()
    final Checked checked = new Checked()
    final Unchecked unchecked = new Unchecked()

}
