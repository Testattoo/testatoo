package org.testatoo.bundle.html5.components.heading

import org.testatoo.core.ByCss
import org.testatoo.core.component.Heading

import static org.testatoo.bundle.html5.components.helper.TextHelper.getText

/**
 * @author David Avenante (d.avenante@gmail.com)
 */

@ByCss('h5')
class H5 extends Heading {

    @Override
    String getText() {
        getText(this);
    }
}