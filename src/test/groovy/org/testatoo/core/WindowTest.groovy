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
package org.testatoo.core

import org.junit.Test
import org.testatoo.core.Evaluator
import org.testatoo.core.Window

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.times
import static org.mockito.Mockito.verify
import static org.testatoo.core.Testatoo.getConfig

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class WindowTest {

    @Test
    public void should_create_a_window() {
        Window window = new Window('id')

        assert window.id == 'id'
        assert window.toString() == 'id'

        Evaluator evaluator = mock(Evaluator)
        config.evaluator = evaluator
    }

    @Test
    public void should_have_equal_based_on_id() {
        Window window_1 = new Window('id_1')
        Window window_2 = new Window('id_2')
        Window window_3 = new Window('id_1')

        assert  window_1 != window_2
        assert  window_1 == window_3
    }

    @Test
    public void should_have_hash_code_base_on_id() {
        Window window = new Window('id_1')

        assert window.hashCode() == 'id_1'.hashCode()
    }

    @Test
    public void should_call_underline_evaluator_on_close() {
        Evaluator evaluator = mock(Evaluator)
        config.evaluator = evaluator

        Window window = new Window('id')
        verify(evaluator, times(0)).closeWindow(window.id)
        window.close()
        verify(evaluator, times(1)).closeWindow(window.id)
    }
}
