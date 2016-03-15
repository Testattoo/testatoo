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
import org.testatoo.core.Actions
import org.testatoo.core.support.Checkable
import org.testatoo.core.support.Clearable
import org.testatoo.core.support.Resettable
import org.testatoo.core.support.Submissible
import org.testatoo.core.support.UnCheckable

import static org.mockito.Mockito.*
import static org.testatoo.core.Actions.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ActionTest {

//    @Test
//    public void should_have_visit_as_placeholder_on_browser_open() {
//        Browser browser = spy(new Browser())
//        Testatoo.browser = browser
//
//        visit 'some_url'
//        verify browser, times(1) open 'some_url'
//    }

    @Test
    public void should_be_able_to_perform_action_on_Checkable() {
        Checkable checkable_cmp = mock(Checkable)

        verify(checkable_cmp, times(0)).check()
        check checkable_cmp
        verify(checkable_cmp, times(1)).check()
    }

    @Test
    public void should_be_able_to_perform_action_on_UnCheckable() {
        UnCheckable unCheckable_cmp = mock(UnCheckable)

        verify(unCheckable_cmp, times(0)).uncheck()
        uncheck unCheckable_cmp
        verify(unCheckable_cmp, times(1)).uncheck()
    }

    @Test
    public void should_be_able_to_perform_action_on_Clearable() {
        Clearable clearable_cmp = mock(Clearable)

        verify(clearable_cmp, times(0)).clear()
        clear clearable_cmp
        verify(clearable_cmp, times(1)).clear()
    }

    @Test
    public void should_be_able_to_perform_action_on_Resettable() {
        Resettable resettable_cmp = mock(Resettable)

        verify(resettable_cmp, times(0)).reset()
        Actions.reset resettable_cmp
        verify(resettable_cmp, times(1)).reset()
    }

    @Test
    public void should_be_able_to_perform_action_on_Submissible() {
        Submissible submissible_cmp = mock(Submissible)

        verify(submissible_cmp, times(0)).submit()
        submit submissible_cmp
        verify(submissible_cmp, times(1)).submit()
    }


//    static void visit(String uri) { browser.open(uri) }
//
//    static void type(String text) { Keyboard.type(text) }
//
//    static <T extends Component> T on(Component c) { c as T }
//
//    static void select(Item... items) { items.each { it.select() } }
//
//    static void unselect(Item... items) { items.each { it.unselect() } }
//
//    static final FillAction fill(InputSupport c) { new FillAction(c) }
//
//    public static class FillAction {
//        private InputSupport input
//
//        public FillAction(InputSupport input) {
//            this.input = input
//        }
//
//        public void with(String value) {
//            input.value = value
//        }
//    }




}
