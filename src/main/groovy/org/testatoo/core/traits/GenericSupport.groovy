package org.testatoo.core.traits

import org.testatoo.core.ComponentException

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
trait GenericSupport {

    boolean isEnabled() {
        !disabled
    }

    boolean isDisabled() {
        Boolean.parseBoolean(eval("it.is(':disabled') || !!it.attr('disabled')"))
    }

    boolean isAvailable() {
        !missing
    }

    boolean isMissing() {
        try {
            meta.idProvider.getMetaInfos(evaluator)
            return false
        } catch (ComponentException ignored) {
            return true
        }

    }

    boolean isHidden() {
        Boolean.parseBoolean(eval("it.is(':hidden')"))
    }

    boolean isVisible() {
        !hidden
    }

}