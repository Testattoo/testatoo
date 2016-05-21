package org.testatoo.hamcrest.matcher.state

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.support.state.FocusSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.focused

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class FocusedMatcherTest {
    @Test
    public void should_have_expected_matcher() {
        FocusSupport cmp = mock(FocusSupport)

        when(cmp.focused()).thenReturn(true)
        assertThat(cmp, is(focused()))

        when(cmp.focused()).thenReturn(false)
        try {
            assertThat(cmp, is(focused()))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: is focused')
                    .appendText('\n     but: is not focused');

            assert e.message == description.toString()
        }
    }
}
