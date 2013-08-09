package org.testatoo.config

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

interface ScopedProvider<E> {
    E scope(Scope scope)
}
