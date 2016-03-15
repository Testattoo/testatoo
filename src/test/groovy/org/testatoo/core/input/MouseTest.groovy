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
import org.testatoo.core.MetaDataProvider
import org.testatoo.core.component.Component

import static org.mockito.Mockito.*
import static org.testatoo.core.Testatoo.getConfig

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class MouseTest {

    @Test
    public void should_delegate_to_underline_call() {
        MetaDataProvider metaData = mock(MetaDataProvider)
        Evaluator evaluator = mock(Evaluator)
        config.evaluator = evaluator

        MouseFakeComponent component = spy(new MouseFakeComponent(metaData))
        component.id = '20'

        verify(component, times(0)).click()
        verify(component, times(0)).doubleClick()
        verify(component, times(0)).rightClick()

        Mouse.clickOn(component)
        verify(component, times(1)).click()
        verify(component, times(0)).doubleClick()
        verify(component, times(0)).rightClick()

        Mouse.doubleClickOn(component)
        verify(component, times(1)).click()
        verify(component, times(1)).doubleClick()
        verify(component, times(0)).rightClick()

        Mouse.rightClickOn(component)
        verify(component, times(1)).click()
        verify(component, times(1)).doubleClick()
        verify(component, times(1)).rightClick()

        Mouse.hoveringMouseOn(component)
        verify(evaluator, times(1)).mouseOver(component.id)

        DragBuilder builder = Mouse.drag(component)
        assert builder.dragged == component

        builder.on(component)
        verify(evaluator, times(1)).dragAndDrop(builder.dragged.id, '20')
    }

    private class MouseFakeComponent extends Component {
        String id

        MouseFakeComponent(MetaDataProvider metaData) { super(metaData) }

        @Override
        void click() {}

        @Override
        void rightClick() {}

        @Override
        void doubleClick() {}

//    @Override
//    DragBuilder drag() {
//        return new DragBuilder() {
//            @Override
//            void on(Component onto) {
//            }
//        }
//    }
    }
}
