package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.support.property.ReferenceSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.has
import static org.testatoo.hamcrest.Matchers.reference

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ReferenceMatcherTest {
    @Test
    public void should_have_expected_matcher() {
        ReferenceSupport cmp = mock(ReferenceSupport)

        when(cmp.reference()).thenReturn('my-reference')
        assertThat(cmp, has(reference('my-reference')))

        try {
            assertThat(cmp, has(reference('other-reference')))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: has reference "other-reference"')
                    .appendText('\n     but: has reference "my-reference"');

            assert e.message == description.toString()
        }
    }
}
