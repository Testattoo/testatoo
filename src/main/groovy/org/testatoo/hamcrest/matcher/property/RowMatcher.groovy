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

        description.appendText('row(s) [')
        description.appendText(expectedRows.join(', '))
        description.appendText(']')
    }

    @Override
    protected void describeMismatchSafely(RowSupport component, Description mismatchDescription) {
        List<String> componentRows = new ArrayList<>()
        component.rows().each { componentRows.add(String.valueOf(it.title())) }

        mismatchDescription.appendText('has row(s) [')
        mismatchDescription.appendText(componentRows.join(', '))
        mismatchDescription.appendText(']')
    }
}
