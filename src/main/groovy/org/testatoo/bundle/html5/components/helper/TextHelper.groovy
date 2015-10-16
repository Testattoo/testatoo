package org.testatoo.bundle.html5.components.helper

import org.testatoo.core.Component
import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class TextHelper {

    static String getText(Component c) {
        config.evaluator.eval(c.id, "it.text()")
    }
}
