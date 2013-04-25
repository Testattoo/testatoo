package org.testatoo.core.component;

import org.testatoo.core.nature.LabelSupport;
import org.testatoo.core.nature.Selectable;

/**
 * This class allows the testing of a DropDown.
 * The DropDown allows the user to choose one item from a list.
 * When the dropDown list is inactive, it displays a single item.
 * When activated, it displays a list of items, from which the user may select one.
 * When the user selects a new item, the control reverts to its inactive state, displaying the selected item.
 *
 * @author David Avenante (d.avenante@gmail.com)
 */
public class DropDown extends Component implements LabelSupport, Selectable {

    public DropDown(String id) {
        super(id);
    }
}
