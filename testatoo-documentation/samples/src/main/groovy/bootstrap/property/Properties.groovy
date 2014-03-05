package bootstrap.property

import org.testatoo.core.property.matcher.PropertyMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Properties {

    static final Tabs tabs = new Tabs()
    static final PropertyMatcher tabs(String... expected) { tabs.equalsTo(expected) }
}
