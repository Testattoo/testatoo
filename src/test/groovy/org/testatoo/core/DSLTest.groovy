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

import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.component.CheckBox
import org.testatoo.core.component.Component
import org.testatoo.core.component.Form
import org.testatoo.core.component.Heading
import org.testatoo.core.component.Item
import org.testatoo.core.component.Radio
import org.testatoo.core.support.property.InputSupport

import static org.mockito.Matchers.any
import static org.mockito.Mockito.*
import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class DSLTest {

    private static MetaDataProvider meta

    @BeforeClass
    public static void before() {
        meta = mock(MetaDataProvider)
        when(meta.metaInfo(any(Component))).thenReturn(new MetaInfo(id: 'id', node: 'node'))
    }

    @Test
    public void should_verify_state_available_on_component() {
        Component cmp = spy(new Component(meta))
        doReturn(new LinkedList<>()).when(cmp).blocks

        doReturn(true).when(cmp).available()
        doReturn(true).when(cmp).enabled()
        doReturn(true).when(cmp).visible()

        cmp.should {
            be available
            be enabled
            be visible
        }

        doReturn(false).when(cmp).available()
        doReturn(false).when(cmp).enabled()
        doReturn(false).when(cmp).visible()

        cmp.should {
            be missing
            be disabled
            be hidden
        }
    }

    @Test
    public void should_be_able_to_check_and_uncheck() {
        CheckBox checkbox = mock(CheckBox)
        when(checkbox.blocks).thenReturn(new LinkedList<>())

        when(checkbox.checked()).thenReturn(false)

        checkbox.should { be unchecked }

        verify(checkbox, times(0)).check()
        check checkbox
        verify(checkbox, times(1)).check()

        when(checkbox.checked()).thenReturn(true)
        checkbox.should { be checked }

        verify(checkbox, times(0)).uncheck()
        uncheck checkbox
        verify(checkbox, times(1)).uncheck()

        when(checkbox.checked()).thenReturn(false)
        checkbox.should { be unchecked }
    }

    @Test
    public void should_be_able_to_visit() {
        config.evaluator = mock(Evaluator)

        verify(config.evaluator, times(0)).open('http://myUrl')
        visit('http://myUrl')
        verify(config.evaluator, times(1)).open('http://myUrl')
    }

    @Test
    public void should_be_able_to_fill_set_and_clear_fields() {
        InputSupport field = mock(InputSupport)
        Date now = new Date()

        verify(field, times(0)).value(any(Object))
        fill field with 'some text'
        set field with now
        verify(field, times(1)).value('some text')
        verify(field, times(1)).value(now)


        verify(field, times(0)).clear()
        clear field
        verify(field, times(1)).clear()
    }

    @Test
    public void should_be_able_to_select_and_unselect_item() {
        Item item_1 = mock(Item)
        Item item_2 = mock(Item)

        verify(item_1, times(0)).select()
        verify(item_2, times(0)).select()
        select item_1, item_2
        verify(item_1, times(1)).select()
        verify(item_2, times(1)).select()

        verify(item_1, times(0)).unselect()
        verify(item_2, times(0)).unselect()
        unselect item_1, item_2
        verify(item_1, times(1)).unselect()
        verify(item_2, times(1)).unselect()
    }

    @Test
    public void should_be_able_to_submit_and_reset_form() {
        Form form = mock(Form)

        verify(form, times(0)).reset()
        Testatoo.reset form // Explicit call to forbid Mockito reset method call
        verify(form, times(1)).reset()

        verify(form, times(0)).submit()
        submit form
        verify(form, times(1)).submit()
    }

    @Test
    public void should_be_able_to_type_text() {
        config.evaluator = mock(Evaluator)

        verify(config.evaluator, times(0)).type(['data'])
        type('data')
        verify(config.evaluator, times(1)).type(['data'])
    }

    @Test
    public void should_have_on_as_placeholder() {
        Radio radio = mock(Radio)
        Heading heading = mock(Heading)

        assert on(radio).is(radio)
        assert on(heading).is(heading)
    }
}
