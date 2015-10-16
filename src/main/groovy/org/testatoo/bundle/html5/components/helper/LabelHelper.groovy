package org.testatoo.bundle.html5.components.helper

import org.testatoo.core.Component
import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class LabelHelper {

    private static final String expr =
            "function() {" +
                    "   var label = \$('label[for=' + it.attr('id') + ']');" +
                    "   if (label.length > 0) return label.text().trim();" +
                    "   var p = it.prev('label');" +
                    "   if (p.length > 0) return p.text();" +
                    "   return it.parent().text().trim();" +
                    "}()"

    static String getLabel(Component c) {
        config.evaluator.eval(c.id, expr).trim()
    }
}
