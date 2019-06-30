/**
 * Copyright © 2018 Ovea (d.avenante@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.dsl

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.testatoo.core.*
import org.testatoo.core.component.*
import org.testatoo.core.component.field.RangeField
import org.testatoo.core.component.field.TextField

import static org.junit.jupiter.api.Assertions.fail
import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*
import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Key.CTRL
import static org.testatoo.core.input.MouseModifiers.LEFT
import static org.testatoo.core.input.MouseModifiers.SINGLE

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@DisplayName("Actions")
class ActionTest {
    private static MetaDataProvider meta
    private static MetaInfo metaInfo = new MetaInfo(id: 'id', node: 'node')

    @BeforeAll
    static void before() {
        meta = mock(MetaDataProvider)
        when(meta.metaInfo(any(Component))).thenReturn(metaInfo)
        config.evaluator = mock(Evaluator)
    }

    @Test
    @DisplayName("Should visit an URL")
    void should_visit() {
        config.evaluator = mock(Evaluator)

        verify(config.evaluator, times(0)).open('http://myUrl')
        visit('http://myUrl')
        verify(config.evaluator, times(1)).open('http://myUrl')
    }

    @Test
    @DisplayName("Should type text")
    void should_type_text() {
        config.evaluator = mock(Evaluator)

        verify(config.evaluator, times(0)).type(['data'])
        type('data')
        verify(config.evaluator, times(1)).type(['data'])
    }

    @Test
    @DisplayName("Should fill a field")
    void should_fill_field() {
        TextField field = spy(TextField)
        field.meta = meta

        fill field with 'Some value'
        verify(field, times(1)).value('Some value')
    }

    @Test
    @DisplayName("Should set a value")
    void should_set_a_value() {
        RangeField range = spy(RangeField)
        range.meta = meta

        set range to 10
        verify(range, times(1)).value(10)
    }

    @Test
    @DisplayName("Should have 'on' as language placeholder ... on component do something ...")
    void should_have_on_as_placeholder() {
        CheckBox checkBox = spy(CheckBox)
        assert on(checkBox).is(checkBox)
    }

    @Test
    @DisplayName("Should submit and reset a form")
    void should_submit_and_reset_form() {
        Form form = spy(Form.class)
        form.meta = meta

        Testatoo.reset form // Explicit call to forbid Mockito reset method call
        verify(form, times(1)).reset()

        submit form
        verify(form, times(1)).submit()
    }

    @Test
    @DisplayName("Should select items in components that containing items")
    void should_select_items_in_components_containing_items() {
        ListBox listBox = spy(ListBox)
        listBox.meta = meta

        Item item_1 = spy(Item)
        item_1.meta = meta

        Item item_2 = spy(Item)
        item_2.meta = meta

        config.evaluator = mock(Evaluator)

        doReturn([item_1, item_2]).when(listBox).items()
        doReturn('1').when(item_1).id()
        doReturn('2').when(item_2).id()

        listBox.select(item_1)
        verify(config.evaluator, times(1)).click('1', [LEFT, SINGLE], [CTRL])
        verify(config.evaluator, times(0)).click('2', [LEFT, SINGLE], [CTRL])

        reset(config.evaluator)
        reset(item_1)
        reset(item_2)
        doReturn('1').when(item_1).id()
        doReturn('2').when(item_2).id()
        doReturn(true).when(item_1).selected()

        listBox.unselect(item_1)
        verify(config.evaluator, times(1)).click('1', [LEFT, SINGLE], [CTRL])
        verify(config.evaluator, times(0)).click('2', [LEFT, SINGLE], [CTRL])

        reset(config.evaluator)
        reset(item_1)
        reset(item_2)
        doReturn('1').when(item_1).id()
        doReturn('2').when(item_2).id()
        doReturn('Item_1').when(item_1).value()
        doReturn('Item_2').when(item_2).value()

        listBox.select('Item_2')
        verify(config.evaluator, times(0)).click('1', [LEFT, SINGLE], [CTRL])
        verify(config.evaluator, times(1)).click('2', [LEFT, SINGLE], [CTRL])

        reset(config.evaluator)
        reset(item_1)
        reset(item_2)
        doReturn('1').when(item_1).id()
        doReturn('2').when(item_2).id()
        doReturn('Item_1').when(item_1).value()
        doReturn('Item_2').when(item_2).value()
        doReturn(true).when(item_1).selected()
        doReturn(true).when(item_2).selected()

        listBox.unselect('Item_1', 'Item_2')
        verify(config.evaluator, times(1)).click('1', [LEFT, SINGLE], [CTRL])
        verify(config.evaluator, times(1)).click('2', [LEFT, SINGLE], [CTRL])
    }

    @Test
    @DisplayName("Should throw an error when action on component does not correspond to its state")
    void should_throw_an_error_when_action_on_component_does_not_correspond_to_its_state() {
        CheckBox checkbox = spy(CheckBox)
        checkbox.meta = meta

        doReturn(false).when(checkbox).enabled()
        try {
            check checkbox
            fail()
        } catch (ComponentException e) {
            assert e.message.endsWith('is disabled and cannot be checked')
        }

        doReturn(true).when(checkbox).enabled()
        doReturn(true).when(checkbox).checked()
        try {
            check checkbox
            fail()
        } catch (ComponentException e) {
            assert e.message.endsWith('is already checked and cannot be checked')
        }

        doReturn(false).when(checkbox).enabled()
        try {
            uncheck checkbox
            fail()
        } catch (ComponentException e) {
            assert e.message.endsWith('is disabled and cannot be unchecked')
        }

        doReturn(true).when(checkbox).enabled()
        doReturn(false).when(checkbox).checked()
        try {
            uncheck checkbox
            fail()
        } catch (ComponentException e) {
            assert e.message.endsWith('is already unchecked and cannot be unchecked')
        }

        ListBox listBox = spy(ListBox)
        listBox.meta = meta

        Item item_1 = spy(Item)
        item_1.meta = meta

        doReturn([item_1]).when(listBox).items()
        doReturn(false).when(item_1).enabled()

        try {
            listBox.select(item_1)
            fail()
        } catch (ComponentException e) {
            assert e.message.endsWith('is disabled and cannot be selected')
        }

        reset(item_1)
        doReturn(true).when(item_1).enabled()
        doReturn(true).when(item_1).selected()

        try {
            listBox.select(item_1)
            fail()
        } catch (ComponentException e) {
            assert e.message.endsWith('is already selected and cannot be selected')
        }

        reset(item_1)
        doReturn(false).when(item_1).enabled()
        try {
            listBox.unselect(item_1)
            fail()
        } catch (ComponentException e) {
            assert e.message.endsWith('is disabled and cannot be deselected')
        }

        reset(item_1)
        doReturn(true).when(item_1).enabled()
        doReturn(false).when(item_1).selected()

        try {
            listBox.unselect(item_1)
            fail()
        } catch (ComponentException e) {
            assert e.message.endsWith('is already unselected and cannot be deselected')
        }
    }
}
