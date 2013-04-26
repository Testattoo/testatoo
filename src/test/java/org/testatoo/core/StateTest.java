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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testatoo.core.component.CheckBox;
import org.testatoo.core.component.Component;
import org.testatoo.core.component.Radio;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class StateTest extends Testatoo {

    private Evaluator evaluator;
    private String id = "myId";

    // TODO test assertion failure error messages ;)

    @Before
    public void setUp() {
        evaluator = mock(Evaluator.class);
        when(evaluator.name()).thenReturn(Evaluator.DEFAULT_NAME);

        EvaluatorHolder.register(evaluator);
    }

    @After
    public void clean() {
        EvaluatorHolder.unregister(Evaluator.DEFAULT_NAME);
    }

//    @Test
//    public void test_availability_state() {
//
//        Component available_component = new Component(id);
//        assertThat(available_component.is(available()));
//
//        Component unavailable_component = new Component(id);
//        assertThat(unavailable_component.is(not(available())));
//        assertThat(unavailable_component.is(unavailable()));
//    }

    @Test
    public void test_enabled_state() {
        Component enabled_component = new Component(id);
        when(evaluator.isEnabled(enabled_component)).thenReturn(true);

        assertThat(enabled_component.is(enabled()));

        Component disabled_component = new Component(id);
        when(evaluator.isEnabled(disabled_component)).thenReturn(false);
        assertThat(disabled_component.is(not(enabled())));
        assertThat(disabled_component.is(disabled()));
    }

    @Test
    public void test_visibility_state() {
        Component visible_component = new Component(id);
        when(evaluator.isVisible(visible_component)).thenReturn(true);

        assertThat(visible_component.is(visible()));

        Component hidden_component = new Component(id);
        when(evaluator.isVisible(hidden_component)).thenReturn(false);
//        assertThat(hidden_component.is(not(visible())));
        assertThat(hidden_component.is(hidden()));
    }

    @Test
    public void test_checked_state() {
        CheckBox checked_checkBox = new CheckBox(id);
        when(evaluator.isChecked(checked_checkBox)).thenReturn(true);

        assertThat(checked_checkBox.is(checked()));

        CheckBox unchecked_checkBox = new CheckBox(id);
        when(evaluator.isChecked(unchecked_checkBox)).thenReturn(false);

//        assertThat(unchecked_checkBox.is(not(checked())));
        assertThat(unchecked_checkBox.is(unchecked()));

        // Throw Error if component doesn't have the Checkable nature
        try {
            Component component = new Component(id);
            assertThat(component.is(checked()));
        } catch (AssertionError e) {
            assertEquals(e.getMessage(), "The component is not Checkable");
        }

        // Same with Radio
        Radio checked_radio = new Radio(id);
        when(evaluator.isChecked(checked_radio)).thenReturn(true);

        assertThat(checked_radio.is(checked()));

        Radio unchecked_radio = new Radio(id);
        when(evaluator.isChecked(unchecked_radio)).thenReturn(false);

//        assertThat(unchecked_radio.is(not(checked())));
        assertThat(unchecked_radio.is(unchecked()));
    }

    @Test
    public void test_focus_state() {
        Component focused_component = new Component(id);
        when(evaluator.isFocused(focused_component)).thenReturn(true);

        assertThat(focused_component.is(focused()));

        Component none_focused_component = new Component(id);
        when(evaluator.isFocused(none_focused_component)).thenReturn(false);
//        assertThat(none_focused_component.is(not(focused())));
    }
}
