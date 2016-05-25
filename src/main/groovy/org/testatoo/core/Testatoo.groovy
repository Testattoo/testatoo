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
package org.testatoo.core

import org.testatoo.core.component.Component
import org.testatoo.core.component.Group
import org.testatoo.core.component.Item
import org.testatoo.core.component.datagrid.Cell
import org.testatoo.core.component.datagrid.Column
import org.testatoo.core.component.datagrid.Row
import org.testatoo.core.input.DragBuilder
import org.testatoo.core.input.Key
import org.testatoo.core.input.Keyboard
import org.testatoo.core.input.Mouse
import org.testatoo.core.internal.CachedMetaData
import org.testatoo.core.internal.jQueryIdProvider
import org.testatoo.core.support.Checkable
import org.testatoo.core.support.Clearable
import org.testatoo.core.support.Resettable
import org.testatoo.core.support.Submissible
import org.testatoo.core.support.UnCheckable
import org.testatoo.core.support.property.InputSupport
import org.testatoo.hamcrest.matcher.property.*
import org.testatoo.hamcrest.matcher.state.*

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class Testatoo {
    /**
     * Access Testatoo config
     */
    static Config config = new Config()

    /**
     * Create a component
     */
    static Component $(String jQuery) {
        new Component(new CachedMetaData(idProvider: new jQueryIdProvider(jQuery, true)))
    }

    /**
     * Creates a list of component
     */
    static List $$(String jQuery, Class clazz = Component) {
        Components components = new Components(clazz, new CachedMetaData(idProvider: new jQueryIdProvider(jQuery, false)))
        components.list()
    }

    static {
        config.scan 'org.testatoo.bundle.html5'
    }

    /**
     * States
     */
    public static Class available = AvailableMatcher
    public static Class checked = CheckedMatcher
    public static Class disabled = DisabledMatcher
    public static Class empty = EmptyMatcher
    public static Class enabled = EnabledMatcher
    public static Class filled = FilledMatcher
    public static Class hidden = HiddenMatcher
    public static Class inRange = InRangeMatcher
    public static Class invalid = InvalidMatcher
    public static Class missing = MissingMatcher
    public static Class optional = OptionalMatcher
    public static Class outOfRange = OutOfRangeMatcher
    public static Class readOnly = ReadOnlyMatcher
    public static Class required = RequiredMatcher
    public static Class selected = SelectedMatcher
    public static Class unchecked = UnCheckedMatcher
    public static Class unselected = UnSelectedMatcher
    public static Class valid = ValidMatcher
    public static Class visible = VisibleMatcher
    public static Class focused = FocusedMatcher

    /**
     * Properties
     */
    public static LabelMatcher label(String label) { new LabelMatcher(label) }
    public static MaximumMatcher maximum(object) { new MaximumMatcher(object) }
    public static MinimumMatcher minimum(object) { new MinimumMatcher(object) }
    public static PlaceholderMatcher placeholder(String placeholder) { new PlaceholderMatcher(placeholder) }
    public static LengthMatcher length(object) { new LengthMatcher(object) }
    public static ItemMatcher items(String... values) { new ItemMatcher(values) }
    public static ItemMatcher items(Item... items) { new ItemMatcher(items) }
    public static ColumnMatcher columns(String... values) { new ColumnMatcher(values) }
    public static ColumnMatcher columns(Column... columns) { new ColumnMatcher(columns) }
    public static RowMatcher rows(String... values) { new RowMatcher(values) }
    public static RowMatcher rows(Row... rows) { new RowMatcher(rows) }
    public static CellMatcher cells(String... values) { new CellMatcher(values) }
    public static CellMatcher cells(Cell... cells) { new CellMatcher(cells) }
    public static GroupMatcher groups(String... values) { new GroupMatcher(values) }
    public static GroupMatcher groups(Group... groups) { new GroupMatcher(groups) }
    public static SelectedItemMatcher selectedItem(String item) { new SelectedItemMatcher(item) }
    public static SelectedItemMatcher selectedItem(Item item) { new SelectedItemMatcher(item) }
    public static SelectedItemsMatcher selectedItems(String... items) { new SelectedItemsMatcher(items) }
    public static SelectedItemsMatcher selectedItems(Item... items) { new SelectedItemsMatcher(items) }
    public static StepMatcher step(object) { new StepMatcher(object) }
    public static TextMatcher text(String text) { new TextMatcher(text) }
    public static ValueMatcher value(Object value) { new ValueMatcher(value) }
    public static ReferenceMatcher reference(String reference) { new ReferenceMatcher(reference) }
    public static TitleMatcher title(String title) { new TitleMatcher(title) }
    public static FocusMatcher getFocus() { new FocusMatcher() }

    /**
     * Actions
     */
    static void visit(String uri) { Browser.open(uri) }
    static void check(Checkable c) { c.check() }
    static void uncheck(UnCheckable c) { c.uncheck() }
    static void clear(Clearable c) { c.clear() }
    static void reset(Resettable c) { c.reset() }
    static void submit(Submissible c) { c.submit() }
    static <T extends Component> T on(Component c) { c as T }
    static void select(Item... items) { items.each { it.select() } }
    static void unselect(Item... items) { items.each { it.unselect() } }
    static final FillAction fill(InputSupport c) { new FillAction(c) }
    static final FillAction set(InputSupport c) { new FillAction(c) }

    public static class FillAction {
        private InputSupport input

        public FillAction(InputSupport input) {
            this.input = input
        }

        public void with(Object value) {
            input.value(value)
        }

        public void to(Object value) {
            input.value(value)
        }
    }

    public static class Components<T extends Component> {
        private final MetaDataProvider meta
        private final Class<T> type;
        private List<T> components

        public Components(Class<T> type, MetaDataProvider meta) {
            this.meta = meta
            this.type = type
        }

        public List<T> list() {
            if (components == null) {
                components = meta.metaInfos().collect {
                    new Component(new CachedMetaData(idProvider: new jQueryIdProvider("#${it.id}", false))).asType(type)
                } as List<T>
            }
            return Collections.unmodifiableList(components)
        }
    }

    // Delegate to Mouse
    static void clickOn(Component c) { Mouse.clickOn(c) }
    static void doubleClickOn(Component c) { Mouse.doubleClickOn(c) }
    static void rightClickOn(Component c) { Mouse.rightClickOn(c) }
    static void hoveringMouseOn(Component c) { Mouse.hoveringMouseOn(c) }
    static DragBuilder drag(Component c) { Mouse.drag(c) }

    // Delegate to Keyboard
    static void type(Collection<?> keys) { Keyboard.type(keys) }
    static void type(Key key)  { type([key]) }
    static void type(String text) { type([text]) }
}