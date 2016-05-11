package org.testatoo.core.component

import org.testatoo.core.component.field.DateField
import org.testatoo.core.component.field.EmailField
import org.testatoo.core.component.field.Field
import org.testatoo.core.component.field.NumberField
import org.testatoo.core.component.field.PasswordField
import org.testatoo.core.component.field.RangeField
import org.testatoo.core.component.field.SearchField
import org.testatoo.core.component.field.TextField
import org.testatoo.core.component.field.URLField
import org.testatoo.core.internal.Identifiers

import static org.testatoo.core.Testatoo.$$

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class ComponentFactory {

    public static Button button(String text) {
        Identifiers.findSelectorsFor(Button).collectMany {
            $$(it.value, it.key)
        }.find { it.text() == text }
    }

    public static Radio radio(String label) {
        Identifiers.findSelectorsFor(Radio).collectMany {
            $$(it.value, it.key)
        }.find { it.label() == label }
    }

    public static CheckBox checkbox(String label) {
        Identifiers.findSelectorsFor(CheckBox).collectMany {
            $$(it.value, it.key)
        }.find { it.label() == label }
    }

    public static PasswordField passwordField(String value) { field(value, PasswordField) }
    public static TextField textField(String value) { field(value, TextField) }
    public static SearchField searchField(String value) { field(value, SearchField) }
    public static EmailField emailField(String value) { field(value, EmailField) }
    public static URLField urlField(String value) { field(value, URLField) }
    public static NumberField numberField(String value) { field(value, NumberField) }
    public static RangeField rangeField(String value) { field(value, RangeField) }
    public static DateField dateField(String value) { field(value, DateField) }

    private static  <T extends Field> T field(String value, Class<T> clazz) {
        List<T> fields = new ArrayList<>()
        Identifiers.findSelectorsFor(clazz).each {
            fields.addAll($$(it.value, it.key))
        }

        fields.find { it.label() == value || it.placeholder() == value }
    }
}
