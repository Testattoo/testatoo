package org.testatoo.bundle.stub

import org.testatoo.core.CssIdentifier
import org.testatoo.core.component.Panel

@CssIdentifier('PanelStub')
class PanelStub extends Panel {
    String title() {
        return 'Panel Title'
    }
}
