package org.testatoo.core.action.support

import org.testatoo.core.Component
import org.testatoo.core.action.MouseDrag

trait Draggable implements ActionSupport {
    void drag(Component onto) {
        this.execute(new MouseDrag(onto))
    }
}
