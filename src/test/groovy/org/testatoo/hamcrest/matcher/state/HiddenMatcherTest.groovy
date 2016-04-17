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
import static org.testatoo.hamcrest.Matchers.hidden

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class HiddenMatcherTest {

    @Test
    public void should_have_expected_matcher() {
        Component cmp = mock(Component)

        when(cmp.hidden()).thenReturn(true)
        assertThat(cmp, is(hidden()))

        when(cmp.hidden()).thenReturn(false)
        try {
            assertThat(cmp, is(hidden()))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: is hidden')
                    .appendText('\n     but: is visible');

            assert e.message == description.toString()
        }
    }
}
