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
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.times
import static org.mockito.Mockito.verify
import static org.testatoo.core.Testatoo.config

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class NavigationTest {

    @Test
    public void should_delegate_to_underline_call() {
        Evaluator evaluator = mock(Evaluator)
        config.evaluator = evaluator

        verify(evaluator, times(0)).to('some_url')
        Navigation.to('some_url')
        verify(evaluator, times(1)).to('some_url')

        verify(evaluator, times(0)).back()
        Navigation.back()
        verify(evaluator, times(1)).back()

        verify(evaluator, times(0)).forward()
        Navigation.forward()
        verify(evaluator, times(1)).forward()

        verify(evaluator, times(0)).refresh()
        Navigation.refresh()
        verify(evaluator, times(1)).refresh()
    }
}
