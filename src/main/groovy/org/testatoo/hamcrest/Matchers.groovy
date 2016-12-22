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
package org.testatoo.hamcrest

import org.hamcrest.Factory
import org.hamcrest.Matcher
import org.testatoo.core.Testatoo
import org.testatoo.core.component.Component
import org.testatoo.core.component.Group
import org.testatoo.core.component.Item
import org.testatoo.core.component.datagrid.Cell
import org.testatoo.core.component.datagrid.Column
import org.testatoo.core.component.datagrid.Row
import org.testatoo.core.support.property.*
import org.testatoo.core.support.state.*
import org.testatoo.hamcrest.matcher.property.*
import org.testatoo.hamcrest.matcher.state.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Matchers {
    @Factory
    static <T> Matcher<T> has(Matcher<T> matcher) { new Has(matcher) }

    // States
    @Factory
    static Matcher<Component> enabled() { new EnabledMatcher() }

    @Factory
    static Matcher<Component> disabled() { new DisabledMatcher() }

    @Factory
    static Matcher<Component> available() { new AvailableMatcher() }

    @Factory
    static Matcher<Component> missing() { new MissingMatcher() }

    @Factory
    static Matcher<Component> visible() { new VisibleMatcher() }

    @Factory
    static Matcher<Component> hidden() { new HiddenMatcher() }

    @Factory
    static Matcher<EmptySupport> empty() { new EmptyMatcher() }

    @Factory
    static Matcher<EmptySupport> filled() { new FilledMatcher() }

    @Factory
    static Matcher<FocusSupport> focused() { new FocusedMatcher() }

    @Factory
    static Matcher<ReadOnlySupport> readOnly() { new ReadOnlyMatcher() }

    @Factory
    static Matcher<RequiredSupport> required() { new RequiredMatcher() }

    @Factory
    static Matcher<RequiredSupport> optional() { new OptionalMatcher() }

    @Factory
    static Matcher<CheckSupport> checked() { new CheckedMatcher() }

    @Factory
    static Matcher<CheckSupport> unchecked() { new UnCheckedMatcher() }

    @Factory
    static Matcher<SelectSupport> selected() { new SelectedMatcher() }

    @Factory
    static Matcher<SelectSupport> unselected() { new UnSelectedMatcher() }

    @Factory
    static Matcher<RangeSupport> inRange() { new InRangeMatcher() }

    @Factory
    static Matcher<RangeSupport> outOfRange() { new OutOfRangeMatcher() }

    @Factory
    static Matcher<ValiditySupport> valid() { new ValidMatcher() }

    @Factory
    static Matcher<ValiditySupport> invalid() { new InvalidMatcher() }

    @Factory
    static Matcher<Component> contain(Component... components) { new ContainMatcher(Testatoo.config.evaluator, components)  }

    // Properties
    @Factory
    static Matcher<LabelSupport> label(String label) { new LabelMatcher(label) }

    @Factory
    static Matcher<InputSupport> placeholder(String placeholder) { new PlaceholderMatcher(placeholder) }

    @Factory
    static Matcher<MaximumSupport> maximum(Object maximum) { new MaximumMatcher(maximum) }

    @Factory
    static Matcher<MinimumSupport> minimum(Object minimum) { new MinimumMatcher(minimum) }

    @Factory
    static Matcher<LengthSupport> length(Object length) { new LengthMatcher(length) }

    @Factory
    static Matcher<StepSupport> step(Object minimum) { new StepMatcher(minimum) }

    @Factory
    static Matcher<TextSupport> text(String text) { new TextMatcher(text) }

    @Factory
    static Matcher<ValueSupport> value(Object value) { new ValueMatcher(value) }

    @Factory
    static Matcher<FocusSupport> focus() { new FocusMatcher() }

    @Factory
    static Matcher<ReferenceSupport> reference(String source) { new ReferenceMatcher(source) }

    @Factory
    static Matcher<TitleSupport> title(String title) { new TitleMatcher(title) }

    @Factory
    static Matcher<ItemSupport> items(String... items) { new ItemMatcher(items) }

    @Factory
    static Matcher<ItemSupport> items(Item... items) { new ItemMatcher(items) }

    @Factory
    static Matcher<SelectedItemSupport> selectedItem(String value) { new SelectedItemMatcher(value) }

    @Factory
    static Matcher<SelectedItemSupport> selectedItem(Item value) { new SelectedItemMatcher(value) }

    @Factory
    static Matcher<SelectedItemsSupport> selectedItems(String... values) { new SelectedItemsMatcher(values)  }

    @Factory
    static Matcher<SelectedItemsSupport> selectedItems(Item... values) { new SelectedItemsMatcher(values)  }

    @Factory
    static Matcher<GroupSupport> groups(String... values) { new GroupMatcher(values) }

    @Factory
    static Matcher<GroupSupport> groups(Group... values) { new GroupMatcher(values) }

    @Factory
    static Matcher<ColumnSupport> columns(String... values) { new ColumnMatcher(values) }

    @Factory
    static Matcher<ColumnSupport> columns(Column... values) { new ColumnMatcher(values) }

    @Factory
    static Matcher<RowSupport> rows(String... values) { new RowMatcher(values) }

    @Factory
    static Matcher<RowSupport> rows(Row... values) { new RowMatcher(values) }

    @Factory
    static Matcher<CellSupport> cells(String... values) { new CellMatcher(values) }

    @Factory
    static Matcher<CellSupport> cells(Cell... values) { new CellMatcher(values) }
}