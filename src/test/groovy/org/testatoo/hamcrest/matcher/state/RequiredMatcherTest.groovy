package org.testatoo.hamcrest.matcher.state

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.support.RequiredSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.required

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class RequiredMatcherTest {

    @Test
    public void should_have_expected_matcher() {
        RequiredSupport cmp = mock(RequiredSupport)

        when(cmp.required()).thenReturn(true)
        assertThat(cmp, is(required()))

        when(cmp.required()).thenReturn(false)
        try {
            assertThat(cmp, is(required()))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: is required')
                    .appendText('\n     but: is optional');

            assert e.message == description.toString()
        }
    }
}