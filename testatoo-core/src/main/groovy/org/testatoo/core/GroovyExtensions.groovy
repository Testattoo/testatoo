package org.testatoo.core

import org.testatoo.core.component.Component
import org.testatoo.core.property.matcher.PropertyMatcher
import org.testatoo.core.state.State

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class GroovyExtensions {

    static boolean asBoolean(Block block) {
        Blocks.run(block)
        return true
    }

    static Block are(Collection<? extends Component> components, State matcher) {
        Blocks.compose(components.collect { it.is(matcher) })
    }

    static Block have(Collection<? extends Component> components, PropertyMatcher matcher) {
        Blocks.compose(components.collect { it.has(matcher) })
    }

}
