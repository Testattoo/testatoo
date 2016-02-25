package org.testatoo.core.input

import org.testatoo.core.component.Component

import static org.testatoo.core.Testatoo.getConfig

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class DragBuilder {

    Component dragged

    public DragBuilder(Component dragged) {
        this.dragged = dragged
    }

    public void on(Component onto) {
        config.evaluator.dragAndDrop(dragged.id, onto.id)
    }
}
