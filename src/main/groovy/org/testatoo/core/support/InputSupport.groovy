package org.testatoo.core.support
/**
 * @author David Avenante (d.avenante@gmail.com)
 */
interface InputSupport {

    String getPlaceholder()

    boolean isEmpty()

    boolean isFilled()

    boolean isReadOnly()

    boolean isRequired()

    boolean isOptional()

    void setValue(Object value)

    void clear()
}