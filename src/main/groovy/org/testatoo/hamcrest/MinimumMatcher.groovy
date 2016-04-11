/**
 * Copyright (C) 2016 Ovea (dev@ovea.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.hamcrest

import org.hamcrest.Description
import org.testatoo.core.support.RangeSupport

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class MinimumMatcher extends PropertyMatcher<RangeSupport> {

    private Object minimum

    MinimumMatcher(Object minimum) {
        this.minimum = minimum
    }

    @Override
    protected boolean matchesSafely(RangeSupport item) {
        item.minimum() == minimum
    }

    @Override
    void describeTo(Description description) {
        description.appendValue(minimum)
    }

    @Override
    protected void describeMismatchSafely(RangeSupport item, Description mismatchDescription) {
        mismatchDescription.appendText('has ').appendValue(item.minimum())
    }
}
