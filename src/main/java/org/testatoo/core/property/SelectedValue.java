package org.testatoo.core.property;

import org.testatoo.core.EvaluatorHolder;
import org.testatoo.core.component.Component;
import org.testatoo.core.nature.Selectable;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class SelectedValue extends PropertySupport {

    @Override
    public Property is(Component component) {
        if (component instanceof Selectable) {
            return new Property("text", EvaluatorHolder.get().selectedValue((Selectable) component));
        }
        throw new AssertionError("The component does not support Text");
    }
}
