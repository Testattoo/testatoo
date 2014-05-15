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

import groovy.time.TimeDuration
import org.testatoo.core.component.Component
import org.testatoo.core.component.Form
import org.testatoo.core.component.input.TextField
import org.testatoo.core.evaluator.Evaluator
import org.testatoo.core.state.Checked

import java.util.concurrent.TimeoutException

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class Testatoo {

    static Evaluator evaluator

    // DSL
    static Component $(String jQuery, long timeout = 2000) { Component.$(jQuery, timeout) }

    static Components<? extends Component> $$(String jQuery, long timeout = 2000) { Components.$$(jQuery, timeout) }

    static void open(String uri) { evaluator.open(uri) }

    static Assertion assertThat(Component c) {
        new Assertion(c)
    }

    static <T extends Component> Assertions<T> assertThat(Components<T> cc) {
        new Assertions<T>(cc)
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
        if (!c.getState(new Checked()))
            evaluator.click(c.id)
        return c
    }

    static Interaction on(Component c) {
        return new Interaction(c)
    }

    static <T extends Component> Interactions<T> on(Components<T> cc) {
        return new Interactions<T>(cc)
    }

    static Form reset(Form form) {
        form.reset()
        return form
    }

    static TextField reset(TextField textField) {
        textField.reset()
        return textField
    }

    static Form submit(Form form) {
        form.submit()
        return form
    }

    static Component select(Component c) {
        on(c).select(c)
        return c
    }

    static void waitUntil(TimeDuration duration = 5.seconds, Closure c) {
        c()
        try {
            Util.waitUntil duration.toMilliseconds(), 500, {
                Log.testatoo "waitUntil: ${c}"
                Blocks.run(Blocks.compose(Blocks.pending()))
            }
        } catch (TimeoutException e) {
            throw new RuntimeException("${e.message}")
        }
    }

}
