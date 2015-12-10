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
package org.testatoo.core.component

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.Component
import org.testatoo.core.component.datagrid.Cell
import org.testatoo.core.component.datagrid.Column
import org.testatoo.core.component.datagrid.DataGrid
import org.testatoo.core.component.datagrid.Row
import org.testatoo.core.component.field.ColorField
import org.testatoo.core.component.field.DateField
import org.testatoo.core.component.field.DateTimeField
import org.testatoo.core.component.field.EmailField
import org.testatoo.core.component.field.Field
import org.testatoo.core.component.field.MonthField
import org.testatoo.core.component.field.NumberField
import org.testatoo.core.component.field.PasswordField
import org.testatoo.core.component.field.PhoneField
import org.testatoo.core.component.field.RangeField
import org.testatoo.core.component.field.SearchField
import org.testatoo.core.component.field.TextField
import org.testatoo.core.component.field.TimeField
import org.testatoo.core.component.field.URLField
import org.testatoo.core.component.field.WeekField
import org.testatoo.core.support.CheckSupport
import org.testatoo.core.support.Checkable
import org.testatoo.core.support.InputSupport
import org.testatoo.core.support.LabelSupport
import org.testatoo.core.support.MultiSelector
import org.testatoo.core.support.RangeSupport
import org.testatoo.core.support.Resettable
import org.testatoo.core.support.SelectSupport
import org.testatoo.core.support.SingleSelector
import org.testatoo.core.support.Submissible
import org.testatoo.core.support.TextSupport
import org.testatoo.core.support.UnCheckable
import org.testatoo.core.support.ValiditySupport
import org.testatoo.core.support.ValueSupport

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ComponentTest {

    @Test
    public void should_have_expected_inheritance() {
        Button in Component
        Button in TextSupport

        CheckBox in Component
        CheckBox in CheckSupport
        CheckBox in LabelSupport
        CheckBox in Checkable
        CheckBox in UnCheckable

        Dropdown in Component
        Dropdown in SingleSelector

        Form in Component
        Form in ValiditySupport
        Form in Resettable
        Form in Submissible

        GroupItem in Component

        Heading in Component
        Heading in TextSupport

        Image in Component

        Item in Component
        Item in SelectSupport
        Item in ValueSupport

        Link in Component
        Link in TextSupport

        ListBox in Component
        ListBox in MultiSelector

        ListView in Component

        Panel in Component

        Radio in Component
        Radio in LabelSupport
        Radio in CheckSupport
        Radio in Checkable

        DataGrid in Component
        Row in Component
        Column in Component
        Cell in Component

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
