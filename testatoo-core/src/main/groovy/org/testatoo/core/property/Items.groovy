package org.testatoo.core.property

import org.testatoo.core.component.Component
import org.testatoo.core.property.matcher.EqualsToListMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Items extends Property {

    Items() {
        // extratc list on values
        evaluator { Component c ->

            evaluator.getMetaInfo("${id} > li").collect { it as ListItem }

            c.evaluator.getJson()MString("testatoo.ext.getLabel('${c.id}')")
        }
    }

    @Delegate
    private EqualsToListMatcher.Matchers eq = EqualsToListMatcher.matchers(this)

}
