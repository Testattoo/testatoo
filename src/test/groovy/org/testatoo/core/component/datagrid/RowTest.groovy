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
package org.testatoo.core.component.datagrid

import org.junit.Test
import org.junit.experimental.categories.Category
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.category.NoGui
import org.testatoo.core.component.Component
import org.testatoo.core.support.property.CellSupport
import org.testatoo.core.support.property.TitleSupport

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
@Category(NoGui)
class RowTest {
    @Test
    public void should_have_expected_inheritance() {
        assert Row in Component
        assert Row in TitleSupport
        assert Row in CellSupport
    }

    @Test
    public void should_have_equality_and_hashcode_based_on_title() {
        Row row_1 = new TestRow('title_1')
        Row row_2 = new TestRow('title_2')
        Row row_3 = new TestRow('title_1')

        assert !row_1.equals(row_2)
        assert row_1.equals(row_3)

        assert row_1.hashCode() == 'title_1'.hashCode()
    }

    private class TestRow extends Row {
        private String title;

        public TestRow(String title) {
            this.title = title
        }

        List<Cell> cells() {
            return null
        }

        Cell cell(Object value) {
            return null
        }

        String title() {
            return title
        }
    }
}
