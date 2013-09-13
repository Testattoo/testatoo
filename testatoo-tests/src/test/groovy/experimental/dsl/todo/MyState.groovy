package experimental.dsl.todo

import org.testatoo.core.component.Component
import org.testatoo.core.state.State

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-08-14
 */
class MyState extends State {
    MyState() {
        // custom state
        evaluator { Component c -> /*c.evaluator.isBlah(c)*/ return true }
        description e: 'cool', w: 'uncool'
    }
}
