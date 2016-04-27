package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.component.Item
import org.testatoo.core.support.ItemSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.core.Testatoo.*
import static org.testatoo.hamcrest.Matchers.has

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ItemMatcherTest {

    @Test
    public void should_have_expected_matcher() {
        ItemSupport cmp = mock(ItemSupport)

        Item item_1 = mock(Item)
        when(item_1.value()).thenReturn('item_1')
        Item item_2 = mock(Item)
        when(item_2.value()).thenReturn('item_2')
        Item item_3 = mock(Item)
        when(item_3.value()).thenReturn('item_3')

        when(cmp.items()).thenReturn([item_1, item_2])

        assertThat(cmp, has(items('item_1', 'item_2')))
        assertThat(cmp, has(items(item_1, item_2)))

        try {
            assertThat(cmp, has(items('item_1', 'item_3')))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: has item(s) [item_1, item_3]')
                    .appendText('\n     but: has item(s) [item_1, item_2]');

            assert e.message == description.toString()
        }

        try {
            assertThat(cmp, has(items(item_1, item_3)))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: has item(s) [item_1, item_3]')
                    .appendText('\n     but: has item(s) [item_1, item_2]');

            assert e.message == description.toString()
        }
    }
}