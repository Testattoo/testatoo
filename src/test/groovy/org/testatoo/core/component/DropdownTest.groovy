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
package org.testatoo.core.component

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.support.SingleSelectable
import org.testatoo.core.support.property.GroupSupport
import org.testatoo.core.support.property.ItemSupport
import org.testatoo.core.support.property.LabelSupport
import org.testatoo.core.support.property.SelectedItemSupport

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class DropdownTest {
    @Test
    void should_have_expected_inheritance() {
        assert Dropdown in Component
        assert Dropdown in ItemSupport
        assert Dropdown in GroupSupport
        assert Dropdown in SingleSelectable
        assert Dropdown in LabelSupport
        assert Dropdown in SelectedItemSupport
    }
}