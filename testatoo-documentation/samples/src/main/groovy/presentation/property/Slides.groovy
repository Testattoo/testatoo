package presentation.property

import org.testatoo.core.component.Component
import org.testatoo.core.component.Section
import org.testatoo.core.property.Property
import org.testatoo.core.property.matcher.EqualsToListMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Slides extends Property {

    Slides() {
        evaluator { Component c -> c.evaluator.getMetaInfo("\$('#${id}').find('section')").collect { it as Section } }
    }

    @Delegate
    private EqualsToListMatcher.Matchers eq = EqualsToListMatcher.matchers(this)

}