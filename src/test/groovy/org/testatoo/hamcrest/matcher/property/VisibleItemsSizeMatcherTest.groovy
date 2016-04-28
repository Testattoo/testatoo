package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.component.Item
import org.testatoo.core.support.ItemSupport
import org.testatoo.core.support.VisibleItemsSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.has

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class VisibleItemsSizeMatcherTest {

    @Test
    public void should_have_expected_matcher() {
        VisibleItemsSupport cmp = mock(VisibleItemsSupport)

        when(cmp.visibleItems()).thenReturn([mock(Item), mock(Item)])

        assertThat(cmp, has(2.visibleItems))
        try {
            assertThat(cmp, has(3.visibleItems))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: has 3 visible item(s)')
                    .appendText('\n     but: has 2 visible item(s)');

            assert e.message == description.toString()
        }
    }
}
