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
package org.testatoo.core

import org.hamcrest.Matcher
import org.testatoo.core.component.Button
import org.testatoo.core.component.CheckBox
import org.testatoo.core.component.Component
import org.testatoo.core.component.ComponentFactory
import org.testatoo.core.component.Dropdown
import org.testatoo.core.component.Group
import org.testatoo.core.component.Heading
import org.testatoo.core.component.Item
import org.testatoo.core.component.Link
import org.testatoo.core.component.ListBox
import org.testatoo.core.component.Panel
import org.testatoo.core.component.Radio
import org.testatoo.core.component.datagrid.Cell
import org.testatoo.core.component.datagrid.Column
import org.testatoo.core.component.datagrid.Row
import org.testatoo.core.component.field.ColorField
import org.testatoo.core.component.field.DateField
import org.testatoo.core.component.field.DateTimeField
import org.testatoo.core.component.field.EmailField
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
import org.testatoo.core.input.DragBuilder
import org.testatoo.core.input.Key
import org.testatoo.core.input.Keyboard
import org.testatoo.core.input.Mouse
import org.testatoo.core.internal.CachedMetaData
import org.testatoo.core.internal.Wait
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
        config.scan 'org.testatoo.bundle'
    }

    protected static componentFactory = new ComponentFactory()
    protected static mouse = new Mouse()
    protected static keyboard = new Keyboard()
    protected static wait = new Wait()

    /**
     * States
     */
    static Class available = AvailableMatcher
    static Class checked = CheckedMatcher
    static Class disabled = DisabledMatcher
    static Class empty = EmptyMatcher
    static Class enabled = EnabledMatcher
    static Class filled = FilledMatcher
    static Class hidden = HiddenMatcher
    static Class inRange = InRangeMatcher
    static Class invalid = InvalidMatcher
    static Class missing = MissingMatcher
    static Class optional = OptionalMatcher
    static Class outOfRange = OutOfRangeMatcher
    static Class readOnly = ReadOnlyMatcher
    static Class required = RequiredMatcher
    static Class selected = SelectedMatcher
    static Class unchecked = UnCheckedMatcher
    static Class unselected = UnSelectedMatcher
    static Class valid = ValidMatcher
    static Class visible = VisibleMatcher
    static Class focused = FocusedMatcher

    /**
     * Properties
     */
    static LabelMatcher label(String label) { new LabelMatcher(label) }
    static MaximumMatcher maximum(object) { new MaximumMatcher(object) }
    static MinimumMatcher minimum(object) { new MinimumMatcher(object) }
    static PlaceholderMatcher placeholder(String placeholder) { new PlaceholderMatcher(placeholder) }
    static LengthMatcher length(object) { new LengthMatcher(object) }
    static ItemMatcher items(String... values) { new ItemMatcher(values) }
    static ItemMatcher items(Item... items) { new ItemMatcher(items) }
    static ColumnMatcher columns(String... values) { new ColumnMatcher(values) }
    static ColumnMatcher columns(Column... columns) { new ColumnMatcher(columns) }
    static RowMatcher rows(String... values) { new RowMatcher(values) }
    static RowMatcher rows(Row... rows) { new RowMatcher(rows) }
    static CellMatcher cells(String... values) { new CellMatcher(values) }
    static CellMatcher cells(Cell... cells) { new CellMatcher(cells) }
    static GroupMatcher groups(String... values) { new GroupMatcher(values) }
    static GroupMatcher groups(Group... groups) { new GroupMatcher(groups) }
    static SelectedItemMatcher selectedItem(String item) { new SelectedItemMatcher(item) }
    static SelectedItemMatcher selectedItem(Item item) { new SelectedItemMatcher(item) }
    static SelectedItemsMatcher selectedItems(String... items) { new SelectedItemsMatcher(items) }
    static SelectedItemsMatcher selectedItems(Item... items) { new SelectedItemsMatcher(items) }
    static StepMatcher step(object) { new StepMatcher(object) }
    static TextMatcher text(String text) { new TextMatcher(text) }
    static ValueMatcher value(Object value) { new ValueMatcher(value) }
    static ReferenceMatcher reference(String reference) { new ReferenceMatcher(reference) }
    static TitleMatcher title(String title) { new TitleMatcher(title) }
    static FocusMatcher getFocus() { new FocusMatcher() }

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

    static class FillAction {
        private InputSupport input

        FillAction(InputSupport input) {
            this.input = input
        }

        void with(Object value) {
            input.value(value)
        }

        void to(Object value) {
            input.value(value)
        }
    }

    static class Components<T extends Component> {
        private final MetaDataProvider meta
        private final Class<T> type
        private List<T> components

        Components(Class<T> type, MetaDataProvider meta) {
            this.meta = meta
            this.type = type
        }

        List<T> list() {
            if (components == null) {
                components = meta.metaInfos().collect {
                    new Component(new CachedMetaData(idProvider: new jQueryIdProvider("#${it.id}", false))).asType(type)
                } as List<T>
            }
            return Collections.unmodifiableList(components)
        }
    }

    // Delegate to Mouse
    static void clickOn(Component c) { mouse.clickOn(c) }
    static void doubleClickOn(Component c) { mouse.doubleClickOn(c) }
    static void rightClickOn(Component c) { mouse.rightClickOn(c) }
    static void hoveringMouseOn(Component c) { mouse.hoveringMouseOn(c) }
    static DragBuilder drag(Component c) { mouse.drag(c) }

    // Delegate to Keyboard
    static void type(Collection<?> keys) { keyboard.type(keys) }
    static void type(Key key)  { type([key]) }
    static void type(String text) { type([text]) }

    // Generic Component Factory
    static Button button(String text) { componentFactory.button(text) }
    static Radio radio(String label) { componentFactory.radio(label) }
    static CheckBox checkbox(String label) { componentFactory.checkbox(label)  }
    static Dropdown dropdown(String label) { componentFactory.dropdown(label) }
    static ListBox listBox(String label) { componentFactory.listBox(label) }
    static Group group(String value) { componentFactory.group(value) }
    static Item item(String value) { componentFactory.item(value) }
    static Heading heading(String text) { componentFactory.heading(text) }
    static Panel panel(String title) { componentFactory.panel(title) }
    static Link link(String name) { componentFactory.link(name) }

    static PasswordField passwordField(String value) { componentFactory.passwordField(value) }
    static TextField textField(String value) { componentFactory.textField(value) }
    static SearchField searchField(String value) { componentFactory.searchField(value) }
    static EmailField emailField(String value) { componentFactory.emailField(value) }
    static URLField urlField(String value) { componentFactory.urlField(value) }
    static NumberField numberField(String value) { componentFactory.numberField(value) }
    static RangeField rangeField(String value) { componentFactory.rangeField(value) }
    static DateField dateField(String value) { componentFactory.dateField(value) }
    static ColorField colorField(String value) { componentFactory.colorField(value) }
    static DateTimeField dateTimeField(String value) { componentFactory.dateTimeField(value) }
    static MonthField monthField(String value) { componentFactory.monthField(value) }
    static PhoneField phoneField(String value) { componentFactory.phoneField(value) }
    static TimeField timeField(String value) { componentFactory.timeField(value) }
    static WeekField weekField(String value) { componentFactory.weekField(value) }

    static void waitUntil(Closure c, Matcher what = null) { wait.waitUntil(c, what) }
}