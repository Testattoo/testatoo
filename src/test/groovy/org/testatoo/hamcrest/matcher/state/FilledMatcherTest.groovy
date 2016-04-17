package org.testatoo.hamcrest.matcher.state

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.support.FilledSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.filled

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class FilledMatcherTest {

    @Test
    public void should_have_expected_matcher() {
        FilledSupport cmp = mock(FilledSupport)

        when(cmp.filled()).thenReturn(true)
        assertThat(cmp, is(filled()))

        when(cmp.filled()).thenReturn(false)
        try {
            assertThat(cmp, is(filled()))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: is filled')
                    .appendText('\n     but: is empty');

            assert e.message == description.toString()
        }
    }
}
