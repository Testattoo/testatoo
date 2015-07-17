package org.testatoo.core.action.support;

import org.testatoo.core.Component;
import org.testatoo.core.action.MouseDrag;

public interface Draggable extends ActionSupport {
    default void drag(Component onto) {
        this.execute(new MouseDrag(onto));
    }
}
