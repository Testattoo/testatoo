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
import org.testatoo.core.component.Form
import org.testatoo.core.evaluator.DeferredEvaluator
import org.testatoo.core.evaluator.Evaluator
import org.testatoo.core.property.*
import org.testatoo.core.state.*

import java.util.concurrent.TimeoutException

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class Testatoo {

    // Settings
    // TODO Mathieu replace  DeferredEvaluator by PerThreadEvaluator
    static Evaluator evaluator = new DeferredEvaluator()

    // DSL
    static Component $(String jQuery, long timeout = 2000) { Component.$(jQuery,  timeout) }

    static void open(String uri) { evaluator.open(uri) }

    static Assertion assertThat(Component c) {
        new Assertion(c)
    }

    static void assertThat(Component component, @DelegatesTo(Component) Closure c) {
        c.delegate = component
        c(component)
        Blocks.run(Blocks.compose(Blocks.pending()))
    }

    static void assertThat(Closure c) {
        c()
        Blocks.run(Blocks.compose(Blocks.pending()))
    }

    static Component check(Component c) {
        evaluator.getString("testatoo.ext.check('${c.id}')")
        return c
    }

    static Interaction on(Component c) {
        return new Interaction(c)
    }

    static Form reset(Form form) {
        form.reset()
        return form
    }

    static Form submit(Form form) {
        form.submit()
        return form
    }

    static Component select(Component c) {
        evaluator.getString("testatoo.ext.selectItem('${c.id}')")
        return c
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

}
