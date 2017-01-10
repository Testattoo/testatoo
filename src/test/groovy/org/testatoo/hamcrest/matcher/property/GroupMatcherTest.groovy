/**
 * Copyright © 2016 Ovea (d.avenante@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.experimental.categories.Category
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.category.NoGui
import org.testatoo.core.component.Group
import org.testatoo.core.support.property.GroupSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.groups
import static org.testatoo.hamcrest.Matchers.has

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
@Category(NoGui)
class GroupMatcherTest {
    @Test
    void should_have_expected_matcher() {
        GroupSupport cmp = mock(GroupSupport)

        Group group_1 = mock(Group)
        when(group_1.value()).thenReturn('group_1')
        Group group_2 = mock(Group)
        when(group_2.value()).thenReturn('group_2')
        Group group_3 = mock(Group)
        when(group_3.value()).thenReturn('group_3')

        when(cmp.groups()).thenReturn([group_1, group_2])

        assertThat(cmp, has(groups('group_1', 'group_2')))
        assertThat(cmp, has(groups(group_1, group_2)))

        try {
            assertThat(cmp, has(groups('group_1', 'group_3')))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription()
            description.appendText('\nExpected: has group(s) ["group_1", "group_3"]')
                    .appendText('\n     but: has group(s) ["group_1", "group_2"]')

            assert e.message == description.toString()
        }

        try {
            assertThat(cmp, has(groups(group_1, group_3)))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription()
            description.appendText('\nExpected: has group(s) ["group_1", "group_3"]')
                    .appendText('\n     but: has group(s) ["group_1", "group_2"]')

            assert e.message == description.toString()
        }
    }
}