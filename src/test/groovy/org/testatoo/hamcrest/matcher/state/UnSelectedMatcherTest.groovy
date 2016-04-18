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
import static org.testatoo.hamcrest.Matchers.unselected

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class UnSelectedMatcherTest {

    @Test
    public void should_have_expected_matcher() {
        SelectSupport cmp = mock(SelectSupport)

        when(cmp.unselected()).thenReturn(true)
        assertThat(cmp, is(unselected()))
        try {
            when(cmp.unselected()).thenReturn(false)
            assertThat(cmp, is(unselected()))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: is unselected')
                    .appendText('\n     but: is selected');

            assert e.message == description.toString()
        }
    }
}
