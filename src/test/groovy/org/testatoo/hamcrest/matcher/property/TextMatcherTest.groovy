package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.support.TextSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.has
import static org.testatoo.hamcrest.Matchers.text

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class TextMatcherTest {

    @Test
    public void should_have_expected_matcher() {
        TextSupport cmp = mock(TextSupport)
        when(cmp.text()).thenReturn('MyText')

        assertThat(cmp, has(text('MyText')))
        try {
            assertThat(cmp, has(text('OtherText')))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: has "OtherText"')
                    .appendText('\n     but: has "MyText"');

            assert e.message == description.toString()
        }
    }
}
