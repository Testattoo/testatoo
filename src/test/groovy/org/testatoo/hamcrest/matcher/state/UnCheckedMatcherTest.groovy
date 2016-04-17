package org.testatoo.hamcrest.matcher.state

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.support.CheckSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.unchecked

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class UnCheckedMatcherTest {

    @Test
    public void should_have_expected_matcher() {
        CheckSupport cmp = mock(CheckSupport)

        when(cmp.unchecked()).thenReturn(true)
        assertThat(cmp, is(unchecked()))

        when(cmp.unchecked()).thenReturn(false)
        try {
            assertThat(cmp, is(unchecked()))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: is unchecked')
                    .appendText('\n     but: is checked');

            assert e.message == description.toString()
        }
    }
}
