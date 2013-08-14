package org.testatoo.core.property

import org.testatoo.core.component.Component
import org.testatoo.core.component.ComponentException

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
abstract class PropertySkeleton implements Property {

    @Override
    final String getValue(Component component) {
        if (!component.supports(this)) {
            throw new ComponentException("Component ${component} does not support property ${getClass().simpleName}")
        }
        return value(component)
    }

    abstract String value(Component component)

}
