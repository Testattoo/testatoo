package org.testatoo.core.property;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class PropertyWrapper {

    private String property;
    private String value;

    public PropertyWrapper(String property, String value) {
        this.property = property;
        this.value = value;
    }

    public boolean equalsTo(String expected_value) {
        if (expected_value.equals(value))
            return true;
        throw new AssertionError("Expected " + property + ": \"" + expected_value + "\" but was: \"" + value + "\"");
    }

}
