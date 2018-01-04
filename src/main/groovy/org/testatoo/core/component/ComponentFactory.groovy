/**
 * Copyright © 2017 Ovea (d.avenante@gmail.com)
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
package org.testatoo.core.component

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
import org.testatoo.core.internal.Identifiers

import static org.testatoo.core.Testatoo.$$

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class ComponentFactory {
    static Button button(String text) { collectAll(Button).find { it.text() == text } }
    static Radio radio(String label) { collectAll(Radio).find { it.label() == label } }
    static CheckBox checkbox(String label) { collectAll(CheckBox).find { it.label() == label } }
    static Dropdown dropdown(String label) { collectAll(Dropdown).find { it.label() == label } }
    static ListBox listBox(String label) { collectAll(ListBox).find { it.label() == label } }
    static Group group(String value) { collectAll(Group).find { it.value() == value } }
    static Item item(String value) { collectAll(Item).find { it.value() == value } }
    static Heading heading(String text) { collectAll(Heading).find { it.text() == text } }
    static Panel panel(String title) { collectAll(Panel).find { it.title() == title } }
    static Link link(String text) { collectAll(Link).find { it.text() == text }}

    static PasswordField passwordField(String value) { field(value, PasswordField) }
    static TextField textField(String value) { field(value, TextField) }
    static SearchField searchField(String value) { field(value, SearchField) }
    static EmailField emailField(String value) { field(value, EmailField) }
    static URLField urlField(String value) { field(value, URLField) }
    static NumberField numberField(String value) { field(value, NumberField) }
    static RangeField rangeField(String value) { field(value, RangeField) }
    static DateField dateField(String value) { field(value, DateField) }
    static ColorField colorField(String value) { field(value, ColorField) }
    static DateTimeField dateTimeField(String value) { field(value, DateTimeField) }
    static MonthField monthField(String value) { field(value, MonthField) }
    static PhoneField phoneField(String value) { field(value, PhoneField) }
    static TimeField timeField(String value) { field(value, TimeField) }
    static WeekField weekField(String value) { field(value, WeekField) }

    private static <T extends Field> T field(String value, Class<T> clazz) {
        collectAll(clazz).find { it.label() == value || it.placeholder() == value }
    }

    private static <T extends Component> List<T> collectAll(Class<T> clazz) {
        Identifiers.findSelectorsFor(clazz).collectMany {
            $$(it.value, it.key)
        }
    }
}
