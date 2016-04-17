package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.support.InputSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.has
import static org.testatoo.hamcrest.Matchers.placeholder

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class PlaceholderMatcherTest {

    @Test
    public void should_have_expected_matcher() {
        InputSupport cmp = mock(InputSupport)

        when(cmp.placeholder()).thenReturn('MyPlaceholder')
        assertThat(cmp, has(placeholder('MyPlaceholder')))
        try {
            assertThat(cmp, has(placeholder('OtherPlaceholder')))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: has "OtherPlaceholder"')
                    .appendText('\n     but: has "MyPlaceholder"');

            assert e.message == description.toString()
        }
    }
}
