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

    void is(State matcher) {
        assert component.is(matcher)
    }

    void has(PropertyMatcher matcher) {
        assert component.has(matcher)
    }

}
