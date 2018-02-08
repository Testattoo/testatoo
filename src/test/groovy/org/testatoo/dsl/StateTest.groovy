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

import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.bundle.stub.CheckBoxStub
import org.testatoo.bundle.stub.ItemStub
import org.testatoo.bundle.stub.field.RangeFieldStub
import org.testatoo.bundle.stub.field.TextFieldStub
import org.testatoo.core.Evaluator
import org.testatoo.core.MetaDataProvider
import org.testatoo.core.MetaInfo
import org.testatoo.core.component.Button
import org.testatoo.core.component.CheckBox
import org.testatoo.core.component.Component
import org.testatoo.core.component.Item
import org.testatoo.core.component.field.RangeField
import org.testatoo.core.component.field.TextField

import static org.junit.Assert.fail
import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*
import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class StateTest {
    private static MetaDataProvider meta
    private static MetaInfo metaInfo = new MetaInfo(id: 'id', node: 'node')

    @BeforeClass
    static void before() {
        meta = mock(MetaDataProvider)
        when(meta.metaInfo(any(Component))).thenReturn(metaInfo)
        config.evaluator = mock(Evaluator)
    }

    @Test
    void should_support_available_enabled_visible() {
        Component cmp = spy(new Component(meta))

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
    void should_support_checked_unchecked() {
        CheckBox checkbox = spy(new CheckBoxStub())
        checkbox.meta = meta

        doReturn(true).when(checkbox).enabled()
        doReturn(false).when(checkbox).checked()
        checkbox.should { be unchecked }

        check checkbox
        verify(config.evaluator, times(1)).click(any(String), any(Collection), any(Collection))

        doReturn(true).when(checkbox).checked()
        checkbox.should { be checked }

        uncheck checkbox
        verify(config.evaluator, times(2)).click(any(String), any(Collection), any(Collection))
    }

    @Test
    void should_support_required() {
        TextField field = spy(new TextFieldStub())
        field.meta = meta

        doReturn(false).when(field).required()
        field.should { be optional }

        doReturn(true).when(field).required()
        field.should { be required }
    }

    @Test
    void should_support_valid() {
        TextField field = spy(new TextFieldStub())
        field.meta = meta

        doReturn(true).when(field).valid()
        field.should { be valid }

        doReturn(false).when(field).valid()
        field.should { be invalid }
    }

    @Test
    void should_support_empty() {
        TextField field = spy(new TextFieldStub())
        field.meta = meta

        doReturn(true).when(field).empty()
        field.should { be empty }

        doReturn(false).when(field).empty()
        field.should { be filled }
    }

    @Test
    void should_support_readOnly() {
        TextField field = spy(new TextFieldStub())
        field.meta = meta
        doReturn(true).when(field).readOnly()
        field.should { be readOnly }

        doReturn(false).when(field).readOnly()
        try {
            field.should { be readOnly }
            fail()
        } catch (AssertionError e) {
            // Good
        }
    }

    @Test
    void should_support_focused() {
        TextField field = spy(new TextFieldStub())
        field.meta = meta

        doReturn(true).when(field).focused()
        field.should { be focused }

        doReturn(false).when(field).focused()
        try {
            field.should { be focused }
            fail()
        } catch (AssertionError e) {
            // Good
        }
    }

    @Test
    void should_support_selected() {
        Item item = spy(new ItemStub())
        item.meta = meta

        doReturn(false).when(item).selected()
        item.should { be unselected }

        doReturn(true).when(item).selected()
        item.should { be selected }
    }

    @Test
    void should_support_range() {
        RangeField field = spy(new RangeFieldStub())
        field.meta = meta

        doReturn(true).when(field).inRange()
        field.should { be inRange }

        doReturn(false).when(field).inRange()
        field.should { be outOfRange }
    }

    @Test
    void should_support_contain() {
        Component cmp = spy(new Component(meta))
        config.evaluator = mock(Evaluator)

        when(config.evaluator.getJson(any(String))).thenReturn([])
        cmp.should { contain mock(Button) }
    }
}
