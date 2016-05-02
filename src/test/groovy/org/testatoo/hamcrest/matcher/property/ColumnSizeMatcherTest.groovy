package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.component.datagrid.Column
import org.testatoo.core.support.property.ColumnSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.has

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ColumnSizeMatcherTest {
    @Test
    public void should_have_expected_matcher() {
        ColumnSupport cmp = mock(ColumnSupport)

        when(cmp.columns()).thenReturn([mock(Column), mock(Column)])

        assertThat(cmp, has(2.columns))
        try {
            assertThat(cmp, has(3.columns))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: has 3 column(s)')
                    .appendText('\n     but: has 2 column(s)');

            assert e.message == description.toString()
        }
    }
}
