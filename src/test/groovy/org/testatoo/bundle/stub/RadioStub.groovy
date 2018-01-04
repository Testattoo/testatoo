package org.testatoo.bundle.stub

import org.testatoo.core.CssIdentifier
import org.testatoo.core.component.Radio

@CssIdentifier('RadioStub')
class RadioStub extends Radio {
    void check() {
    }

    String label() {
        return 'Radio Label'
    }

    boolean checked() {
        return false
    }
}
