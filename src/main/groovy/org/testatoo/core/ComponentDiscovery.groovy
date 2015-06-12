package org.testatoo.core

import org.testatoo.core.component.Component

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class ComponentDiscovery {

    private static final ComponentDiscovery INSTANCE = new ComponentDiscovery()

    private ComponentDiscovery() {
        ServiceLoader<Component>
    }

    static ComponentDiscovery getInstance() {
        return INSTANCE
    }
}
