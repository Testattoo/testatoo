package org.testatoo.core.property

import org.testatoo.core.property.matcher.EqualsToListMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Items extends Property {

    @Delegate
    private EqualsToListMatcher.Matchers eq = EqualsToListMatcher.matchers(this)

//    @Delegate
//    private ContainingMatcher.Matchers contains = ContainingMatcher.matchers(this)

}
