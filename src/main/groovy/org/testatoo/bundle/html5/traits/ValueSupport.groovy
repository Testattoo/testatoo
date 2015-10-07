package org.testatoo.bundle.html5.traits

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
trait ValueSupport {

    String getValue() {
        eval("it.val()")
    }

}