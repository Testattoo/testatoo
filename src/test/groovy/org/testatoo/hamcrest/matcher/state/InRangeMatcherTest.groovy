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
import static org.testatoo.hamcrest.Matchers.inRange

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class InRangeMatcherTest {

    @Test
    public void should_have_expected_matcher() {
        RangeSupport cmp = mock(RangeSupport)

        when(cmp.inRange()).thenReturn(true)
        assertThat(cmp, is(inRange()))
        try {
            when(cmp.inRange()).thenReturn(false)
            assertThat(cmp, is(inRange()))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: is in range')
                    .appendText('\n     but: is out of range');

            assert e.message == description.toString()
        }
    }
}
