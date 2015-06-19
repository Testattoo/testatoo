package org.testatoo.core

import org.testatoo.core.component.Component

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class ComponentDiscovery {

    private static final ComponentDiscovery INSTANCE = new ComponentDiscovery()

    final List<Class<Component>> componentTypes

    private ComponentDiscovery() {
        componentTypes = ServiceClassLoader.load(Component).findAll { Identifiers.hasIdentifier(it) }
    }

    static ComponentDiscovery getInstance() {
        return INSTANCE
    }

}
