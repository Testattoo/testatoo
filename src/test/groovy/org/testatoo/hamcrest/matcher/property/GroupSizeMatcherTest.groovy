package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.component.Group
import org.testatoo.core.support.GroupSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.has

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class GroupSizeMatcherTest {

    @Test
    public void should_have_expected_matcher() {
        GroupSupport cmp = mock(GroupSupport)

        when(cmp.groups()).thenReturn([mock(Group), mock(Group)])

        assertThat(cmp, has(2.groups))
        try {
            assertThat(cmp, has(3.groups))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: has 3 group(s)')
                    .appendText('\n     but: has 2 group(s)');

            assert e.message == description.toString()
        }
    }
}
