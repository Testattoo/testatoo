package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.support.ValueSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.has
import static org.testatoo.hamcrest.Matchers.value

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ValueMatcherTest {

    @Test
    public void should_have_expected_matcher() {
        ValueSupport cmp = mock(ValueSupport)
        when(cmp.value()).thenReturn('MyValue')

        assertThat(cmp, has(value('MyValue')))
        try {
            assertThat(cmp, has(value('OtherValue')))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: has "OtherValue"')
                    .appendText('\n     but: has "MyValue"');

            assert e.message == description.toString()
        }
    }
}
