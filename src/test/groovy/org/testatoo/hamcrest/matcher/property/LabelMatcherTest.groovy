package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.support.LabelSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.has
import static org.testatoo.hamcrest.Matchers.label

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class LabelMatcherTest {

    @Test
    public void should_have_expected_matcher() {
        LabelSupport cmp = mock(LabelSupport)
        when(cmp.label()).thenReturn('MyLabel')

        assertThat(cmp, has(label('MyLabel')))
        try {
            assertThat(cmp, has(label('OtherLabel')))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: has "OtherLabel"')
                    .appendText('\n     but: has "MyLabel"');

            assert e.message == description.toString()
        }
    }
}
