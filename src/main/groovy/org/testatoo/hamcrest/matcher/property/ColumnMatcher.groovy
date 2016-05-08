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
package org.testatoo.hamcrest.matcher.property

import org.hamcrest.Description
import org.testatoo.core.component.datagrid.Column
import org.testatoo.core.support.property.ColumnSupport
import org.testatoo.hamcrest.PropertyMatcher
import org.testatoo.hamcrest.matcher.property.dummy.DummyColumn

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class ColumnMatcher extends PropertyMatcher<ColumnSupport> {
    private List<String> values = new ArrayList<>()
    private List<Column> columns = new ArrayList<>()

    ColumnMatcher(String... values) {
        this.values = values
    }

    ColumnMatcher(Column... columns) {
        this.columns = columns
    }

    @Override
    protected boolean matchesSafely(ColumnSupport component) {
        if (values) {
            columns.clear()
            values.each { columns.add(new DummyColumn(it)) }
        }
        values.clear()
        columns.each { values.add(String.valueOf(it.title())) }
        component.columns().size() == columns.size() && component.columns().containsAll(columns)
    }

    @Override
    void describeTo(Description description) {
        List<String> expectedColumns = new ArrayList<>()
        columns.each { expectedColumns.add(String.valueOf(it.title())) }

        description.appendText('column(s) ')
        description.appendValueList('[', ', ', ']', expectedColumns)
    }

    @Override
    protected void describeMismatchSafely(ColumnSupport component, Description mismatchDescription) {
        List<String> componentColumns = new ArrayList<>()
        component.columns().each { componentColumns.add(String.valueOf(it.title())) }

        mismatchDescription.appendText('has column(s) ')
        mismatchDescription.appendValueList('[', ', ', ']', componentColumns)
    }
}
