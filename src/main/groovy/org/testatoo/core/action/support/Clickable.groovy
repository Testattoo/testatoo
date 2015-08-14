package org.testatoo.core.action.support

import org.testatoo.core.action.MouseClick

import static org.testatoo.core.action.MouseModifiers.*

trait Clickable implements ActionSupport {
    void click() {
        this.execute(new MouseClick([LEFT, SINGLE]))
    }

    void rightClick() {
        this.execute(new MouseClick([RIGHT, SINGLE]))
    }

    void doubleClick() {
        this.execute(new MouseClick([LEFT, DOUBLE]))
    }
}
