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

import static org.testatoo.core.ComponentFactory.component;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class Testatoo {

    private static ThreadLocal<Component> it = new ThreadLocal<Component>();

    public static Component it() {
        return it.get();
    }

    public void assertThat(Component component) {
        it.set(component);
    }

    public void assertThat(boolean expected) {}

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

    // Input

    /**
     * Reset the field and call the method type
     *
     * @param value   value to be entered
     * @param textField the textField
     * @return the textField
     */
    public static TextField enter(String value, TextField textField) {
        EvaluatorHolder.get().reset(textField);
        return type(value, textField);
    }

    /**
     * To simulate the enter of a value in a textField
     *
     * @param value   value to be entered
     * @param textField the textField
     * @return the textField with value in it
     */
    public static TextField type(String value, TextField textField) {
        EvaluatorHolder.get().focusOn(textField);
        Keyboard.type(value);
        return textField;
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

}
