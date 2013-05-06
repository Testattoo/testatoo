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
package org.testatoo.core;

import org.testatoo.core.component.Component;
import org.testatoo.core.component.Page;
import org.testatoo.core.component.TextField;
import org.testatoo.core.input.Keyboard;
import org.testatoo.core.input.Mouse;
import org.testatoo.core.property.*;
import org.testatoo.core.state.*;

import java.util.concurrent.TimeUnit;

import static org.testatoo.core.ComponentFactory.component;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class Testatoo {

    public static Component it() {
        return Component.it();
    }

    public void assertThat(Matcher matcher) {
        matcher.matches();
    }

    public void and(Matcher matcher) {
        matcher.matches();
    }

    public void open(String url) {
        EvaluatorHolder.get().open(url);
    }

    public Page page() {
        return component(Page.class, By.$("body"));
    }

    // State
    public Available available() {
        return new Available();
    }

    public InvertedState unavailable() {
        return InvertedState.of(available());
    }

    public Enabled enabled() {
        return new Enabled();
    }

    public Disabled disabled() {
        return new Disabled();
    }

    public Visible visible() {
        return new Visible();
    }

    public Hidden hidden() {
        return new Hidden();
    }

    public Checked checked() {
        return new Checked();
    }

    public Unchecked unchecked() {
        return new Unchecked();
    }

    public Focused focused() {
        return new Focused();
    }

    public State not(State state) {
        return InvertedState.of(state);
    }

    // Property
    public Label label() {
        return new Label();
    }

    public Placeholder placeholder() {
        return new Placeholder();
    }

    public Value value() {
        return new Value();
    }

    public Text text() {
        return new Text();
    }

    public Title title() {
        return new Title();
    }

    public Reference reference() {
        return new Reference();
    }

    public SelectedValue selectedValue() {
        return new SelectedValue();
    }

    /**
     * Reset the field and call the method type
     *
     * @param value   value to be entered
     * @param component the component
     * @return the component
     */
    public static Component enter(String value, Component component) {
        if (component instanceof TextField) {
            EvaluatorHolder.get().reset(component);
            return type(value, component);
        }
        throw new AssertionError("The component is not a TextField");
    }

    /**
     * To simulate the enter of a value in a textField
     *
     * @param value   value to be entered
     * @param component the component
     * @return the component
     */
    public static Component type(String value, Component component) {
        if (component instanceof TextField) {
            EvaluatorHolder.get().focusOn(component);
            Keyboard.type(value);
            return component;
        }
        throw new AssertionError("The component is not a TextField");
    }

    /**
     * To simulate a click on a component
     *
     * @param component testatoo component
     * @return the component after click
     */
    public static Component clickOn(Component component) {
        Mouse.clickOn(component);
        return component;
    }

    /**
     * To simulate a double-click on a component
     *
     * @param component testatoo component
     * @return the component after double-click
     */
    public static Component doubleClickOn(Component component) {
        Mouse.doubleClickOn(component);
        return component;
    }

    /**
     * To simulate a mouse movement over a component
     *
     * @param component testatoo component
     * @return the component after mouse-over
     */
    public static Component dragMouseOver(Component component) {
        Mouse.mouseOverOn(component);
        return component;
    }

    /**
     * To simulate a mouse movement out of a component
     *
     * @param component testatoo component
     * @return the component after mouse-out
     */
    public static Component dragMouseOut(Component component) {
        Mouse.mouseOutOf(component);
        return component;
    }

    /**
     * To allow more readable tests
     *
     * @param component testatoo component
     * @return a testatoo component
     */
    public static Component on(Component component) {
        return into(component);
    }

    /**
     * To allow more readable tests
     *
     * @param component testatoo component
     * @return a testatoo component
     */
    public static Component into(Component component) {
        EvaluatorHolder.get().focusOn((Component) component);
        return component;
    }

    /**
     * Waiting until an assertion is reached. The timeout is 1 second
     *
     * @param matcher the matcher of code to evaluate
     */
    public static void waitUntil(Matcher matcher) {
        waitUntil(matcher, max(5, TimeUnit.SECONDS));
    }

    /**
     * Waiting until an assertion is reached.
     *
     * @param matcher the matcher of code to evaluate
     * @param duration maximum waiting time
     */
//    public static void waitUntil(State state, Duration duration) {
//        waitUntil(state, duration, freq(500, TimeUnit.MILLISECONDS));
//    }

    public static void waitUntil(Matcher matcher, Duration duration) {
        waitUntil(matcher, duration, freq(500, TimeUnit.MILLISECONDS));
    }

    /**
     * Waiting until an assertion is reached.
     *
     * @param matcher the matcher of code to evaluate
     * @param duration  maximum waiting time
     * @param frequency frequency of retries
     */
//    public static void waitUntil(State state, Duration duration, Duration frequency) {

    public static void waitUntil(Matcher matcher, Duration duration, Duration frequency) {
        final long step = frequency.unit.toMillis(frequency.duration);
        Throwable ex = null;
        try {
            for (long timeout = duration.unit.toMillis(duration.duration); timeout > 0; timeout -= step, Thread.sleep(step)) {
                try {
                    matcher.matches();
                    return;
                } catch (Throwable e) {
                    ex = e;
                }
            }
        } catch (InterruptedException iex) {
            throw new RuntimeException("Interrupted exception", iex);
        }
        throw new RuntimeException("Unable to reach the condition in " + duration.duration + " " + duration.unit, ex);
    }

    public static Duration max(long duration, TimeUnit unit) {
        return new Duration(duration, unit);
    }

    public static Duration freq(long duration, TimeUnit unit) {
        return new Duration(duration, unit);
    }

}
