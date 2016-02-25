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
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.dsl.Browser
import org.testatoo.core.dsl.Window

import static org.mockito.Matchers.any
import static org.mockito.Mockito.*
import static org.testatoo.core.Testatoo.getConfig

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class BrowserTest {

    @Test
    public void should_delegate_to_underline_call() {
        Evaluator evaluator = mock(Evaluator)
        config.evaluator = evaluator

        Browser browser = new Browser()

        verify(evaluator, times(0)).title
        browser.title
        verify(evaluator, times(1)).title

        verify(evaluator, times(0)).pageSource
        browser.pageSource
        verify(evaluator, times(1)).pageSource

        verify(evaluator, times(0)).url
        browser.url
        verify(evaluator, times(1)).url

        verify(evaluator, times(0)).open(any(String))
        browser.open('http://www.testatoo.org')
        verify(evaluator, times(1)).open('http://www.testatoo.org')

        Window window = new Window('winId')
        verify(evaluator, times(0)).switchToWindow(any(String))
        browser.switchTo(window)
        verify(evaluator, times(1)).switchToWindow('winId')


        when(evaluator.windowIds).thenReturn([] as Set)
        assert browser.windows.size() == 0

        Window win_1 = new Window('win_1')
        Window win_2 = new Window('win_1')
        when(evaluator.windowIds).thenReturn(['win_1', 'win_2'] as Set)
        assert browser.windows.size() == 2
        assert browser.windows.containsAll(win_1, win_2)
    }
}
