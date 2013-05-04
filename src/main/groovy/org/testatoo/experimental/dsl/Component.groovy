package org.testatoo.experimental.dsl

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-03
 */
class Component implements IdSupport {

    private final Id id

    Component(Id id) { this.id = id }

    String getId() { id.value }

    Closure<?> is(State state) { return { state.check(this) } }

    Closure<?> are(State state) { is(state) }

}
