package org.testatoo.bundle.html5.traits

import static org.testatoo.core.Testatoo.getConfig

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
trait ValiditySupport {

    boolean isValid() {
        !invalid
    }

    boolean isInvalid() {
        config.evaluator.getBool(id, "it.is(':invalid')")
    }
}