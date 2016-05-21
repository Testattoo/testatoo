package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.support.state.FocusSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class FocusMatcherTest {
    @Test
    public void should_have_expected_matcher() {
        FocusSupport cmp = mock(FocusSupport)

        when(cmp.focused()).thenReturn(true)
        assertThat(cmp, has(focus()))
        try {
            when(cmp.focused()).thenReturn(false)
            assertThat(cmp, has(focus()))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: has focus')
                    .appendText('\n     but: has no focus');

            assert e.message == description.toString()
        }
    }
}
