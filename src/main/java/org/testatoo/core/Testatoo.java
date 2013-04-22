/**
 * Copyright (C) 2013 Ovea <dev@testatoo.org>
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

import org.testatoo.core.property.*;
import org.testatoo.core.state.*;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class Testatoo {

    public void assertThat(boolean expected) {
        if (!expected) {
            throw new AssertionError("Expected true but was false");
        }
    }

    public void open(String url) {
        EvaluatorHolder.get().open(url);
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
    public Label label(String label) {
        return new Label(label);
    }

    public Value value(String value) {
        return new Value(value);
    }

    public Text text(String text) {
        return new Text(text);
    }

    public Title title(String title) {
        return new Title(title);
    }

    public Reference reference(String reference) {
        return new Reference(reference);
    }

}
