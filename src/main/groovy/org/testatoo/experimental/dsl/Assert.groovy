package org.testatoo.experimental.dsl

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-03
 */
class Assert {

    static void ensure(Component c, boolean test, Map<String, ?> opts) {
        if (!test) throw new AssertionError("Component ${c.class.simpleName} with id ${c.id} expected ${opts.e} but was ${opts.w}")
    }

}
