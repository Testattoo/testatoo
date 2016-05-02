package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.support.property.TitleSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.has
import static org.testatoo.hamcrest.Matchers.title

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class TitleMatcherTest {
    @Test
    public void should_have_expected_matcher() {
        TitleSupport cmp = mock(TitleSupport)
        when(cmp.title()).thenReturn('MyTitle')

        assertThat(cmp, has(title('MyTitle')))
        try {
            assertThat(cmp, has(title('OtherTitle')))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: has "OtherTitle"')
                    .appendText('\n     but: has "MyTitle"');

            assert e.message == description.toString()
        }
    }
}
