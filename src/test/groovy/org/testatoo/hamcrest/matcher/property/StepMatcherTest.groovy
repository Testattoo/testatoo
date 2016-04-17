package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.support.StepSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.has
import static org.testatoo.hamcrest.Matchers.step

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class StepMatcherTest {

    @Test
    public void should_have_expected_matcher() {
        StepSupport cmp = mock(StepSupport)

        when(cmp.step()).thenReturn(10)
        assertThat(cmp, has(step(10)))

        try {
            assertThat(cmp, has(step(50)))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: has 50')
                    .appendText('\n     but: has 10');

            assert e.message == description.toString()
        }
    }
}
