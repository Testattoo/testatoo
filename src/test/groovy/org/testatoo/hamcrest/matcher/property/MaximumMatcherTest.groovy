package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.support.MaximumSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.has
import static org.testatoo.hamcrest.Matchers.maximum

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class MaximumMatcherTest {

    @Test
    public void should_have_expected_matcher() {
        MaximumSupport cmp = mock(MaximumSupport)

        when(cmp.maximum()).thenReturn(10)
        assertThat(cmp, has(maximum(10)))
        try {
            assertThat(cmp, has(maximum(50)))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: has 50')
                    .appendText('\n     but: has 10');

            assert e.message == description.toString()
        }
    }
}