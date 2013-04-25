package org.testatoo.core.component;

import org.testatoo.core.nature.LabelSupport;
import org.testatoo.core.nature.Selectable;

/**
 * This class allows the testing of ComboBox.
 * The ComboBox is a combination of a DropDown and a TextField, allowing the user to either type a value directly
 * into the textField or choose an option from the DropDown.
 *
 * @author David Avenante (d.avenante@gmail.com)
 */
public class ComboBox extends Component implements LabelSupport, Selectable {

    public ComboBox(String id) {
        super(id);
    }
}
