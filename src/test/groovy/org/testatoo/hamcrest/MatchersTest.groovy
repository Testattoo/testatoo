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
import org.testatoo.bundle.html5.list.MultiSelect
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
            assertThat(cmp, enabled())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: <' + cmp + '> is enabled     but: is disabled'
        }

        // Disabled
        when(cmp.disabled()).thenReturn(true)
        assertThat(cmp, disabled())

        when(cmp.disabled()).thenReturn(false)
        try {
            assertThat(cmp, disabled())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: <' + cmp + '> is disabled     but: is enabled'
        }

        // Available
        when(cmp.available()).thenReturn(true)
        assertThat(cmp, available())

        when(cmp.available()).thenReturn(false)
        try {
            assertThat(cmp, available())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: <' + cmp + '> is available     but: is missing'
        }

        // Missing
        when(cmp.missing()).thenReturn(true)
        assertThat(cmp, missing())

        when(cmp.missing()).thenReturn(false)
        try {
            assertThat(cmp, missing())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: <' + cmp + '> is missing     but: is available'
        }

        // Visible
        when(cmp.visible()).thenReturn(true)
        assertThat(cmp, visible())

        when(cmp.visible()).thenReturn(false)
        try {
            assertThat(cmp, visible())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: <' + cmp + '> is visible     but: is hidden'
        }

        // Hidden
        when(cmp.hidden()).thenReturn(true)
        assertThat(cmp, hidden())

        when(cmp.hidden()).thenReturn(false)
        try {
            assertThat(cmp, hidden())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: <' + cmp + '> is hidden     but: is visible'
        }
    }

    @Test
    public void should_have_matcher_for_label_support() {
        LabelSupport cmp = mock(LabelSupport)
        when(cmp.label()).thenReturn('MyLabel')

        assertThat(cmp, hasLabel('MyLabel'))
        try {
            assertThat(cmp, hasLabel('OtherLabel'))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: "OtherLabel"     but: was "MyLabel"'
        }
    }

    @Test
    public void should_have_matchers_for_input_support() {
        InputSupport cmp = mock(InputSupport)

        // Placeholder
        when(cmp.placeholder()).thenReturn('MyPlaceholder')
        assertThat(cmp, hasPlaceholder('MyPlaceholder'))
        try {
            assertThat(cmp, hasPlaceholder('OtherPlaceholder'))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: "OtherPlaceholder"     but: was "MyPlaceholder"'
        }

        // Empty
        when(cmp.empty()).thenReturn(true)
        assertThat(cmp, Matchers.empty())

        when(cmp.empty()).thenReturn(false)
        try {
            assertThat(cmp, Matchers.empty())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component is empty     but: is filled'
        }

        // Filled
        when(cmp.filled()).thenReturn(true)
        assertThat(cmp, filled())

        when(cmp.filled()).thenReturn(false)
        try {
            assertThat(cmp, filled())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component is filled     but: is empty'
        }

        // ReadOnly
        when(cmp.readOnly()).thenReturn(true)
        assertThat(cmp, readOnly())

        when(cmp.readOnly()).thenReturn(false)
        try {
            assertThat(cmp, readOnly())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component is read only     but: is not read only'
        }

        // Required
        when(cmp.required()).thenReturn(true)
        assertThat(cmp, required())

        when(cmp.required()).thenReturn(false)
        try {
            assertThat(cmp, required())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component is required     but: is optional'
        }

        // Optional
        when(cmp.optional()).thenReturn(true)
        assertThat(cmp, optional())

        when(cmp.optional()).thenReturn(false)
        try {
            assertThat(cmp, optional())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component is optional     but: is required'
        }
    }

    @Test
    public void should_have_matchers_for_check_support() {
        CheckSupport cmp = mock(CheckSupport)

        // Checked
        when(cmp.checked()).thenReturn(true)
        assertThat(cmp, checked())

        when(cmp.checked()).thenReturn(false)
        try {
            assertThat(cmp, checked())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component is checked     but: is unchecked'
        }

        // Unchecked
        when(cmp.unchecked()).thenReturn(true)
        assertThat(cmp, unchecked())

        when(cmp.unchecked()).thenReturn(false)
        try {
            assertThat(cmp, unchecked())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component is unchecked     but: is checked'
        }
    }

    @Test
    public void should_have_matchers_for_range_support() {
        RangeSupport cmp = mock(RangeSupport)

        // Maximum
        when(cmp.maximum()).thenReturn(10)
        assertThat(cmp, hasMaximum(10))
        try {
            assertThat(cmp, hasMaximum(50))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: <50>     but: was <10>'
        }

        // Minimum
        when(cmp.minimum()).thenReturn(10)
        assertThat(cmp, hasMinimum(10))
        try {
            assertThat(cmp, hasMinimum(50))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: <50>     but: was <10>'
        }

        // Step
        when(cmp.step()).thenReturn(10)
        assertThat(cmp, hasStep(10))
        try {
            assertThat(cmp, hasStep(50))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: <50>     but: was <10>'
        }

        // InRange
        when(cmp.inRange()).thenReturn(true)
        assertThat(cmp, inRange())
        try {
            when(cmp.inRange()).thenReturn(false)
            assertThat(cmp, inRange())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component is in range     but: is out of range'
        }

        // OutOfRange
        when(cmp.outOfRange()).thenReturn(true)
        assertThat(cmp, outOfRange())
        try {
            when(cmp.outOfRange()).thenReturn(false)
            assertThat(cmp, outOfRange())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component is out of range     but: is in range'
        }
    }

    @Test
    public void should_have_matchers_for_validity_support() {
        ValiditySupport cmp = mock(ValiditySupport)

        // Valid
        when(cmp.valid()).thenReturn(true)
        assertThat(cmp, valid())
        try {
            when(cmp.valid()).thenReturn(false)
            assertThat(cmp, valid())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component is valid     but: is invalid'
        }

        // Invalid
        when(cmp.invalid()).thenReturn(true)
        assertThat(cmp, invalid())
        try {
            when(cmp.invalid()).thenReturn(false)
            assertThat(cmp, invalid())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component is invalid     but: is valid'
        }
    }

    @Test
    public void should_have_matcher_for_text_support() {
        TextSupport cmp = mock(TextSupport)
        when(cmp.text()).thenReturn('MyText')

        assertThat(cmp, hasText('MyText'))
        try {
            assertThat(cmp, hasText('OtherText'))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: "OtherText"     but: was "MyText"'
        }
    }

    @Test
    public void should_have_matchers_for_select_support() {
        SelectSupport cmp = mock(SelectSupport)

        // Selected
        when(cmp.selected()).thenReturn(true)
        assertThat(cmp, selected())
        try {
            when(cmp.selected()).thenReturn(false)
            assertThat(cmp, selected())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component is selected     but: is unselected'
        }

        // Unselected
        when(cmp.unselected()).thenReturn(true)
        assertThat(cmp, unselected())
        try {
            when(cmp.unselected()).thenReturn(false)
            assertThat(cmp, unselected())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component is unselected     but: is selected'
        }
    }

    @Test
    public void should_have_matcher_for_value_support() {
        ValueSupport cmp = mock(ValueSupport)
        when(cmp.value()).thenReturn('MyValue')

        assertThat(cmp, Matchers.hasValue('MyValue'))
        try {
            assertThat(cmp, Matchers.hasValue('OtherValue'))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: "OtherValue"     but: was "MyValue"'
        }
    }

    @Test
    public void should_have_matcher_for_listbox_multiselectable() {
        MultiSelect cmp = mock(MultiSelect)

        // Multi Selectable
        when(cmp.multiSelectable()).thenReturn(true)
        assertThat(cmp, multiSelectable())
        try {
            when(cmp.multiSelectable()).thenReturn(false)
            assertThat(cmp, multiSelectable())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component is multi selectable     but: is single selectable'
        }
    }

    private static String message(AssertionError error) {
        error.message.replaceAll('[\n\r]', '')
    }
}
