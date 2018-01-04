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
package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.testatoo.core.component.Item
import org.testatoo.core.support.property.SelectedItemSupport
import org.testatoo.hamcrest.PropertyMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class SelectedItemMatcher extends PropertyMatcher<SelectedItemSupport> {
    private String value
    private Item item

    SelectedItemMatcher(String value) {
        this.value = value
    }

    SelectedItemMatcher(Item item) {
        this.item = item
    }

    @Override
    protected boolean matchesSafely(SelectedItemSupport component) {
        if(value) {
            return component.selectedItem().value() == value
        }
        component.selectedItem() == item
    }

    @Override
    void describeTo(Description description) {
        description.appendText('selected item ').appendValue(value ? value : item.value())
    }

    @Override
    protected void describeMismatchSafely(SelectedItemSupport component, Description mismatchDescription) {
        mismatchDescription.appendText('has selected item ').appendValue(component.selectedItem().value())
    }
}
