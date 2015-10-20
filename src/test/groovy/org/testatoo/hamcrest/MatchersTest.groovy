/**
 * Copyright (C) 2014 Ovea (dev@ovea.com)
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
import org.testatoo.core.Component
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


/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class MatchersTest {

    @Test
    public void should_have_matchers_for_generic_support() {
        Component cmp = mock(Component)

        // Enabled
        when(cmp.enabled).thenReturn(true)
        assertThat(cmp, enabled())

        when(cmp.enabled).thenReturn(false)
        try {
            assertThat(cmp, enabled())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component enabled     but: disabled'
        }

        // Disabled
        when(cmp.disabled).thenReturn(true)
        assertThat(cmp, disabled())

        when(cmp.disabled).thenReturn(false)
        try {
            assertThat(cmp, disabled())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component disabled     but: enabled'
        }

        // Available
        when(cmp.available).thenReturn(true)
        assertThat(cmp, available())

        when(cmp.available).thenReturn(false)
        try {
            assertThat(cmp, available())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component available     but: missing'
        }

        // Missing
        when(cmp.missing).thenReturn(true)
        assertThat(cmp, missing())

        when(cmp.missing).thenReturn(false)
        try {
            assertThat(cmp, missing())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component missing     but: available'
        }

        // Visible
        when(cmp.visible).thenReturn(true)
        assertThat(cmp, visible())

        when(cmp.visible).thenReturn(false)
        try {
            assertThat(cmp, visible())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component visible     but: hidden'
        }

        // Hidden
        when(cmp.hidden).thenReturn(true)
        assertThat(cmp, hidden())

        when(cmp.hidden).thenReturn(false)
        try {
            assertThat(cmp, hidden())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component hidden     but: visible'
        }
    }

    @Test
    public void should_have_matchers_for_label_support() {
        LabelSupport cmp = mock(LabelSupport)
        when(cmp.label).thenReturn('MyLabel')

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
        when(cmp.placeholder).thenReturn('MyPlaceholder')
        assertThat(cmp, hasPlaceholder('MyPlaceholder'))
        try {
            assertThat(cmp, hasPlaceholder('OtherPlaceholder'))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: "OtherPlaceholder"     but: was "MyPlaceholder"'
        }

        // Empty
        when(cmp.empty).thenReturn(true)
        assertThat(cmp, empty())

        when(cmp.empty).thenReturn(false)
        try {
            assertThat(cmp, empty())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component empty     but: filled'
        }

        // Filled
        when(cmp.filled).thenReturn(true)
        assertThat(cmp, filled())

        when(cmp.filled).thenReturn(false)
        try {
            assertThat(cmp, filled())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component filled     but: empty'
        }

        // ReadOnly
        when(cmp.readOnly).thenReturn(true)
        assertThat(cmp, readOnly())

        when(cmp.readOnly).thenReturn(false)
        try {
            assertThat(cmp, readOnly())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component readOnly     but: not readOnly'
        }

        // Required
        when(cmp.required).thenReturn(true)
        assertThat(cmp, required())

        when(cmp.required).thenReturn(false)
        try {
            assertThat(cmp, required())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component required     but: optional'
        }

        // Optional
        when(cmp.optional).thenReturn(true)
        assertThat(cmp, optional())

        when(cmp.optional).thenReturn(false)
        try {
            assertThat(cmp, optional())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component optional     but: required'
        }
    }

    @Test
    public void should_have_matchers_for_check_support() {
        CheckSupport cmp = mock(CheckSupport)

        // Checked
        when(cmp.checked).thenReturn(true)
        assertThat(cmp, checked())

        when(cmp.checked).thenReturn(false)
        try {
            assertThat(cmp, checked())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component checked     but: unchecked'
        }

        // Unchecked
        when(cmp.unchecked).thenReturn(true)
        assertThat(cmp, unchecked())

        when(cmp.unchecked).thenReturn(false)
        try {
            assertThat(cmp, unchecked())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component unchecked     but: checked'
        }
    }

    @Test
    public void should_have_matchers_for_range_support() {
        RangeSupport cmp = mock(RangeSupport)

        // Maximum
        when(cmp.maximum).thenReturn(10)
        assertThat(cmp, hasMaximum(10))
        try {
            assertThat(cmp, hasMaximum(50))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: <50>     but: was <10>'
        }

        // Minimum
        when(cmp.minimum).thenReturn(10)
        assertThat(cmp, hasMinimum(10))
        try {
            assertThat(cmp, hasMinimum(50))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: <50>     but: was <10>'
        }

        // Step
        when(cmp.step).thenReturn(10)
        assertThat(cmp, hasStep(10))
        try {
            assertThat(cmp, hasStep(50))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: <50>     but: was <10>'
        }

        // InRange
        when(cmp.inRange).thenReturn(true)
        assertThat(cmp, inRange())
        try {
            when(cmp.inRange).thenReturn(false)
            assertThat(cmp, inRange())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component in range     but: out of range'
        }

        // OutOfRange
        when(cmp.outOfRange).thenReturn(true)
        assertThat(cmp, outOfRange())
        try {
            when(cmp.outOfRange).thenReturn(false)
            assertThat(cmp, outOfRange())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component out of range     but: in range'
        }
    }

    @Test
    public void should_have_matchers_for_validity_support() {
        ValiditySupport cmp = mock(ValiditySupport)

        // Valid
        when(cmp.valid).thenReturn(true)
        assertThat(cmp, valid())
        try {
            when(cmp.valid).thenReturn(false)
            assertThat(cmp, valid())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component valid     but: invalid'
        }

        // Invalid
        when(cmp.invalid).thenReturn(true)
        assertThat(cmp, invalid())
        try {
            when(cmp.invalid).thenReturn(false)
            assertThat(cmp, invalid())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component invalid     but: valid'
        }
    }

    @Test
    public void should_have_matchers_for_text_support() {
        TextSupport cmp = mock(TextSupport)
        when(cmp.text).thenReturn('MyText')

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
        when(cmp.selected).thenReturn(true)
        assertThat(cmp, selected())
        try {
            when(cmp.selected).thenReturn(false)
            assertThat(cmp, selected())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component selected     but: unselected'
        }

        // Unselected
        when(cmp.unselected).thenReturn(true)
        assertThat(cmp, unselected())
        try {
            when(cmp.unselected).thenReturn(false)
            assertThat(cmp, unselected())
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: Component unselected     but: selected'
        }
    }

    @Test
    public void should_have_matchers_for_value_support() {
        ValueSupport cmp = mock(ValueSupport)
        when(cmp.value).thenReturn('MyValue')

        assertThat(cmp, hasValue('MyValue'))
        try {
            assertThat(cmp, hasValue('OtherValue'))
            fail()
        } catch (AssertionError e) {
            assert message(e) == 'Expected: "OtherValue"     but: was "MyValue"'
        }
    }

    private static String message(AssertionError error) {
        error.message.replaceAll('[\n\r]', '')
    }
}
