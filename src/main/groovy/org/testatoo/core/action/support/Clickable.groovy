package org.testatoo.core.action.support

import org.testatoo.core.action.MouseClick
import org.testatoo.core.input.Key

import static org.testatoo.core.action.MouseModifiers.*

trait Clickable implements ActionSupport {
    void click() {
        this.execute(new MouseClick(Arrays.asList(LEFT, SINGLE), Collections.<Key> emptyList()))
    }

    void rightClick() {
        this.execute(new MouseClick(Arrays.asList(RIGHT, SINGLE), Collections.<Key> emptyList()))
    }

    void doubleClick() {
        this.execute(new MouseClick(Arrays.asList(LEFT, DOUBLE), Collections.<Key> emptyList()))
    }
}
