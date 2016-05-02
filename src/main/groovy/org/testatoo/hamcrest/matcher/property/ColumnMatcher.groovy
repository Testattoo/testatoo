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

        description.appendText('column(s) [')
        description.appendText(expectedColumns.join(', '))
        description.appendText(']')
    }

    @Override
    protected void describeMismatchSafely(ColumnSupport component, Description mismatchDescription) {
        List<String> componentColumns = new ArrayList<>()
        component.columns().each { componentColumns.add(String.valueOf(it.title())) }

        mismatchDescription.appendText('has column(s) [')
        mismatchDescription.appendText(componentColumns.join(', '))
        mismatchDescription.appendText(']')
    }
}
