package org.testatoo.bundle.html5.traits

import static org.testatoo.core.Testatoo.config

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
trait ValueSupport {
    String getValue() {
        config.evaluator.eval(this.id, "it.val()")
    }
}