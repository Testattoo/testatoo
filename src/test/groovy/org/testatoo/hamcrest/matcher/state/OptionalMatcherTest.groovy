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
import org.testatoo.core.support.state.RequiredSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.optional

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class OptionalMatcherTest {
    @Test
    void should_have_expected_matcher() {
        RequiredSupport cmp = mock(RequiredSupport)

        when(cmp.required()).thenReturn(false)
        assertThat(cmp, is(optional()))

        when(cmp.required()).thenReturn(true)
        try {
            assertThat(cmp, is(optional()))
            fail()
        } catch (AssertionError e) {

            Description description = new StringDescription()
            description.appendText('\nExpected: is optional')
                .appendText('\n     but: is required')

            assert e.message == description.toString()
        }
    }
}
