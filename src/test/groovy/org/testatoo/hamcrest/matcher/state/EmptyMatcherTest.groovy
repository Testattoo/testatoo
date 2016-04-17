package org.testatoo.hamcrest.matcher.state

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.support.EmptySupport
import org.testatoo.hamcrest.Matchers

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class EmptyMatcherTest {

    @Test
    public void should_have_expected_matcher() {
        EmptySupport cmp = mock(EmptySupport)

        when(cmp.empty()).thenReturn(true)
        assertThat(cmp, is(Matchers.empty()))

        when(cmp.empty()).thenReturn(false)
        try {
            assertThat(cmp, is(Matchers.empty()))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: is empty')
                    .appendText('\n     but: is filled');

            assert e.message == description.toString()
        }
    }
}
