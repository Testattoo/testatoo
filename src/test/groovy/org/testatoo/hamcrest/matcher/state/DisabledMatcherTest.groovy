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
import static org.testatoo.hamcrest.Matchers.disabled

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class DisabledMatcherTest {

    @Test
    public void should_have_expected_matcher() {
        Component cmp = mock(Component)

        when(cmp.disabled()).thenReturn(true)
        assertThat(cmp, is(disabled()))

        when(cmp.disabled()).thenReturn(false)
        try {
            assertThat(cmp, is(disabled()))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: is disabled')
                    .appendText('\n     but: is enabled');

            assert e.message == description.toString()
        }
    }
}
