package org.testatoo.core.action.support;

import org.testatoo.core.action.MouseClick;
import org.testatoo.core.action.MouseModifiers;
import org.testatoo.core.input.Key;

import java.util.Arrays;
import java.util.Collections;

public interface Clickable extends ActionSupport {
    default void click() {
        this.execute(new MouseClick(Arrays.asList(MouseModifiers.LEFT, MouseModifiers.SINGLE), Collections.<Key>emptyList()));
    }

    default void doubleClick() {
        this.execute(new MouseClick(Arrays.asList(MouseModifiers.LEFT, MouseModifiers.DOUBLE), Collections.<Key>emptyList()));
    }
}
