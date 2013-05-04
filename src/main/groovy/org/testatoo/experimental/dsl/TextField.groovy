package org.testatoo.experimental.dsl
/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-04
 */
class TextField extends Component {

    Block enter(String text) {
        return {
            evaluator.reset(this)
            evaluator.focus = this
            evaluator.type(text)
        } as Block
    }

}
