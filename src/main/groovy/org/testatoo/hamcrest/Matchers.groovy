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
    public static <T> Matcher<T> has(Matcher<T> matcher) { new Has(matcher) }

    // States
    @Factory
    public static Matcher<Component> enabled() { new EnabledMatcher() }

    @Factory
    public static Matcher<Component> disabled() { new DisabledMatcher() }

    @Factory
    public static Matcher<Component> available() { new AvailableMatcher() }

    @Factory
    public static Matcher<Component> missing() { new MissingMatcher() }

    @Factory
    public static Matcher<Component> visible() { new VisibleMatcher() }

    @Factory
    public static Matcher<Component> hidden() { new HiddenMatcher() }

    @Factory
    public static Matcher<EmptySupport> empty() { new EmptyMatcher() }

    @Factory
    public static Matcher<EmptySupport> filled() { new FilledMatcher() }

    @Factory
    public static Matcher<FocusSupport> focused() { new FocusedMatcher() }

    @Factory
    public static Matcher<ReadOnlySupport> readOnly() { new ReadOnlyMatcher() }

    @Factory
    public static Matcher<RequiredSupport> required() { new RequiredMatcher() }

    @Factory
    public static Matcher<RequiredSupport> optional() { new OptionalMatcher() }

    @Factory
    public static Matcher<CheckSupport> checked() { new CheckedMatcher() }

    @Factory
    public static Matcher<CheckSupport> unchecked() { new UnCheckedMatcher() }

    @Factory
    public static Matcher<SelectSupport> selected() { new SelectedMatcher() }

    @Factory
    public static Matcher<SelectSupport> unselected() { new UnSelectedMatcher() }

    @Factory
    public static Matcher<RangeSupport> inRange() { new InRangeMatcher() }

    @Factory
    public static Matcher<RangeSupport> outOfRange() { new OutOfRangeMatcher() }

    @Factory
    public static Matcher<ValiditySupport> valid() { new ValidMatcher() }

    @Factory
    public static Matcher<ValiditySupport> invalid() { new InvalidMatcher() }

    @Factory
    public static Matcher<Component> contain(Component... components) { new ContainMatcher(Testatoo.config.evaluator, components)  }

    // Properties
    @Factory
    public static Matcher<LabelSupport> label(String label) { new LabelMatcher(label) }

    @Factory
    public static Matcher<InputSupport> placeholder(String placeholder) { new PlaceholderMatcher(placeholder) }

    @Factory
    public static Matcher<MaximumSupport> maximum(Object maximum) { new MaximumMatcher(maximum) }

    @Factory
    public static Matcher<MinimumSupport> minimum(Object minimum) { new MinimumMatcher(minimum) }

    @Factory
    public static Matcher<LengthSupport> length(Object length) { new LengthMatcher(length) }

    @Factory
    public static Matcher<StepSupport> step(Object minimum) { new StepMatcher(minimum) }

    @Factory
    public static Matcher<TextSupport> text(String text) { new TextMatcher(text) }

    @Factory
    public static Matcher<ValueSupport> value(Object value) { new ValueMatcher(value) }

    @Factory
    public static Matcher<FocusSupport> focus() { new FocusMatcher() }

    @Factory
    public static Matcher<ReferenceSupport> reference(String source) { new ReferenceMatcher(source) }

    @Factory
    public static Matcher<TitleSupport> title(String title) { new TitleMatcher(title) }

    @Factory
    public static Matcher<ItemSupport> items(String... items) { new ItemMatcher(items) }

    @Factory
    public static Matcher<ItemSupport> items(Item... items) { new ItemMatcher(items) }

    @Factory
    public static Matcher<SelectedItemSupport> selectedItem(String value) { new SelectedItemMatcher(value) }

    @Factory
    public static Matcher<SelectedItemSupport> selectedItem(Item value) { new SelectedItemMatcher(value) }

    @Factory
    public static Matcher<SelectedItemsSupport> selectedItems(String... values) { new SelectedItemsMatcher(values)  }

    @Factory
    public static Matcher<SelectedItemsSupport> selectedItems(Item... values) { new SelectedItemsMatcher(values)  }

    @Factory
    public static Matcher<GroupSupport> groups(String... values) { new GroupMatcher(values) }

    @Factory
    public static Matcher<GroupSupport> groups(Group... values) { new GroupMatcher(values) }

    @Factory
    public static Matcher<ColumnSupport> columns(String... values) { new ColumnMatcher(values) }

    @Factory
    public static Matcher<ColumnSupport> columns(Column... values) { new ColumnMatcher(values) }

    @Factory
    public static Matcher<RowSupport> rows(String... values) { new RowMatcher(values) }

    @Factory
    public static Matcher<RowSupport> rows(Row... values) { new RowMatcher(values) }

    @Factory
    public static Matcher<CellSupport> cells(String... values) { new CellMatcher(values) }

    @Factory
    public static Matcher<CellSupport> cells(Cell... values) { new CellMatcher(values) }

}
