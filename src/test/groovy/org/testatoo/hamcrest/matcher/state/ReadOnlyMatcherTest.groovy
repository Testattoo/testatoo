/**
 * Copyright © 2018 Ovea (d.avenante@gmail.com)
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
package org.testatoo.hamcrest.matcher.state

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.support.state.ReadOnlySupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.readOnly

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ReadOnlyMatcherTest {
    @Test
    void should_have_expected_matcher() {
        ReadOnlySupport cmp = mock(ReadOnlySupport)

        when(cmp.readOnly()).thenReturn(true)
        assertThat(cmp, is(readOnly()))

        when(cmp.readOnly()).thenReturn(false)
        try {
            assertThat(cmp, is(readOnly()))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription()
            description.appendText('\nExpected: is read only')
                    .appendText('\n     but: is not read only')

            assert e.message == description.toString()
        }
    }
}