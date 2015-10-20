/**
 * Copyright (C) 2014 Ovea (dev@ovea.com)
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
import org.hamcrest.TypeSafeMatcher
import org.testatoo.core.support.RangeSupport

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class StepMatcher extends TypeSafeMatcher<RangeSupport> {

    private Object step

    StepMatcher(Object step) {
        this.step = step
    }

    @Override
    protected boolean matchesSafely(RangeSupport item) {
        item.step == step
    }

    @Override
    void describeTo(Description description) {
        description.appendValue(step)
    }

    @Override
    protected void describeMismatchSafely(RangeSupport item, Description mismatchDescription) {
        mismatchDescription.appendText('was ').appendValue(item.step)
    }
}
