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
        Field in Component
        Field in LabelSupport
        Field in InputSupport
        Field in ValueSupport
        Field in ValiditySupport

        ColorField in Field

        DateField in Field
        DateField in RangeSupport

        DateTimeField in Field

        EmailField in Field

        MonthField in Field

        NumberField in Field
        NumberField in RangeSupport

        PasswordField in Field

        TextField in Field

        PhoneField in Field

        RangeField in Field
        RangeField in RangeSupport

        SearchField in Field

        TimeField in Field

        URLField in Field

        WeekField in Field
    }
}
