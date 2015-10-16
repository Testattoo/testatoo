package org.testatoo.core.support
/**
 * @author David Avenante (d.avenante@gmail.com)
 */
interface CheckSupport {

    boolean isChecked()

    boolean isUnchecked()

    void check()

    void uncheck()
}