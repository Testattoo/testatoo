package org.testatoo.core.property

import org.testatoo.core.Evaluator
import org.testatoo.core.component.Component
import org.testatoo.core.component.ComponentType

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
abstract class PropertySkeleton implements Property {
    final Evaluator evaluator

    PropertySkeleton(Evaluator evaluator) { this.evaluator = evaluator }

    @Override
    final String getValue(Component component) {
        // TODO component test support this property
        ComponentType type = evaluator.getType(this)
        if(type != component.type) {
//            throw new ....
        }
        return value(component)
    }

    abstract String value(Component component)

}
