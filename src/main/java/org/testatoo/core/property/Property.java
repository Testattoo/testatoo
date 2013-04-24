package org.testatoo.core.property;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class Property {

    private String property;
    private String value;

    public Property(String property, String value) {
        this.property = property;
        this.value = value;
    }

    public boolean equalsTo(String expected_value) {
        if (value.equals(expected_value))
            return true;
        throw new AssertionError("Expected " + property + ": \"" + expected_value + "\" but was: \"" + value + "\"");
    }

    public boolean contains(String expected_value) {
        if (value.contains(expected_value))
            return true;
        throw new AssertionError("Expected " + property + " contains: \"" + expected_value + "\" but was: \"" + value + "\"");

    }

    public boolean matches(String expected_value) {
        if (value.matches(expected_value))
            return true;
        throw new AssertionError("Expected " + property + " matches: \"" + expected_value + "\" but was: \"" + value + "\"");

    }

}
