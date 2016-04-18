package org.testatoo.hamcrest.matcher.state

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.support.RangeSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.outOfRange

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class OutOfRangeMatcherTest {

    @Test
    public void should_have_expected_matcher() {
        RangeSupport cmp = mock(RangeSupport)

        when(cmp.outOfRange()).thenReturn(true)
        assertThat(cmp, is(outOfRange()))
        try {
            when(cmp.outOfRange()).thenReturn(false)
            assertThat(cmp, is(outOfRange()))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: is out of range')
                    .appendText('\n     but: is in range');

            assert e.message == description.toString()
        }
    }
}
