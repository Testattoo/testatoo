package org.testatoo.hamcrest.matcher.state

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.support.ValiditySupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.valid

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ValidMatcherTest {

    @Test
    public void should_have_expected_matcher() {
        ValiditySupport cmp = mock(ValiditySupport)

        // Valid
        when(cmp.valid()).thenReturn(true)
        assertThat(cmp, is(valid()))
        try {
            when(cmp.valid()).thenReturn(false)
            assertThat(cmp, is(valid()))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: is valid')
                    .appendText('\n     but: is invalid');

            assert e.message == description.toString()
        }
    }
}
