package org.testatoo

import org.testatoo.core.component.Component
import org.testatoo.core.property.Property
import org.testatoo.core.property.matcher.EqualsToMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class MyProp extends Property {
    MyProp() {
        evaluator { Component c -> /*c.evaluator.execute('$(...)')*/ return 'temp1' }
    }

    @Delegate
    private EqualsToMatcher.Matchers eq = EqualsToMatcher.matchers(this)
}
