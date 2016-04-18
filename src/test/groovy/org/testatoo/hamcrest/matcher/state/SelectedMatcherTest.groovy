package org.testatoo.hamcrest.matcher.state

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.support.SelectSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.selected

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class SelectedMatcherTest {

    @Test
    public void should_have_expected_matcher() {
        SelectSupport cmp = mock(SelectSupport)

        // Selected
        when(cmp.selected()).thenReturn(true)
        assertThat(cmp, is(selected()))
        try {
            when(cmp.selected()).thenReturn(false)
            assertThat(cmp, is(selected()))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: is selected')
                    .appendText('\n     but: is unselected');

            assert e.message == description.toString()
        }
    }
}
