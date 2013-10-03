package org.testatoo.core

import org.testatoo.core.component.Component
import org.testatoo.core.property.Property
import org.testatoo.core.property.matcher.PropertyMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class TestatooBuilder {

    static TestatooBuilder assertThat(Component c) { new TestatooBuilder(c) }

    TestatooBuilder has(Property p) { this }

    TestatooBuilder has(PropertyMatcher p) { this }

}

