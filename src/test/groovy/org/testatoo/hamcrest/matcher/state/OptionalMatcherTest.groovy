package org.testatoo.hamcrest.matcher.state

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.support.OptionalSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.hamcrest.Matchers.is
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.optional
import static org.testatoo.hamcrest.Matchers.optional

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class OptionalMatcherTest {

    @Test
    public void should_have_expected_matcher() {
        OptionalSupport cmp = mock(OptionalSupport)

        when(cmp.optional()).thenReturn(true)
        assertThat(cmp, is(optional()))

        when(cmp.optional()).thenReturn(false)
        try {
            assertThat(cmp, is(optional()))
            fail()
        } catch (AssertionError e) {

            Description description = new StringDescription();
            description.appendText('\nExpected: is optional')
                    .appendText('\n     but: is required');

            assert e.message == description.toString()
        }
    }
}
