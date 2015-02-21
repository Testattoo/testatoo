package org.testatoo.core.property

import org.testatoo.core.component.Component
import org.testatoo.core.property.matcher.EqualsToMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class ArticleSize extends Property {

    ArticleSize() {
        evaluator { Component c -> Integer.valueOf(c.evaluator.getString("\$('#${c.id}').find('article').length")) }
    }

    @Delegate
    private EqualsToMatcher.Matchers eq = EqualsToMatcher.matchers(this)
}
