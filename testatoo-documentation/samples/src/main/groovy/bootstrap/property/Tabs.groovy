package bootstrap.property

import org.testatoo.core.property.Property
import org.testatoo.core.property.matcher.ContainingMatcher
import org.testatoo.core.property.matcher.EqualsToListMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Tabs extends Property {

    @Delegate
    private EqualsToListMatcher.Matchers eq = EqualsToListMatcher.matchers(this)

    @Delegate
    private ContainingMatcher.Matchers contains = ContainingMatcher.matchers(this)

}
