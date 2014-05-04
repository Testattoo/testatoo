package presentation.property

import org.testatoo.core.component.Component
import org.testatoo.core.property.Property
import org.testatoo.core.property.matcher.ContainingMatcher
import org.testatoo.core.property.matcher.EqualsToMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Company extends Property {

    Company() {
        evaluator { Component c -> c.evaluator.getString("\$('[data-role=company]').text()") }
    }

    @Delegate
    private EqualsToMatcher.Matchers eq = EqualsToMatcher.matchers(this)

    @Delegate
    private ContainingMatcher.Matchers contains = ContainingMatcher.matchers(this)
}
