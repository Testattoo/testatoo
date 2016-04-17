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
import static org.testatoo.hamcrest.Matchers.missing

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class MissingMatcherTest {

    @Test
    public void should_have_expected_matcher() {
        Component cmp = mock(Component)

        when(cmp.missing()).thenReturn(true)
        assertThat(cmp, is(missing()))

        when(cmp.missing()).thenReturn(false)
        try {
            assertThat(cmp, is(missing()))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: is missing')
                    .appendText('\n     but: is available');

            assert e.message == description.toString()
        }
    }
}
