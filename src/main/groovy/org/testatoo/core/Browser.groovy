package org.testatoo.core

import org.testatoo.core.evaluator.Evaluator

/**
 * @author David Avenante (d.avenante@gmail.com)
 */

class Browser {

    private Evaluator evaluator

    Browser(Evaluator evaluator) {
        this.evaluator = evaluator
    }

    String getTitle() {
        evaluator.title
    }

    String getPageSource() {
        evaluator.pageSource
    }

    String getUrl() {
        evaluator.url
    }

    void open(String url) {
        evaluator.open(url)
    }

    Navigation getNavigate() {
        return new Navigation(evaluator)
    }
}
