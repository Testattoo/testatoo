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
package org.testatoo.hamcrest

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.component.Component
import org.testatoo.core.support.CheckSupport
import org.testatoo.core.support.InputSupport
import org.testatoo.core.support.LabelSupport
import org.testatoo.core.support.RangeSupport
import org.testatoo.core.support.SelectSupport
import org.testatoo.core.support.TextSupport
import org.testatoo.core.support.ValiditySupport
import org.testatoo.core.support.ValueSupport

import static org.junit.Assert.fail
import static org.hamcrest.MatcherAssert.assertThat
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.*
import static org.hamcrest.Matchers.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class MatchersTest {

    @Test
    public void should_have_matchers_for_generic_support() {
        Component cmp = mock(Component)

        // Enabled
        when(cmp.enabled()).thenReturn(true)
        // with combination of is from Hamcrest
        assertThat(cmp, is(enabled()))

        when(cmp.enabled()).thenReturn(false)
        try {
            assertThat(cmp, is(enabled()))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: is enabled     but: is disabled'
        }

        // Disabled
        when(cmp.disabled()).thenReturn(true)
        assertThat(cmp, is(disabled()))

        when(cmp.disabled()).thenReturn(false)
        try {
            assertThat(cmp, is(disabled()))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: is disabled     but: is enabled'
        }

        // Available
        when(cmp.available()).thenReturn(true)
        assertThat(cmp, is(available()))

        when(cmp.available()).thenReturn(false)
        try {
            assertThat(cmp, is(available()))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: is available     but: is missing'
        }

        // Missing
        when(cmp.missing()).thenReturn(true)
        assertThat(cmp, is(missing()))

        when(cmp.missing()).thenReturn(false)
        try {
            assertThat(cmp, is(missing()))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: is missing     but: is available'
        }

        // Visible
        when(cmp.visible()).thenReturn(true)
        assertThat(cmp, is(visible()))

        when(cmp.visible()).thenReturn(false)
        try {
            assertThat(cmp, is(visible()))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: is visible     but: is hidden'
        }

        // Hidden
        when(cmp.hidden()).thenReturn(true)
        assertThat(cmp, is(hidden()))

        when(cmp.hidden()).thenReturn(false)
        try {
            assertThat(cmp, is(hidden()))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: is hidden     but: is visible'
        }
    }

    @Test
    public void should_have_matcher_for_label_support() {
        LabelSupport cmp = mock(LabelSupport)
        when(cmp.label()).thenReturn('MyLabel')

        assertThat(cmp, has(label('MyLabel')))
        try {
            assertThat(cmp, has(label('OtherLabel')))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: has "OtherLabel"     but: has "MyLabel"'
        }
    }

    @Test
    public void should_have_matchers_for_input_support() {
        InputSupport cmp = mock(InputSupport)

        // Placeholder
        when(cmp.placeholder()).thenReturn('MyPlaceholder')
        assertThat(cmp, has(placeholder('MyPlaceholder')))
        try {
            assertThat(cmp, has(placeholder('OtherPlaceholder')))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: has "OtherPlaceholder"     but: has "MyPlaceholder"'
        }

        // Empty
        when(cmp.empty()).thenReturn(true)
        assertThat(cmp, is(Matchers.empty()))

        when(cmp.empty()).thenReturn(false)
        try {
            assertThat(cmp, is(Matchers.empty()))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: is empty     but: is filled'
        }

        // Filled
        when(cmp.filled()).thenReturn(true)
        assertThat(cmp, is(filled()))

        when(cmp.filled()).thenReturn(false)
        try {
            assertThat(cmp, is(filled()))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: is filled     but: is empty'
        }

        // ReadOnly
        when(cmp.readOnly()).thenReturn(true)
        assertThat(cmp, is(readOnly()))

        when(cmp.readOnly()).thenReturn(false)
        try {
            assertThat(cmp, is(readOnly()))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: is read only     but: is not read only'
        }

        // Required
        when(cmp.required()).thenReturn(true)
        assertThat(cmp, is(required()))

        when(cmp.required()).thenReturn(false)
        try {
            assertThat(cmp, is(required()))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: is required     but: is optional'
        }

        // Optional
        when(cmp.optional()).thenReturn(true)
        assertThat(cmp, is(optional()))

        when(cmp.optional()).thenReturn(false)
        try {
            assertThat(cmp, is(optional()))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: is optional     but: is required'
        }
    }

    @Test
    public void should_have_matchers_for_check_support() {
        CheckSupport cmp = mock(CheckSupport)

        // Checked
        when(cmp.checked()).thenReturn(true)
        assertThat(cmp, is(checked()))

        when(cmp.checked()).thenReturn(false)
        try {
            assertThat(cmp, is(checked()))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: is checked     but: is unchecked'
        }

        // Unchecked
        when(cmp.unchecked()).thenReturn(true)
        assertThat(cmp, is(unchecked()))

        when(cmp.unchecked()).thenReturn(false)
        try {
            assertThat(cmp, is(unchecked()))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: is unchecked     but: is checked'
        }
    }

    @Test
    public void should_have_matchers_for_range_support() {
        RangeSupport cmp = mock(RangeSupport)

        // Maximum
        when(cmp.maximum()).thenReturn(10)
        assertThat(cmp, has(maximum(10)))
        try {
            assertThat(cmp, has(maximum(50)))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: has <50>     but: has <10>'
        }

        // Minimum
        when(cmp.minimum()).thenReturn(10)
        assertThat(cmp, has(minimum(10)))
        try {
            assertThat(cmp, has(minimum(50)))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: has <50>     but: has <10>'
        }

        // Step
        when(cmp.step()).thenReturn(10)
        assertThat(cmp, has(step(10)))
        try {
            assertThat(cmp, has(step(50)))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: has <50>     but: has <10>'
        }

        // InRange
        when(cmp.inRange()).thenReturn(true)
        assertThat(cmp, is(inRange()))
        try {
            when(cmp.inRange()).thenReturn(false)
            assertThat(cmp, is(inRange()))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: is in range     but: is out of range'
        }

        // OutOfRange
        when(cmp.outOfRange()).thenReturn(true)
        assertThat(cmp, is(outOfRange()))
        try {
            when(cmp.outOfRange()).thenReturn(false)
            assertThat(cmp, is(outOfRange()))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: is out of range     but: is in range'
        }
    }

    @Test
    public void should_have_matchers_for_validity_support() {
        ValiditySupport cmp = mock(ValiditySupport)

        // Valid
        when(cmp.valid()).thenReturn(true)
        assertThat(cmp, is(valid()))
        try {
            when(cmp.valid()).thenReturn(false)
            assertThat(cmp, is(valid()))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: is valid     but: is invalid'
        }

        // Invalid
        when(cmp.invalid()).thenReturn(true)
        assertThat(cmp, is(invalid()))
        try {
            when(cmp.invalid()).thenReturn(false)
            assertThat(cmp, is(invalid()))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: is invalid     but: is valid'
        }
    }

    @Test
    public void should_have_matcher_for_text_support() {
        TextSupport cmp = mock(TextSupport)
        when(cmp.text()).thenReturn('MyText')

        assertThat(cmp, has(text('MyText')))
        try {
            assertThat(cmp, has(text('OtherText')))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: has "OtherText"     but: has "MyText"'
        }
    }

    @Test
    public void should_have_matchers_for_select_support() {
        SelectSupport cmp = mock(SelectSupport)

        // Selected
        when(cmp.selected()).thenReturn(true)
        assertThat(cmp, is(selected()))
        try {
            when(cmp.selected()).thenReturn(false)
            assertThat(cmp, is(selected()))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: is selected     but: is unselected'
        }

        // Unselected
        when(cmp.unselected()).thenReturn(true)
        assertThat(cmp, is(unselected()))
        try {
            when(cmp.unselected()).thenReturn(false)
            assertThat(cmp, is(unselected()))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: is unselected     but: is selected'
        }
    }

    @Test
    public void should_have_matcher_for_value_support() {
        ValueSupport cmp = mock(ValueSupport)
        when(cmp.value()).thenReturn('MyValue')

        assertThat(cmp, has(value('MyValue')))
        try {
            assertThat(cmp, has(value('OtherValue')))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: has "OtherValue"     but: has "MyValue"'
        }
    }

//    @Test
//    public void should_have_matcher_for_selected_items() {
//        Dropdown cmp = mock(Dropdown)
//
//        when(cmp.selectedItem)
//
////        when(cmp.selectedItems()).thenReturn([new Item])
//    }




    private static String message(AssertionError error) {
        error.message.replaceAll('[\n\r]', '')
    }
}
