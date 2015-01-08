package org.testatoo.core

import org.testatoo.core.evaluator.Evaluator

/**
 * @author David Avenante (d.avenante@gmail.com)
 */

class Navigation {

    private Evaluator evaluator

    Navigation(Evaluator evaluator) {
        this.evaluator = evaluator
    }

    void to(String url) {
        evaluator.to(url)
    }

    void back() {
        evaluator.back()
    }

    void forward() {
        evaluator.forward()
    }

    void refresh() {
        evaluator.refresh()
    }
}
