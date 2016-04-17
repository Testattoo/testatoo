package org.testatoo.hamcrest.matcher.state

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.component.Component

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.enabled

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class EnabledMatcherTest {

    @Test
    public void should_have_expected_matcher() {
        Component cmp = mock(Component)

        when(cmp.enabled()).thenReturn(true)
        assertThat(cmp, is(enabled()))

        when(cmp.enabled()).thenReturn(false)
        try {
            assertThat(cmp, is(enabled()))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: is enabled')
                    .appendText('\n     but: is disabled');

            assert e.message == description.toString()
        }
    }
}
