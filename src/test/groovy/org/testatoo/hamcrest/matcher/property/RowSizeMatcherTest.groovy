package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.component.datagrid.Row
import org.testatoo.core.support.property.RowSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.has

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class RowSizeMatcherTest {
    @Test
    public void should_have_expected_matcher() {
        RowSupport cmp = mock(RowSupport)

        when(cmp.rows()).thenReturn([mock(Row), mock(Row)])

        assertThat(cmp, has(2.rows))
        try {
            assertThat(cmp, has(3.rows))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: has 3 row(s)')
                    .appendText('\n     but: has 2 row(s)');

            assert e.message == description.toString()
        }
    }
}
