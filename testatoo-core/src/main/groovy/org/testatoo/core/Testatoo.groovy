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

import org.testatoo.core.component.Component
import org.testatoo.core.evaluator.DeferredEvaluator
import org.testatoo.core.property.*
import org.testatoo.core.state.*

import java.util.concurrent.TimeoutException

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class Testatoo {

    // Settings
    Evaluator evaluator = new DeferredEvaluator()

    // DSL
    Component $(String jQuery, long timeout = 2000) { new Component(evaluator, new jQueryIdProvider(jQuery, timeout)) }

    void open(String uri) { evaluator.open(uri) }

    void assertThat(Block m) { run(m) }

    void on(Block m) { run(m) }

    void assertThat(Collection<Block> blocks) { run(Blocks.compose(blocks)) }

    void assertThat(Closure<?> c) {
        c()
        run(Blocks.compose(Blocks.pending()))
    }

    static void waitUntil(Block m, long timeout = 5000) {
        try {
            Util.waitUntil timeout, 500, {
                println "waitUntil: ${m}"
                m.run()
            }
        } catch (TimeoutException e) {
            throw new RuntimeException("${e.message} : ${m}")
        }
    }

    // Properties
    final PlaceholderProperty placeholder = new PlaceholderProperty()
    final LabelProperty label = new LabelProperty()
    final TextProperty text = new TextProperty()
    final ReferenceProperty reference = new ReferenceProperty()
    final TitleProperty title = new TitleProperty()

    final EnabledState enabled = new EnabledState(evaluator)
    final DisabledState disabled = new DisabledState(evaluator)
    final VisibleState visible = new VisibleState(evaluator)
    final HiddenState hidden = new HiddenState(evaluator)
    final AvailableState available = new AvailableState(evaluator)
    final MissingState missing = new MissingState(evaluator)
    final CheckedState checked = new CheckedState(evaluator)
    final UnCheckedState unchecked = new UnCheckedState(evaluator)

    // Utils
    static void run(Block block) {
        println block.toString()
        block.run()
    }

}
