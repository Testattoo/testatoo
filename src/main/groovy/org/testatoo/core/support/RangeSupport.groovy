package org.testatoo.core.support
/**
 * @author David Avenante (d.avenante@gmail.com)
 */
interface RangeSupport {

    Object getMinimun()

    Object getMaximum()

    Object getStep()

    boolean isInRange()

    boolean isOutOfRange()
}