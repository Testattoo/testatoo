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
import org.testatoo.core.component.datagrid.Row
import org.testatoo.core.support.property.RowSupport
import org.testatoo.hamcrest.PropertyMatcher
import org.testatoo.hamcrest.matcher.property.dummy.DummyRow

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class RowMatcher extends PropertyMatcher<RowSupport> {
    private List<String> values = new ArrayList<>()
    private List<Row> rows = new ArrayList<>()

    RowMatcher(String... values) {
        this.values = values
    }

    RowMatcher(Row... rows) {
        this.rows = rows
    }

    @Override
    protected boolean matchesSafely(RowSupport component) {
        if (values) {
            rows.clear()
            values.each { rows.add(new DummyRow(it)) }
        }
        values.clear()
        rows.each { values.add(String.valueOf(it.title())) }
        component.rows().size() == rows.size() && component.rows().containsAll(rows)
    }

    @Override
    void describeTo(Description description) {
        List<String> expectedRows = new ArrayList<>()
        rows.each { expectedRows.add(String.valueOf(it.title())) }

        description.appendText('row(s) ')
        description.appendValueList('[', ', ', ']', expectedRows)
    }

    @Override
    protected void describeMismatchSafely(RowSupport component, Description mismatchDescription) {
        List<String> componentRows = new ArrayList<>()
        component.rows().each { componentRows.add(String.valueOf(it.title())) }

        mismatchDescription.appendText('has row(s) ')
        mismatchDescription.appendValueList('[', ', ', ']', componentRows)
    }
}
