package bootstrap

import org.testatoo.core.property.matcher.PropertyMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class GroovyExtensions {

    public static PropertyMatcher getTabs(Integer expected) {
        org.testatoo.core.property.Properties.size.equalsTo(expected)
    }

}
