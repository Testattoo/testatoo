package org.testatoo.experimental.dsl

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-03
 */
class Component implements IdSupport {

    private final Id id

    Component(Id id) { this.id = id }

    String getId() { id.value }

    Block is(Matcher matcher) { block(matcher) }

    Block are(Matcher matcher) { block(matcher) }

    Block has(Matcher matcher) { block(matcher) }

    Block have(Matcher matcher) { block(matcher) }

    private block(Matcher m) { return { m.matches(this) } as Block }
}
