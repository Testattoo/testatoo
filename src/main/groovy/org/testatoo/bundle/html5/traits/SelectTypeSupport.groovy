package org.testatoo.bundle.html5.traits

import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
trait SelectTypeSupport {

    boolean isSingleSelectable() {
        !multiSelectable
    }

    boolean isMultiSelectable() {
        config.evaluator.getBool(id, "it.is('select') && it.prop('multiple')")
    }
}
