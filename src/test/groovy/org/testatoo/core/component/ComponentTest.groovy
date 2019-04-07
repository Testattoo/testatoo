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
package org.testatoo.core.component

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.testatoo.core.Evaluator
import org.testatoo.core.MetaDataProvider
import org.testatoo.core.MetaInfo
import org.testatoo.core.input.DragBuilder
import org.testatoo.core.support.Clickable
import org.testatoo.core.support.Draggable

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*
import static org.testatoo.core.Testatoo.config
import static org.testatoo.core.input.MouseModifiers.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@DisplayName("Component")
class ComponentTest {
    MetaDataProvider metaData
    Evaluator evaluator

    @BeforeEach
    void before() {
        metaData = mock(MetaDataProvider)
        when(metaData.metaInfo(any(Component))).thenReturn(new MetaInfo(id: 'id', node: 'node'))
        evaluator = mock(Evaluator)
        config.evaluator = evaluator
    }

    @Test
    @DisplayName("Should have expected inheritance")
    void inheritance() {
        assert Component in Clickable
        assert Component in Draggable
    }

    @Test
    void should_be_initialized_with_a_meta_data_provider() {
        Component cmp = new Component(metaData)
        assert cmp.meta == metaData
    }

    @Test
    void should_have_identity_on_id() {
        Component cmp_1 = new Component(metaData)
        Component cmp_2 = new Component(metaData)
        Component cmp_3 = new Component(metaData)
        Component cmp_4 = new Component(metaData) {}

        when(metaData.metaInfo(any(Component)))
            .thenReturn(new MetaInfo(id: 'cmpId_1')) // Call on id for cmp_1
            .thenReturn(new MetaInfo(id: 'cmpId_2')) // Call on id for cmp_2
            .thenReturn(new MetaInfo(id: 'cmpId_1')) // Call on id for cmp_1
            .thenReturn(new MetaInfo(id: 'cmpId_1')) // Call on id for cmp_3
            .thenReturn(new MetaInfo(id: 'cmpId_1')) // Call on id for cmp_1
            .thenReturn(new MetaInfo(id: 'cmpId_1')) // Call on id for cmp_3

        assert cmp_1 != cmp_2 // Same class not same id
        assert cmp_1 == cmp_3  // Same class and same id
        assert cmp_1 != cmp_4  // Different class and same id

        assert cmp_1.hashCode() == cmp_1.id().hashCode()
    }

    @Test
    void should_implement_toString_based_on_class_name_and_id() {
        Component cmp_1 = new Component(metaData)
        when(metaData.metaInfo(any(Component))).thenReturn(new MetaInfo(id: 'cmpId_1'))

        assert cmp_1.toString() == 'Component:cmpId_1'
    }

    @Test
    void should_have_generic_behaviours_delegated_to_evaluator() {
        String cmp_id = 'cmpId_1'
        Component component = new Component(metaData)
        when(metaData.metaInfo(any(Component))).thenReturn(new MetaInfo(id: cmp_id))

        String default_enabled_check_expression = "it.is(':disabled') || !!it.attr('disabled')"
        verify(evaluator, times(0)).check(cmp_id, default_enabled_check_expression)

        component.enabled()
        verify(evaluator, times(1)).check(cmp_id, default_enabled_check_expression)

        component.enabled()
        verify(evaluator, times(2)).check(cmp_id, default_enabled_check_expression)

        String default_visibility_check_expression = "it.is(':hidden')"
        verify(evaluator, times(0)).check(cmp_id, default_visibility_check_expression)

        component.visible()
        verify(evaluator, times(1)).check(cmp_id, default_visibility_check_expression)

        reset(metaData)
        when(metaData.metaInfo(any(Component))).thenReturn(new MetaInfo(id: cmp_id))
        verify(metaData, times(0)).metaInfo(component)

        component.available()
        verify(metaData, times(1)).metaInfo(component)

        verify(evaluator, times(0)).click(cmp_id, [LEFT, SINGLE], [])
        component.click()
        verify(evaluator, times(1)).click(cmp_id, [LEFT, SINGLE], [])

        verify(evaluator, times(0)).click(cmp_id, [RIGHT, SINGLE], [])
        component.rightClick()
        verify(evaluator, times(1)).click(cmp_id, [RIGHT, SINGLE], [])

        verify(evaluator, times(0)).click(cmp_id, [LEFT, DOUBLE], [])
        component.doubleClick()
        verify(evaluator, times(1)).click(cmp_id, [LEFT, DOUBLE], [])

        DragBuilder dragBuilder = component.drag()
        assert dragBuilder.dragged == component
    }
}
