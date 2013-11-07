package org.testatoo.core

import org.testatoo.core.component.Component
import org.testatoo.core.property.matcher.PropertyMatcher
import org.testatoo.core.state.State

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Assertion {

    final Component component

    Assertion(Component c) {
        this.component = c;
    }

    Block is(State matcher) { component.is(matcher) }

    Block has(PropertyMatcher matcher) { component.has(matcher) }

}
