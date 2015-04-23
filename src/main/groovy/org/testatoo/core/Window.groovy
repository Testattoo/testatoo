package org.testatoo.core

import org.testatoo.core.evaluator.Evaluator

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Window {

    private Evaluator evaluator
    String id

    Window(Evaluator evaluator, String id) {
        this.evaluator = evaluator
        this.id = id;
    }

    void close() {
        evaluator.closeWindow(this.id)
    }

    @Override
    public String toString() {
        this.id
    }
}
