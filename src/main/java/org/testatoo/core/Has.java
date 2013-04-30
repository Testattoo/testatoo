package org.testatoo.core;

import org.testatoo.core.component.Component;
import org.testatoo.core.property.Property;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class Has {

    private final Property property;
    private final Component component;

    public Has(Property property, Component component) {
        this.property = property;
        this.component = component;
    }

    public Runnable equalsTo(final String expected_value) {
        return new Runnable() {
            @Override
            public void run() {
                if (property.value(component).equals(expected_value))
                    return;
                throw new AssertionError("Expected " + property.property() + ": \"" + expected_value + "\" but was: \"" + property.value(component) + "\"");
            }
        };
    }

    public Runnable contains(final String expected_value) {
        return new Runnable() {
            @Override
            public void run() {
                if (property.value(component).contains(expected_value))
                    return;
                throw new AssertionError("Expected " + property.property() + " contains: \"" + expected_value + "\" but was: \"" + property.value(component) + "\"");
            }
        };
    }

    public Runnable matches(final String expression) {
        return new Runnable() {
            @Override
            public void run() {
                if (property.value(component).matches(expression))
                    return;
                throw new AssertionError("Expected " + property.property() + " matches: \"" + expression + "\" but was: \"" + property.value(component) + "\"");
            }
        };
    }

}
