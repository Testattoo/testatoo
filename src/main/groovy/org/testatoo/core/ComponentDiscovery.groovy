package org.testatoo.core

import org.testatoo.core.component.Component

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class ComponentDiscovery {

    private static final ComponentDiscovery INSTANCE = new ComponentDiscovery()

    final List<Class<Component>> componentClasses

    private ComponentDiscovery() {
        componentClasses = ServiceClassLoader.load(Component).findAll { it.isAnnotationPresent(Assert) }
    }

    static ComponentDiscovery getInstance() {
        return INSTANCE
    }

}
