package org.testatoo.core.traits

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
trait LabelSuport {

    String expr =
            "function() {" +
            "   var label = \$('label[for=' + it.attr('id') + ']');" +
            "   if (label.length > 0) return label.text().trim();" +
            "   var p = it.prev('label');" +
            "   if (p.length > 0) return p.text();" +
            "   return it.parent().text().trim();" +
            "}()"

    String getLabel() {
        eval(expr).trim()
    }

}