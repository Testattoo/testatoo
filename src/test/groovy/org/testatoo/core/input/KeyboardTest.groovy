/**
 * Copyright (C) 2016 Ovea (dev@ovea.com)
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
package org.testatoo.core.input

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.Evaluator

import static org.mockito.Matchers.any
import static org.mockito.Mockito.*
import static org.testatoo.core.Testatoo.config
import static org.testatoo.core.input.Key.SHIFT;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class KeyboardTest {

    @Test
    public void should_delegate_to_underline_call() {
        Evaluator evaluator = mock(Evaluator)
        config.evaluator = evaluator

        verify(evaluator, times(0)).type(any(Collection))

        Keyboard.type("some input")
        verify(evaluator, times(1)).type(["some input"])

        Keyboard.type(SHIFT + 'testatoo')
        verify(evaluator, times(1)).type(SHIFT + 'testatoo')
    }
}
