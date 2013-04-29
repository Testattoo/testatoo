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
import org.testatoo.core.input.Keyboard;

import static org.mockito.Mockito.*;
import static org.testatoo.core.Evaluator.DEFAULT_NAME;
import static org.testatoo.core.input.Key.ENTER;
import static org.testatoo.core.input.KeyModifier.CONTROL;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class KeyboardTest {

    private Evaluator evaluator;

    @Before
    public void setUp() {
        evaluator = mock(Evaluator.class);
        when(evaluator.name()).thenReturn(DEFAULT_NAME);
        // Needed for Keyboard ;)
        EvaluatorHolder.register(evaluator);
    }

    @After
    public void clean() {
        EvaluatorHolder.unregister(DEFAULT_NAME);
    }

    @Test
    public void can_type_text() {
        Keyboard.type("Some text");
        verify(evaluator, times(1)).type("Some text");
    }

    // Key press is for key
    @Test
    public void can_pressKey() {
        Keyboard.press(ENTER);
        verify(evaluator, times(1)).press(ENTER);
    }

    // Key down and release is for keyModifier
    @Test
    public void can_keyDown() {
        Keyboard.keyDown(CONTROL);
        verify(evaluator, times(1)).keyDown(CONTROL);
    }

    @Test
    public void can_release() {
        Keyboard.keyDown(CONTROL);
        Keyboard.release(CONTROL);

        verify(evaluator, times(1)).keyDown(CONTROL);
        verify(evaluator, times(1)).release(CONTROL);
    }

//    @Test
//    public void can_release_a_pressed_keyModifier() {
//        DummyEvaluator dummyEvaluator = new DummyEvaluator();
//        EvaluatorHolder.register(dummyEvaluator);
//
//        assertThat(dummyEvaluator.getPressedKeyModifier().size(), is(0));
//
//        Keyboard.keyDown(CONTROL);
//        Keyboard.keyDown(ALT);
//
//        assertThat(dummyEvaluator.getPressedKeyModifier().size(), is(2));
//        assertThat(dummyEvaluator.getPressedKeyModifier(), hasItems(CONTROL, ALT));
//
//        Keyboard.release(ALT);
//
//        assertThat(dummyEvaluator.getPressedKeyModifier().size(), is(1));
//        assertThat(dummyEvaluator.getPressedKeyModifier(), hasItems(CONTROL));
//    }
//
//    @Test
//    public void can_release_all_keyModifier() {
//        DummyEvaluator dummyEvaluator = new DummyEvaluator();
//        EvaluatorHolder.register(dummyEvaluator);
//
//        Keyboard.keyDown(CONTROL);
//        Keyboard.keyDown(ALT);
//
//        assertThat(dummyEvaluator.getPressedKeyModifier().size(), is(2));
//        assertThat(dummyEvaluator.getPressedKeyModifier(), hasItems(CONTROL, ALT));
//
//        Keyboard.release();
//        assertThat(dummyEvaluator.getPressedKeyModifier().size(), is(0));
//    }

//    private class DummyEvaluator extends implements Evaluator {
//        @Override
//        public Object implementation() {
//            return null;
//        }
//
//        @Override
//        public String evaluate(String expression) {
//            return "";
//        }
//
//        @SuppressWarnings("unchecked")
//        public List<KeyModifier> getPressedKeyModifier() {
//            return Collections.unmodifiableList(pressedKeyModifier);
//        }
//    }
}
