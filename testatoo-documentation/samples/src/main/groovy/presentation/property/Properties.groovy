package presentation.property

import org.testatoo.core.property.matcher.PropertyMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Properties {

    static final Author author = new Author()
    static final PropertyMatcher author(String expected) { author.equalsTo(expected) }

    static final Company company = new Company()
    static final PropertyMatcher company(String expected) { company.equalsTo(expected) }
}
