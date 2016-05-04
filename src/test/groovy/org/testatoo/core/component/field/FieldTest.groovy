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
package org.testatoo.core.component.field

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.component.Component
import org.testatoo.core.support.property.InputSupport
import org.testatoo.core.support.property.LabelSupport
import org.testatoo.core.support.property.LengthSupport
import org.testatoo.core.support.property.ValueSupport
import org.testatoo.core.support.state.RangeSupport
import org.testatoo.core.support.state.ValiditySupport

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class FieldTest {
    @Test
    public void should_have_expected_inheritance() {
        assert Field in Component
        assert Field in LabelSupport
        assert Field in InputSupport
        assert Field in ValueSupport
        assert Field in ValiditySupport

        assert ColorField in Field

        assert DateField in Field
        assert DateField in RangeSupport

        assert DateTimeField in Field

        assert EmailField in Field

        assert MonthField in Field

        assert NumberField in Field
        assert NumberField in RangeSupport

        assert PasswordField in Field
        assert PasswordField in LengthSupport

        assert TextField in Field
        assert TextField in LengthSupport

        assert PhoneField in Field

        assert RangeField in Field
        assert RangeField in RangeSupport

        assert SearchField in Field
        assert SearchField in LengthSupport

        assert TimeField in Field

        assert URLField in Field
        assert URLField in LengthSupport

        assert WeekField in Field
    }
}
