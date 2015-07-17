package org.testatoo.core.action.support;

import org.testatoo.core.action.MouseClick;
import org.testatoo.core.action.MouseModifiers;
import org.testatoo.core.input.Key;

import java.util.Arrays;
import java.util.Collections;

import static org.testatoo.core.action.MouseModifiers.*;

public interface Clickable extends ActionSupport {
    default void click() {
        this.execute(new MouseClick(Arrays.asList(LEFT, SINGLE), Collections.<Key>emptyList()));
    }

    default void rightClick() {
        this.execute(new MouseClick(Arrays.asList(RIGHT, SINGLE), Collections.<Key>emptyList()));
    }

    default void doubleClick() {
        this.execute(new MouseClick(Arrays.asList(LEFT, DOUBLE), Collections.<Key>emptyList()));
    }
}
