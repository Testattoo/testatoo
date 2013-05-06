package org.testatoo.experimental.dsl

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-03
 */
class Component implements IdSupport {

    private Id id
    Evaluator evaluator

    void setId(Id id) { this.id = id }

    String getId() { id.getValue(evaluator) }

    Block is(Matcher matcher) { block(matcher) }

    Block are(Matcher matcher) { block(matcher) }

    Block has(Matcher matcher) { block(matcher) }

    Block have(Matcher matcher) { block(matcher) }

    Block click() { Blocks.block "click on ${this}", { evaluator.click(this) } }

    private block(Matcher m) { Blocks.block "matching ${this}: ${m}", { m.matches(this) } }

    @Override
    String toString() { getClass().simpleName + ":${id as String}" }

    Object asType(Class clazz) {
        if (Component.isAssignableFrom(clazz)) {
            Component c = (Component) clazz.newInstance()
            c.setId(this.id)
            c.evaluator = this.evaluator
            return c
        }
        return super.asType(clazz)
    }

}
